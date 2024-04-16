package io.nextree.travelclub.web.store;

import io.nextree.travelclub.web.domain.club.CommunityMember;

import java.util.List;

public interface MemberStore {
    String create(CommunityMember member);
    CommunityMember retrieve(String email);
    List<CommunityMember> retrieveByName(String name);
    void update(CommunityMember member);
    void delete(String email);

    boolean exists(String email);
}
