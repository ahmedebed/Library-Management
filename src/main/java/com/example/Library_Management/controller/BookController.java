package com.example.Library_Management.controller;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.dto.request.BookRequest;
import com.example.Library_Management.dto.response.BookResponse;
import com.example.Library_Management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian-api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBook(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(bookService.getAllBook(pageable));
    }
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Validated(OnCreate.class) BookRequest request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @RequestBody @Validated(OnUpdate.class) BookRequest request
    ) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
