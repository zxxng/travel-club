package io.nextree.travelclub.web.store.jpastore.repository;

import io.nextree.travelclub.web.store.jpastore.jpo.BoardJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardJpo, String> {
    List<BoardJpo> findAllByName(String name);

}
