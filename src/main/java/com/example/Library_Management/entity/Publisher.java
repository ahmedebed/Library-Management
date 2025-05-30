package com.example.Library_Management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 200)
    private String address;

    @OneToMany(mappedBy = "publisher",fetch = FetchType.LAZY)
    private List<Book> books ;
}

