package com.book.ReadingIsGood.mapper;

import com.book.ReadingIsGood.model.entity.Book;
import com.book.ReadingIsGood.dtos.requestDto.CreateBookRequestDto;
import com.book.ReadingIsGood.dtos.requestDto.UpdateBookRequestDto;
import com.book.ReadingIsGood.dtos.responseDto.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
        Book toBookFromCreateBookRequestDto(CreateBookRequestDto createBookRequestDto);
        BookDto toBookDtoFromBook(Book book);
        Book toBookDtoFromUpdateBookRequestDto (UpdateBookRequestDto updateBookRequestDto);

}
