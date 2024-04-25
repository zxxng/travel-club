package io.nextree.travelclub.web.store.jpastore.repository;

import io.nextree.travelclub.web.store.jpastore.jpo.TravelClubJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubRepository extends JpaRepository<TravelClubJpo, Long> {
    List<TravelClubJpo> findAllByName(String name);

    @Modifying
    @Query("UPDATE TravelClubJpo club SET club.name = :name ,club.intro = :intro WHERE club.id = :id")
    void updateUserNameAndIntroById(@Param("id") Long id, @Param("name") String name, @Param("intro") String intro);

}
