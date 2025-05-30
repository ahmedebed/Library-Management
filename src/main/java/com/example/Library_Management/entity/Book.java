package com.example.Library_Management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(length = 50, unique = true)
    private String isbn;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(length = 30)
    private String language;

    @Column(length = 30)
    private String edition;

    @Column(name = "image", length = 300)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors ;

    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<BorrowingTransaction> borrowingTransactions;
}
