package io.nextree.travelclub.web.store;


import io.nextree.travelclub.web.domain.board.SocialBoard;

import java.util.List;

public interface BoardStore {
    String create(SocialBoard board);
    SocialBoard retrieve(String boardId);
    List<SocialBoard> retrieveByName(String name);
    void update(SocialBoard board);
    void delete(String boardId);

    boolean exists(String boardId);
}
