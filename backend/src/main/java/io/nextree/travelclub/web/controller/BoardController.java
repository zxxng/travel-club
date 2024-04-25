package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.service.BoardService;
import io.nextree.travelclub.web.service.dto.BoardDto;
import org.springframework.http.ResponseEntity;
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
    public Long register(@RequestBody BoardDto boardDto) {
        boardService.register(boardDto);

        return boardDto.getClubId();
    }

    @GetMapping("/{boardId}")
    public BoardDto find(@PathVariable Long boardId) {
        return boardService.find(boardId);
    }

    @GetMapping // localhost:8080/board?name=boardName
    public List<BoardDto> findAllByName(@RequestParam String name) {
        return boardService.findByName(name);
    }

    @GetMapping ("/club")// localhost:8080/board/club?name=clubName
    public BoardDto findByClubName(@RequestParam String name) {
        return boardService.findByClubName(name);
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody BoardDto boardDto) {
        boardService.modify(boardDto);

        return ResponseEntity.ok("{\"message\":\"Board modified successfully\"}");
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> delete(@PathVariable Long boardId) {
        boardService.remove(boardId);

        return ResponseEntity.ok("{\"message\":\"Board deleted successfully\"}");
    }
}
