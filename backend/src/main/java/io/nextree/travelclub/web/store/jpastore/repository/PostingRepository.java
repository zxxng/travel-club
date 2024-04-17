package io.nextree.travelclub.web.store.jpastore.repository;

import io.nextree.travelclub.web.store.jpastore.jpo.PostingJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<PostingJpo, String> {
    List<PostingJpo> findAllByBoardId(String boardId);
    List<PostingJpo> findAllByTitle(String title);
}
