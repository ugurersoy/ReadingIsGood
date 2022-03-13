package com.book.ReadingIsGood.controller;

import com.book.ReadingIsGood.dtos.requestDto.CreateBookRequestDto;
import com.book.ReadingIsGood.dtos.requestDto.UpdateBookRequestDto;
import com.book.ReadingIsGood.dtos.responseDto.BookDto;
import com.book.ReadingIsGood.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody CreateBookRequestDto createBookRequestDto){
        BookDto bookDto = bookService.createNewBook(createBookRequestDto);
        return ResponseEntity.ok(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateStockBook(@PathVariable String id,@RequestBody UpdateBookRequestDto updateBookRequestDto){
        BookDto bookDto = bookService.updateBook(updateBookRequestDto,id);
        return ResponseEntity.ok(bookDto);
    }
}
