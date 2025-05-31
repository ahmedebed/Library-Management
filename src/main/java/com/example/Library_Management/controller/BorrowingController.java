package com.example.Library_Management.controller;

import com.example.Library_Management.dto.request.BorrowingRequest;
import com.example.Library_Management.dto.response.BorrowingResponse;
import com.example.Library_Management.service.BorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staff-api/borrowing")
public class BorrowingController {
    private final BorrowingService borrowingService;
    @PostMapping
    public ResponseEntity<BorrowingResponse> borrowBook(@RequestBody @Valid BorrowingRequest borrowingRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingService.borrowBook(borrowingRequest));
    }
    @PatchMapping("/return/{transactionId}")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable Long transactionId) {
        return ResponseEntity.ok(borrowingService.returnBook(transactionId));
    }
    @GetMapping
    public ResponseEntity<Page<BorrowingResponse>> getAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(borrowingService.getAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingResponse> getById(@PathVariable long id){
        return ResponseEntity.ok(borrowingService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BorrowingResponse> deleteById(@PathVariable long id){
        borrowingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}