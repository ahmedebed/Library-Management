package com.example.Library_Management.controller;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.dto.request.AuthorRequest;
import com.example.Library_Management.dto.response.AuthorResponse;
import com.example.Library_Management.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian-api/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable long id){
        return ResponseEntity.ok(authorService.getById(id));
    }
    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAll (@PageableDefault Pageable pageable){
        return ResponseEntity.ok(authorService.getAll(pageable));
    }
    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody @Validated(OnCreate.class) AuthorRequest authorRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(authorRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@RequestBody @Validated(OnUpdate.class) AuthorRequest authorRequest
            ,@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.update(authorRequest,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
