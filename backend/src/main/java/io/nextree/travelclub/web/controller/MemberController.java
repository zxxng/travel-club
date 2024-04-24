package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.service.MemberService;
import io.nextree.travelclub.web.service.dto.MemberDto;
import io.nextree.travelclub.web.service.dto.MembershipDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
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
    public ResponseEntity<String> register(@RequestBody MemberDto memberDto) {
        memberService.register(memberDto);

        return ResponseEntity.ok("{\"email\":\"" + memberDto.getEmail()+ "\"}");
    }

    @GetMapping("/{email}")
    public MemberDto find(@PathVariable String email) {
        return memberService.find(email);
    }

    @GetMapping// localhost:8080/member?name=jena
    public List<MemberDto> findByName(@RequestParam String name) {
        return memberService.findByName(name);
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody MemberDto memberDto) {
        memberService.modify(memberDto);

        return ResponseEntity.ok("{\"message\":\"Member modified successfully\"}");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> delete(@PathVariable String email) {
        memberService.remove(email);

        return ResponseEntity.ok("{\"message\":\"Member deleted successfully\"}");
    }
}
