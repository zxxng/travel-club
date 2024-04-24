package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.service.MembershipService;
import io.nextree.travelclub.web.service.dto.MembershipDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/membership")
public class MembershipController {
    private MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public Long register(@RequestBody MembershipDto membershipDto) {
        membershipService.register(membershipDto);

        return membershipDto.getClubId();
    }

    @GetMapping("/{clubId}/{memberId}")
    public MembershipDto findById(@PathVariable("clubId") Long clubId, @PathVariable("memberId") String memberId) {
        return membershipService.findById(clubId, memberId);
    }

    @GetMapping
    public List<MembershipDto> findAllByClubIdOrMemberId(@RequestParam(required = false) Long clubId, @RequestParam(required = false) String memberId) {
        return membershipService.findAllByClubIdOrMemberId(clubId, memberId);
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody MembershipDto clubMembershipDto) {
        membershipService.modify(clubMembershipDto);

        return ResponseEntity.ok("{\"message\":\"Membership modified successfully\"}");
    }

    @DeleteMapping("/{clubId}/{memberId}")
    public ResponseEntity<String> delete(@PathVariable("clubId") Long clubId, @PathVariable("memberId") String memberId) {
        membershipService.delete(clubId, memberId);

        return ResponseEntity.ok("{\"message\":\"Membership deleted successfully\"}");
    }
}
