package com.example.Library_Management.service;

import com.example.Library_Management.dto.response.UserResponse;
import com.example.Library_Management.entity.User;
import com.example.Library_Management.exception.UserNotFoundException;
import com.example.Library_Management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;
    public UserResponse getProfile(long userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));
        return UserResponse.mapToUserResponse(user);
    }
}
