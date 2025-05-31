package com.example.Library_Management.service;

import com.example.Library_Management.dto.request.MemberRequest;
import com.example.Library_Management.dto.response.MemberResponse;
import com.example.Library_Management.entity.Member;
import com.example.Library_Management.exception.DuplicateEmailException;
import com.example.Library_Management.exception.MemberNotFoundException;
import com.example.Library_Management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Page<MemberResponse> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberResponse::mapToMemberResponse);
    }

    public MemberResponse findById(Long id) {
        Member member=memberRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException(id));
        return MemberResponse.mapToMemberResponse(member);
    }

    public MemberResponse createMember(MemberRequest memberRequest) {
        Member member=buildMember(memberRequest);
        memberRepository.save(member);
        return MemberResponse.mapToMemberResponse(member);
    }
    private Member buildMember(MemberRequest memberRequest){
        return Member.builder()
                .firstname(memberRequest.firstname())
                .lastname(memberRequest.lastname())
                .email(memberRequest.email())
                .address(memberRequest.address())
                .build();
    }

    public MemberResponse updateMember(MemberRequest memberRequest, Long id) {
        Member member=memberRepository.findById(id)
                .orElseThrow(()-> new MemberNotFoundException(id));
        updateMember(memberRequest,member);
        memberRepository.save(member);
        return MemberResponse.mapToMemberResponse(member);
    }
    private void updateMember(MemberRequest memberRequest,Member member){
        Optional.ofNullable(memberRequest.firstname()).ifPresent(member::setFirstname);
        Optional.ofNullable(memberRequest.lastname()).ifPresent(member::setLastname);
        Optional.ofNullable(memberRequest.email()).ifPresent(email -> {
            boolean exists = memberRepository.existsByEmailAndIdNot(email, member.getId());
            if (exists) {
                throw new DuplicateEmailException("Email is already in use");
            }
            member.setEmail(email);
        });
        Optional.ofNullable(memberRequest.address()).ifPresent(member::setAddress);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
