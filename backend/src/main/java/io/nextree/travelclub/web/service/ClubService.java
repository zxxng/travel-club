package io.nextree.travelclub.web.service;

import io.nextree.travelclub.web.service.dto.TravelClubDto;

import java.util.List;

public interface ClubService {
    void register(TravelClubDto clubDto);
    TravelClubDto findClub(String clubId);
    TravelClubDto findClubByName(String name);
    void modify(TravelClubDto clubDto);
    void remove(String clubId);

//    void addMembership(ClubMembershipDto membershipDto);
//    ClubMembershipDto findMembershipIn(String clubId, String memberId);
//    List<ClubMembershipDto> findAllMembershipsIn(String clubId);
//    void modifyMembership(String clubId, ClubMembershipDto membership);
//    void removeMembership(String clubId, String memberId);
}
