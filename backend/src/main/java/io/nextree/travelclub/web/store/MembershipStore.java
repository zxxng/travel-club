package io.nextree.travelclub.web.store;

import io.nextree.travelclub.web.domain.club.ClubMembership;

import java.util.List;

public interface MembershipStore {
    String create(ClubMembership membership);
    ClubMembership retrieve(String membershipId);
    ClubMembership retrieveByClubIdAndMemberId(String clubId, String memberId);
    List<ClubMembership> retrieveByClubId(String clubId);
    List<ClubMembership> retrieveByMemberId(String memberId);
    void update(ClubMembership membership);
    void delete(String membershipId);

    boolean exists(String membershipId);

}
