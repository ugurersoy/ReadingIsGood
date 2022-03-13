package com.book.ReadingIsGood.unit;

import com.book.ReadingIsGood.mapper.BookMapper;
import com.book.ReadingIsGood.model.entity.Book;
import com.book.ReadingIsGood.dtos.requestDto.CreateBookRequestDto;
import com.book.ReadingIsGood.dtos.requestDto.UpdateBookRequestDto;
import com.book.ReadingIsGood.dtos.responseDto.BookDto;
import com.book.ReadingIsGood.repository.BookRepository;
import com.book.ReadingIsGood.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

     @InjectMocks
     BookService bookService;


     @Mock
     BookRepository bookRepository;

     @Mock
     BookMapper bookMapper;

     LocalDateTime dateTime = LocalDateTime.now();

     Book book;
     BookDto bookDto;


    @BeforeEach
    void init() {
        this.book = Book.builder().id("1").amount(11).price(BigDecimal.ONE).createDate(dateTime).name("Kel oğlan").build();
        this.bookDto = BookDto.builder().amount(11).price(BigDecimal.ONE).name("Kel oğlan").createDate(dateTime).build();

    }
       @Test
       public void whenCreateBookRequestDtoSuccess(){
           CreateBookRequestDto createBookRequestDto = CreateBookRequestDto.builder().amount(11).price(BigDecimal.ONE).name("Kel oğlan").build();

           when(bookMapper.toBookFromCreateBookRequestDto(createBookRequestDto)).thenReturn(book);
           when(bookMapper.toBookDtoFromBook(book)).thenReturn(bookDto);
           when(bookRepository.save(book)).thenReturn(book);

           BookDto bookDtoResponse = bookService.createNewBook(createBookRequestDto);
           assertNotNull(bookDtoResponse);
           assertEquals(bookDtoResponse.getPrice(),BigDecimal.ONE);
           assertEquals(bookDtoResponse.getName(),"Kel oğlan");
       }

    @Test
    public void whenUpdateBookRequestDtoSuccess(){
        UpdateBookRequestDto updateBookRequestDto = UpdateBookRequestDto.builder().amount(11).build();

        when(bookMapper.toBookDtoFromUpdateBookRequestDto(updateBookRequestDto)).thenReturn(book);
        when(bookMapper.toBookDtoFromBook(book)).thenReturn(bookDto);

        when(bookRepository.findById("1")).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(book)).thenReturn(book);

        BookDto bookDtoResponse = bookService.updateBook(updateBookRequestDto,"1");

        assertNotNull(bookDtoResponse);
        assertEquals(bookDtoResponse.getAmount(),11);
    }


}
