package io.nextree.travelclub.web.service;

import io.nextree.travelclub.web.service.dto.MembershipDto;

import java.util.List;

public interface MembershipService {
    void register(MembershipDto membershipDto);
    MembershipDto find(String membershipId);
    MembershipDto findByClubIdAndMemberId(String clubId, String memberEmail);
    List<MembershipDto> findAllByClubId(String clubId);
    List<MembershipDto> findAllByMemberId(String memberId);
    void modify(MembershipDto clubMembershipDto);
    void delete(String membershipId);
}
