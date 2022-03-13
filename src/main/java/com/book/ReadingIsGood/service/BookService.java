package com.book.ReadingIsGood.service;

import com.book.ReadingIsGood.exceptions.BookNotFoundException;
import com.book.ReadingIsGood.mapper.BookMapper;
import com.book.ReadingIsGood.model.entity.Book;
import com.book.ReadingIsGood.dtos.requestDto.CreateBookRequestDto;
import com.book.ReadingIsGood.dtos.requestDto.UpdateBookRequestDto;
import com.book.ReadingIsGood.dtos.responseDto.BookDto;
import com.book.ReadingIsGood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    /**
     * This method created for inserting book
     *
     **/
    public BookDto createNewBook(CreateBookRequestDto createBookRequestDto) {
        BookDto bookDto = null;
        try {
            bookDto = bookMapper.toBookDtoFromBook(bookRepository.save(bookMapper.toBookFromCreateBookRequestDto(createBookRequestDto)));
        } catch (Exception ex) {

        }
        return bookDto;
    }

    public BookDto updateBook(UpdateBookRequestDto updateBookRequestDto,String id) {

        Optional<Book> bookOptional = bookRepository.findById(id);

        bookOptional.ifPresent(book -> {
            book.setAmount(updateBookRequestDto.getAmount());
            bookRepository.save(book);
        });

        return bookOptional.map(bookMapper :: toBookDtoFromBook).orElseThrow(()->new BookNotFoundException("Kitap Bulunamadı"));
    }

}
