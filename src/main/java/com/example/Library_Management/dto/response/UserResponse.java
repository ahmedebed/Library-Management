package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.User;
import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String firstname,
        String lastname,
        String email,
        String role
) {
    public static UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .build();
    }
}
