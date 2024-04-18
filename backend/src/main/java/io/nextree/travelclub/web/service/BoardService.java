package io.nextree.travelclub.web.service;


import io.nextree.travelclub.web.service.dto.BoardDto;

import java.util.List;

public interface BoardService {
    Long register(BoardDto boardDto);
    BoardDto find(Long boardId);
    List<BoardDto> findByName(String boardName);
    BoardDto findByClubName(String clubName);
    void modify(BoardDto boardDto);
    void remove(Long boardId);
}
