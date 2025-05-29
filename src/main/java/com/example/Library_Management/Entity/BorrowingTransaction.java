package com.example.Library_Management.Entity;

import com.example.Library_Management.Enums.BorrowingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "borrowing_transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private ZonedDateTime borrowDate;


    @Column(name = "return_date")
    private ZonedDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BorrowingStatus status;

}
