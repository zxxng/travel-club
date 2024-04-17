package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.service.ClubService;
import io.nextree.travelclub.web.service.MemberService;
import io.nextree.travelclub.web.service.MembershipService;
import io.nextree.travelclub.web.service.dto.MembershipDto;
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
    public String register(@RequestBody MembershipDto membershipDto) {
        membershipService.register(membershipDto);

        return membershipDto.getId().toString();
    }

    @GetMapping("/{membershipId}")
    public ClubMembership find(@PathVariable String membershipId) {
        MembershipDto foundedMembership = membershipService.find(membershipId);

        return foundedMembership.toMembership();
    }

    @GetMapping
    public ClubMembership findByClubIdAndMemberId(@RequestParam String clubId, @RequestParam String memberId) {
        MembershipDto foundedMembership = membershipService.findByClubIdAndMemberId(clubId, memberId);

        return foundedMembership.toMembership();
    }

    @GetMapping("/club/{clubId}")
    public List<ClubMembership> findAllByClubId(@PathVariable String clubId) {
        return membershipService.findAllByClubId(clubId).stream()
                .map(MembershipDto::toMembership).collect(Collectors.toList());
    }

    @GetMapping("/member/{memberId}")
    public List<ClubMembership> findAllByMemberId(@PathVariable String memberId) {
        return membershipService.findAllByMemberId(memberId).stream()
                .map(MembershipDto::toMembership).collect(Collectors.toList());
    }

    @PutMapping
    public void modify(@RequestBody MembershipDto clubMembershipDto) {
        membershipService.modify(clubMembershipDto);
    }

    @DeleteMapping("/{membershipId}")
    public void delete(@PathVariable String membershipId) {
        membershipService.delete(membershipId);
    }
}
