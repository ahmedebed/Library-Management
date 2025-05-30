package com.example.Library_Management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String firstname;
    @Column(nullable = false, length = 80)
    private String lastname;
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY)
    private List<BorrowingTransaction> borrowingTransactions;


}

