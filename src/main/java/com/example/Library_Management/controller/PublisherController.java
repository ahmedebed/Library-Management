package com.example.Library_Management.controller;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.dto.request.PublisherRequest;
import com.example.Library_Management.dto.response.PublisherResponse;
import com.example.Library_Management.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian-api/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PublisherResponse>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(publisherService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> create(@RequestBody @Validated(OnCreate.class) PublisherRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponse> update(@RequestBody @Validated(OnUpdate.class) PublisherRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
