package io.nextree.travelclub.web.controller;


import io.nextree.travelclub.web.domain.board.Posting;
import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.service.BoardService;
import io.nextree.travelclub.web.service.PostingService;
import io.nextree.travelclub.web.service.dto.PostingDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posting")
public class PostingController {
    private PostingService postingService;
    private BoardService boardService;

    public PostingController(PostingService postingService, BoardService boardService) {
        this.postingService = postingService;
        this.boardService = boardService;
    }

    @PostMapping("/{boardId}") // localhost:8080/posting/
    public String register(@PathVariable String boardId,@RequestBody PostingDto postingDto) {
        postingService.register(boardId, postingDto);

        return postingDto.getUsid();
    }

    @GetMapping // localhost:8080/posting?boardId=boardId&postingId=postingId
    public PostingDto find(@RequestParam String boardId, String postingId) {
        return postingService.find(postingId);
    }

    @GetMapping("/{boardId}")
    public List<PostingDto> findByBoardId(@PathVariable String boardId) {
        SocialBoard targetBoard = boardService.find(boardId).toBoard();

        return postingService.findByBoardId(boardId);
    }

    @PutMapping
    public void modify(@RequestBody PostingDto postingDto) {
        postingService.modify(postingDto);
    }

    @DeleteMapping("/{postingId}")
    public void delete(@PathVariable String postingId) {
        postingService.remove(postingId);
    }

    @DeleteMapping // localhost:8080/board?boardId=boardId
    public void deleteAllIn(@RequestParam String boardId) {
        this.findByBoardId(boardId).forEach(posting -> this.delete(posting.getUsid()));
    }
}
