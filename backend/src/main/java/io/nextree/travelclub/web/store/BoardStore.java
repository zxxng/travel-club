package io.nextree.travelclub.web.store;


import io.nextree.travelclub.web.domain.board.SocialBoard;

import java.util.List;

public interface BoardStore {
    Long create(SocialBoard board);
    SocialBoard retrieve(Long boardId);
    List<SocialBoard> retrieveByName(String name);
    void update(SocialBoard board);
    void delete(Long boardId);

    boolean exists(Long boardId);
}
