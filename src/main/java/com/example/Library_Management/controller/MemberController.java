package com.example.Library_Management.controller;

import com.example.Library_Management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff-api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

}
