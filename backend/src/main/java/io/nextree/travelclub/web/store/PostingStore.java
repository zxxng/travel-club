package io.nextree.travelclub.web.store;


import io.nextree.travelclub.web.domain.board.Posting;

import java.util.List;

public interface PostingStore {
    String create(Posting posting);
    Posting retrieve(String postingId);
    List<Posting> retrieveByBoardId(String boardId);
    List<Posting> retrieveByTitle(String title);
    void update(Posting posting);
    void delete(String postingId);

    boolean exists(String postingId);
}
