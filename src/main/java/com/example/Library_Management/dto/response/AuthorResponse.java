package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.Author;
import lombok.Builder;

@Builder
public record AuthorResponse(
        Long id,
        String name,
        String description,
        String nationality
) {
    public static AuthorResponse mapToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .description(author.getDescription())
                .nationality(author.getNationality())
                .build();
    }
}
