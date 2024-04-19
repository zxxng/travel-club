package io.nextree.travelclub.web.store;


import io.nextree.travelclub.web.domain.board.Posting;

import java.util.List;

public interface PostingStore {
    String create(Posting posting);
    Posting retrieve(String postingId);
    List<Posting> retrieveByBoardId(Long boardId);
    List<Posting> retrieveByTitle(String title);
    void update(Posting posting);
    void delete(String postingId);
    void deleteAllByBoardId(Long boardId);

    boolean exists(String postingId);
}
