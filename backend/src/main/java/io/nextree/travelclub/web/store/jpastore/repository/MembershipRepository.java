package io.nextree.travelclub.web.store.jpastore.repository;

import io.nextree.travelclub.web.store.jpastore.jpo.MembershipJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipJpo, String> {
    List<MembershipJpo> findAllByClubId(String clubId);
    List<MembershipJpo> findAllByMemberEmail(String memberEmail);
}
