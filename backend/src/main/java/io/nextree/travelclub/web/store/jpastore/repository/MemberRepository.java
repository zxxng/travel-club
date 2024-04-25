package io.nextree.travelclub.web.store.jpastore.repository;

import io.nextree.travelclub.web.store.jpastore.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberJpo, String> {
    List<MemberJpo> findAllByName(String name);

    @Modifying
    @Query("UPDATE MemberJpo member SET member.name = :name, member.nickName = :nickName, member.phoneNumber = :phoneNumber, member.birthDay = :birthDay WHERE member.email = :email")
    void updateMemberInfoById(@Param("email") String email, @Param("name") String name, @Param("nickName") String nickName, @Param("phoneNumber") String phoneNumber, @Param("birthDay") String birthDay);
}
