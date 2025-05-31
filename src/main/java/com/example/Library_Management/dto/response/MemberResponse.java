package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MemberResponse(
        Long id,
        String firstname,
        String lastname,
        String fullName,
        String email,
        String address
) {
    public static MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .firstname(member.getFirstname())
                .lastname(member.getLastname())
                .email(member.getEmail())
                .address(member.getAddress())
                .build();
    }
    public static MemberResponse mapToBasicMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .fullName(member.getFirstname() + " " +member.getLastname())
                .email(member.getEmail())
                .build();
    }

}
