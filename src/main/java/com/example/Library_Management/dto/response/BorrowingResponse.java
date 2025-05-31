package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.BorrowingTransaction;
import com.example.Library_Management.enums.BorrowingStatus;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record BorrowingResponse(
        Long id,
        MemberResponse member,
        BookResponse book,
        ZonedDateTime borrowDate,
        ZonedDateTime returnDate,
        BorrowingStatus status
) {
    public static BorrowingResponse mapToBorrowingResponse(BorrowingTransaction borrowingTransaction){
        return BorrowingResponse.builder()
                .id(borrowingTransaction.getId())
                .member(MemberResponse.mapToBasicMemberResponse(borrowingTransaction.getMember()))
                .book(BookResponse.mapToBookBasicResponse(borrowingTransaction.getBook()))
                .borrowDate(borrowingTransaction.getBorrowDate())
                .returnDate(borrowingTransaction.getReturnDate() !=null ?borrowingTransaction.getReturnDate() :null)
                .status(borrowingTransaction.getStatus())
                .build();
    }
}
