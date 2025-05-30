package com.example.Library_Management.service;

import com.example.Library_Management.dto.request.UserRequest;
import com.example.Library_Management.dto.response.UserResponse;
import com.example.Library_Management.entity.User;
import com.example.Library_Management.exception.UserNotFoundException;
import com.example.Library_Management.repository.UserRepository;
import com.example.Library_Management.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public UserResponse create(UserRequest userRequest) {
       User user= buildUser(userRequest);
       userRepository.save(user);
       return UserResponse.mapToUserResponse(user);
    }
    private User buildUser(UserRequest userRequest){
        return User.builder()
                .firstname(userRequest.firstname())
                .lastname(userRequest.lastname())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .role(userRequest.role())
                .build();
    }

    public Page<UserResponse> getAll(String search, Pageable pageable) {
        Specification<User> specification= UserSpecification.build(search);
        return userRepository.findAll(specification,pageable)
                .map(UserResponse::mapToUserResponse);
    }

    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return UserResponse.mapToUserResponse(user);
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
