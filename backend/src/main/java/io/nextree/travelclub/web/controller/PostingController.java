package io.nextree.travelclub.web.controller;


import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.service.BoardService;
import io.nextree.travelclub.web.service.PostingService;
import io.nextree.travelclub.web.service.dto.PostingDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posting")
public class PostingController {
    private PostingService postingService;

    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @PostMapping("/{boardId}") // localhost:8080/posting/
    public String register(@PathVariable Long boardId,@RequestBody PostingDto postingDto) {
        postingService.register(boardId, postingDto);

        return postingDto.getPostingId();
    }

    @GetMapping ("/{postingId}") // localhost:8080/posting/1:00001
    public PostingDto find(@PathVariable String postingId) {
        return postingService.find(postingId);
    }

    @GetMapping// localhost:8080/posting/boardId=boardId
    public List<PostingDto> findByBoardId(@RequestParam Long boardId) {
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

    @DeleteMapping // localhost:8080/posting?boardId=boardId
    public void deleteAllIn(@RequestParam Long boardId) {
        postingService.removeAllIn(boardId);
    }
}
