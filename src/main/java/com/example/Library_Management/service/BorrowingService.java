package com.example.Library_Management.service;

import com.example.Library_Management.dto.request.BorrowingRequest;
import com.example.Library_Management.dto.response.BorrowingResponse;
import com.example.Library_Management.entity.Book;
import com.example.Library_Management.entity.BorrowingTransaction;
import com.example.Library_Management.entity.Member;
import com.example.Library_Management.enums.BorrowingStatus;
import com.example.Library_Management.exception.AlreadyReturnedException;
import com.example.Library_Management.exception.BookNotFoundException;
import com.example.Library_Management.exception.BorrowingTransactionNotFoundException;
import com.example.Library_Management.exception.MemberNotFoundException;
import com.example.Library_Management.repository.BookRepository;
import com.example.Library_Management.repository.BorrowingRepository;
import com.example.Library_Management.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
        private final MemberRepository memberRepository;
        private final BookRepository bookRepository;
        private final ZoneId zoneId = ZoneId.of("Africa/Cairo");


    public BorrowingResponse borrowBook(BorrowingRequest request) {
            Member member = memberRepository.findById(request.memberId())
                    .orElseThrow(() -> new MemberNotFoundException("Member not found"));

            Book book = bookRepository.findById(request.bookId())
                    .orElseThrow(() -> new BookNotFoundException("Book not found"));

            boolean alreadyBorrowed = borrowingRepository.existsByMemberAndBookAndStatus(member, book, BorrowingStatus.BORROWED);
            if (alreadyBorrowed) {
                throw new IllegalStateException("This member has already borrowed this book and hasn't returned it yet");
            }
            BorrowingTransaction transaction=build(member,book);
            transaction=borrowingRepository.save(transaction);
            return BorrowingResponse.mapToBorrowingResponse(transaction);
        }
    private BorrowingTransaction build(Member member,Book book){
            return BorrowingTransaction.builder()
                    .member(member)
                    .book(book)
                    .borrowDate(ZonedDateTime.now(zoneId))
                    .returnDate(null)
                    .status(BorrowingStatus.BORROWED)
                    .build();
    }
    @Transactional
    public BorrowingResponse returnBook(Long transactionId) {
        BorrowingTransaction transaction = borrowingRepository.findById(transactionId)
                .orElseThrow(() -> new BorrowingTransactionNotFoundException("Borrowing transaction not found"));

        if (transaction.getStatus() == BorrowingStatus.RETURNED) {
            throw new AlreadyReturnedException("This book was already returned");
        }
        transaction.setStatus(BorrowingStatus.RETURNED);
        transaction.setReturnDate(ZonedDateTime.now(zoneId));
        return BorrowingResponse.mapToBorrowingResponse(transaction);
    }
    
    public Page<BorrowingResponse> getAll(Pageable pageable) {
        return borrowingRepository.findAll(pageable)
                .map(BorrowingResponse::mapToBorrowingResponse);
    }

    public BorrowingResponse getById(long id) {
        BorrowingTransaction transaction = borrowingRepository.findById(id)
                .orElseThrow(() -> new BorrowingTransactionNotFoundException("Borrowing transaction not found"));
        return BorrowingResponse.mapToBorrowingResponse(transaction);
    }

    public void deleteById(long id) {
        BorrowingTransaction transaction = borrowingRepository.findById(id)
                .orElseThrow(() -> new BorrowingTransactionNotFoundException("Borrowing transaction not found"));

        if (transaction.getStatus() == BorrowingStatus.BORROWED) {
            throw new IllegalStateException("Cannot delete borrowing transaction while the book is still borrowed");
        }
        borrowingRepository.delete(transaction);
    }

}
