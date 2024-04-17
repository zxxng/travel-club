package io.nextree.travelclub.web.service;

import io.nextree.travelclub.web.service.dto.PostingDto;

import java.util.List;

public interface PostingService {
    String register(String boardId, PostingDto postingDto);
    PostingDto find(String postingId);
    List<PostingDto> findByBoardId(String boardId);
    void modify(PostingDto postingDto);
    void remove(String postingId);
    void removeAllIn(String boardId);
}
