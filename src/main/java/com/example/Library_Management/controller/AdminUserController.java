package com.example.Library_Management.controller;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.dto.request.UserRequest;
import com.example.Library_Management.dto.response.UserResponse;
import com.example.Library_Management.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    @PostMapping
    public ResponseEntity<UserResponse> create (@RequestBody @Validated(OnCreate.class) UserRequest userRequest){
         return ResponseEntity.status(HttpStatus.CREATED).body(adminUserService.create(userRequest));
    }
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUser(
            @RequestParam(required = false) String search,
            @PageableDefault Pageable pageable){
        return ResponseEntity.ok(adminUserService.getAll(search,pageable));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(adminUserService.getUserById(userId));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long userId){
        adminUserService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
