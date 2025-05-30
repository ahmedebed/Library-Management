package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.Member;
import lombok.Builder;

@Builder
public record MemberResponse(
        Long id,
        String firstname,
        String lastname,
        String email,
        String address
) {
    public static MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .firstname(member.getFirstname())
                .lastname(member.getLastname())
                .email(member.getEmail())
                .address(member.getAddress())
                .build();
    }
}
