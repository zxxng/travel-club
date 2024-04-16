package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.service.MemberService;
import io.nextree.travelclub.web.service.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping // localhost:8080/member/
    public String register(@RequestBody MemberDto memberDto) {
        memberService.register(memberDto);

        return memberDto.getEmail();
    }

    @GetMapping("/{email}")
    public CommunityMember find(@PathVariable String email) {
        System.out.println(email);
        return memberService.find(email).toMember();
    }

    @GetMapping// localhost:8080/member?name=jena
    public List<CommunityMember> findByName(@RequestParam String name) {
        return memberService.findByName(name).stream()
                .map(MemberDto::toMember).collect(Collectors.toList());
    }

    @PutMapping
    public void modify(@RequestBody MemberDto memberDto) {
        memberService.modify(memberDto);
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        memberService.remove(email);
    }
}
