package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.service.BoardService;
import io.nextree.travelclub.web.service.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping // localhost:8080/board/
    public String register(@RequestBody BoardDto boardDto) {
        boardService.register(boardDto);

        return boardDto.getClubId();
    }

    @GetMapping("/{boardId}")
    public SocialBoard find(@PathVariable String boardId) {
        return boardService.find(boardId).toBoard();
    }

    @GetMapping // localhost:8080/board?name=boardName
    public List<SocialBoard> findAllByName(@RequestParam String name) {
        List<BoardDto> boardDtos = boardService.findByName(name);

        return boardDtos.stream().map(BoardDto::toBoard).collect(Collectors.toList());
    }

    @GetMapping ("/club")// localhost:8080/board/club?name=clubName
    public SocialBoard findByClubName(@RequestParam String name) {
        return boardService.findByClubName(name).toBoard();
    }

    @PutMapping
    public void modify(@RequestBody BoardDto boardDto) {
        boardService.modify(boardDto);
    }

    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable String boardId) {
        boardService.remove(boardId);
    }
}
