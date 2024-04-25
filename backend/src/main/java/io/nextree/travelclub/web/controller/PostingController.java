package io.nextree.travelclub.web.controller;


import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.service.BoardService;
import io.nextree.travelclub.web.service.PostingService;
import io.nextree.travelclub.web.service.dto.PostingDto;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> register(@PathVariable Long boardId, @RequestBody PostingDto postingDto) {
        postingService.register(boardId, postingDto);

        return ResponseEntity.ok("{\"postingId\":\"" + postingDto.getPostingId() + "\"}");
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
    public ResponseEntity<String> modify(@RequestBody PostingDto postingDto) {
        postingService.modify(postingDto);

        return ResponseEntity.ok("{\"message\":\"Posting modified successfully\"}");
    }

    @DeleteMapping("/{postingId}")
    public ResponseEntity<String> delete(@PathVariable String postingId) {
        postingService.remove(postingId);

        return ResponseEntity.ok("{\"message\":\"Posting deleted successfully\"}");
    }

    @DeleteMapping // localhost:8080/posting?boardId=boardId
    public ResponseEntity<String> deleteAllIn(@RequestParam Long boardId) {
        postingService.removeAllIn(boardId);

        return ResponseEntity.ok("{\"message\":\"All postings were deleted successfully\"}");
    }
}
