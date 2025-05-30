package com.example.Library_Management.service;

import com.example.Library_Management.dto.request.BookRequest;
import com.example.Library_Management.dto.response.BookResponse;
import com.example.Library_Management.entity.Author;
import com.example.Library_Management.entity.Book;
import com.example.Library_Management.entity.Category;
import com.example.Library_Management.entity.Publisher;
import com.example.Library_Management.exception.BookNotFoundException;
import com.example.Library_Management.exception.CategoryNotFoundException;
import com.example.Library_Management.exception.PublisherNotFoundException;
import com.example.Library_Management.repository.AuthorRepository;
import com.example.Library_Management.repository.BookRepository;
import com.example.Library_Management.repository.CategoryRepository;
import com.example.Library_Management.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public BookResponse getBookById(Long id) {
        Book book=bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
        return BookResponse.mapToBookDetailResponse(book);
    }

    public Page<BookResponse> getAllBook(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(BookResponse::mapToBookSummaryResponse);
    }

    public BookResponse createBook(BookRequest request) {
        Category category = validateAndGetCategory(request.categoryId());
        Publisher publisher = validateAndGetPublisher(request.publisherId());
        List<Author> authors = validateAndGetAuthors(request.authorIds());

        Book book = buildBookFromRequest(request, category, publisher, authors);
        bookRepository.save(book);

        return BookResponse.mapToBookDetailResponse(book);
    }

    private Category validateAndGetCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    private Publisher validateAndGetPublisher(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    private List<Author> validateAndGetAuthors(List<Long> authorIds) {
        List<Author> authors = authorRepository.findAllById(authorIds);
        if (authors.size() != authorIds.size()) {
            throw new IllegalArgumentException("One or more authors not found");
        }
        return authors;
    }

    private Book buildBookFromRequest(BookRequest request, Category category, Publisher publisher, List<Author> authors) {
        return Book.builder()
                .title(request.title())
                .summary(request.summary())
                .edition(request.edition())
                .image(request.image())
                .language(request.language())
                .isbn(request.isbn())
                .publicationYear(request.publicationYear())
                .category(category)
                .publisher(publisher)
                .authors(authors)
                .build();
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        updateSimpleFields(book, request);
        updateRelations(book, request);

        Book updatedBook = bookRepository.save(book);
        return BookResponse.mapToBookDetailResponse(updatedBook);
    }

    private void updateSimpleFields(Book book, BookRequest request) {
        Optional.ofNullable(request.title()).ifPresent(book::setTitle);
        Optional.ofNullable(request.summary()).ifPresent(book::setSummary);
        Optional.ofNullable(request.edition()).ifPresent(book::setEdition);
        Optional.ofNullable(request.image()).ifPresent(book::setImage);
        Optional.ofNullable(request.language()).ifPresent(book::setLanguage);
        Optional.ofNullable(request.isbn()).ifPresent(book::setIsbn);
        Optional.ofNullable(request.publicationYear()).ifPresent(book::setPublicationYear);
    }

    private void updateRelations(Book book, BookRequest request) {
        if (request.categoryId() != null) {
            Category category = validateAndGetCategory(request.categoryId());
            book.setCategory(category);
        }
        if (request.publisherId() != null) {
            Publisher publisher = validateAndGetPublisher(request.publisherId());
            book.setPublisher(publisher);
        }
        if (request.authorIds() != null && !request.authorIds().isEmpty()) {
            List<Author> authors = validateAndGetAuthors(request.authorIds());
            book.clearAuthors();
            book.addAuthors(authors);
        }
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
