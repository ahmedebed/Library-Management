package com.example.Library_Management.dto.response;
import com.example.Library_Management.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookResponse(
        Long id,
        String title,
        String summary,
        String isbn,
        Integer publicationYear,
        String language,
        String edition,
        String image,
        PublisherResponse publisher,
        CategoryResponse category,
        List<AuthorResponse> authors
) {

    public static BookResponse mapToBookSummaryResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .publisher(book.getPublisher() != null ?
                        PublisherResponse.mapToPublisherResponse(book.getPublisher()) : null)
                .build();
    }

    public static BookResponse mapToBookDetailResponse(Book book) {
        List<AuthorResponse> authorResponses = book.getAuthors() == null ? List.of() :
                book.getAuthors().stream()
                        .map(AuthorResponse::mapToAuthorResponse)
                        .collect(Collectors.toList());

        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .summary(book.getSummary())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .language(book.getLanguage())
                .edition(book.getEdition())
                .image(book.getImage())
                .publisher(book.getPublisher() != null ?
                        PublisherResponse.mapToPublisherResponse(book.getPublisher()) : null)
                .category(book.getCategory() != null ?
                        CategoryResponse.mapToCategoryResponse(book.getCategory()) : null)
                .authors(authorResponses)
                .build();
    }
}

