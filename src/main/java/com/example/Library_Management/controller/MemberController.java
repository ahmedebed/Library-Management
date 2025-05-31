package com.example.Library_Management.controller;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.dto.request.MemberRequest;
import com.example.Library_Management.dto.response.MemberResponse;
import com.example.Library_Management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff-api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping
    public ResponseEntity<Page<MemberResponse>> getAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(memberService.getAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(memberService.findById(id));
    }
    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Validated(OnCreate.class)MemberRequest memberRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@RequestBody @Validated(OnUpdate.class)MemberRequest memberRequest, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.updateMember(memberRequest,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
