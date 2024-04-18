package io.nextree.travelclub.web.service;

import io.nextree.travelclub.web.service.dto.MembershipDto;

import java.util.List;

public interface MembershipService {
    void register(MembershipDto membershipDto);
    MembershipDto findById(Long clubId, String memberEmail);
    List<MembershipDto> findAllByClubIdOrMemberId(Long clubId, String memberId);
    void modify(MembershipDto clubMembershipDto);
    void delete(Long clubId, String memberEmail);
}
