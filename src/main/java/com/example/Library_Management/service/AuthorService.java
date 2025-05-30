package com.example.Library_Management.service;

import com.example.Library_Management.dto.request.AuthorRequest;
import com.example.Library_Management.dto.response.AuthorResponse;
import com.example.Library_Management.entity.Author;
import com.example.Library_Management.exception.AuthorNotFoundException;
import com.example.Library_Management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorResponse getById(long id) {
        Author author=authorRepository.findById(id)
                .orElseThrow(()->new AuthorNotFoundException(id));
        return AuthorResponse.mapToAuthorResponse(author);
    }

    public Page<AuthorResponse> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(AuthorResponse::mapToAuthorResponse);
    }

    public AuthorResponse create(AuthorRequest authorRequest) {
        Author author=buildAuthor(authorRequest);
        authorRepository.save(author);
        return AuthorResponse.mapToAuthorResponse(author);
    }
    private Author buildAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .name(authorRequest.name())
                .description(authorRequest.description())
                .nationality(authorRequest.nationality())
                .build();
    }

    public AuthorResponse update(AuthorRequest authorRequest, Long id) {
        Author author=authorRepository.findById(id)
                .orElseThrow(()->new AuthorNotFoundException(id));
        UpdateAuthor(authorRequest,author);
        authorRepository.save(author);
        return AuthorResponse.mapToAuthorResponse(author);
    }
    private void UpdateAuthor(AuthorRequest authorRequest,Author author){
        Optional.ofNullable(authorRequest.name()).ifPresent(author::setName);
        Optional.ofNullable(authorRequest.description()).ifPresent(author::setDescription);
        Optional.ofNullable(authorRequest.nationality()).ifPresent(author::setNationality);
    }

    public void deleteById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id " + id));

        if (author.getBooks() != null && !author.getBooks().isEmpty()) {
            throw new IllegalStateException("Cannot delete author because they are assigned to one or more books");
        }
        authorRepository.deleteById(id);
    }

}
