package com.example.Library_Management.repository;

import com.example.Library_Management.entity.Book;
import com.example.Library_Management.entity.BorrowingTransaction;
import com.example.Library_Management.entity.Member;
import com.example.Library_Management.enums.BorrowingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingTransaction,Long> {
    boolean existsByMemberAndBookAndStatus(Member member, Book book, BorrowingStatus borrowingStatus);
}
