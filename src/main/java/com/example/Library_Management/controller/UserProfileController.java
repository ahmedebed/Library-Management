package com.example.Library_Management.controller;

import com.example.Library_Management.dto.response.UserResponse;
import com.example.Library_Management.service.GlobalService;
import com.example.Library_Management.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff-api/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final GlobalService globalService;
    @GetMapping
    public ResponseEntity<UserResponse> getProfile(HttpServletRequest request){
        long userId =globalService.extractUserIdFromToken(request);
        return ResponseEntity.ok(userProfileService.getProfile(userId));
    }
}
