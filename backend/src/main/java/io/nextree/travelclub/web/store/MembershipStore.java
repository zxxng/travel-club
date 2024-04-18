package io.nextree.travelclub.web.store;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.store.jpastore.jpo.id.MembershipId;

import java.util.List;

public interface MembershipStore {
    String create(ClubMembership membership);
    ClubMembership retrieveById(MembershipId membershipId);
    List<ClubMembership> retrieveByClubId(Long clubId);
    List<ClubMembership> retrieveByMemberId(String memberId);
    void update(ClubMembership membership);
    void delete(MembershipId membershipId);

    boolean exists(MembershipId membershipId);

}
