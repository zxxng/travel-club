package io.nextree.travelclub.web.store.jpastore.repository;

import io.nextree.travelclub.web.store.jpastore.jpo.MembershipJpo;
import io.nextree.travelclub.web.store.jpastore.jpo.id.MembershipId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipJpo, MembershipId> {
    List<MembershipJpo> findAllByClubId(Long clubId);
    List<MembershipJpo> findAllByMemberEmail(String memberEmail);
}
