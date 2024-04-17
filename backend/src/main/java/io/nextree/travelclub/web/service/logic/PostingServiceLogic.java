package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.board.Posting;
import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.service.PostingService;
import io.nextree.travelclub.web.service.dto.PostingDto;
import io.nextree.travelclub.web.store.BoardStore;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.PostingStore;
import io.nextree.travelclub.web.util.exception.NoSuchBoardException;
import io.nextree.travelclub.web.util.exception.NoSuchMemberException;
import io.nextree.travelclub.web.util.exception.NoSuchPostingException;
import io.nextree.travelclub.web.util.helper.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostingServiceLogic implements PostingService {
    private BoardStore boardStore;
    private PostingStore postingStore;
    private ClubStore clubStore;

    public PostingServiceLogic(BoardStore boardStore, PostingStore postingStore, ClubStore clubStore) {
        this.boardStore = boardStore;
        this.postingStore = postingStore;
        this.clubStore = clubStore;
    }

    @Override
    public String register(String boardId, PostingDto postingDto) {
        Optional.ofNullable(clubStore.retrieve(boardId))
                .map(club -> club.getMembershipBy(postingDto.getWriterEmail()))
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with email: " + postingDto.getWriterEmail()));

        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(board -> postingStore.create(postingDto.toPostingIn(board)))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));
    }

    @Override
    public PostingDto find(String postingId) {
        Posting foundPosting = Optional.ofNullable(postingStore.retrieve(postingId))
                .orElseThrow(() -> new NoSuchPostingException("No such posting whit id: " + postingId));

        foundPosting.setReadCount(foundPosting.getReadCount() + 1);

        return new PostingDto(foundPosting);
    }

    @Override
    public List<PostingDto> findByBoardId(String boardId) {
        Optional.ofNullable(boardStore.retrieve(boardId))
                .orElseThrow(() -> new NoSuchBoardException("No such board whit id: " + boardId));

        return postingStore.retrieveByBoardId(boardId).stream()
                .map(posting -> new PostingDto(posting))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(PostingDto postingDto) {
        String postingId = postingDto.getUsid();
        Posting targetPosting = Optional.ofNullable(postingStore.retrieve(postingId))
                .orElseThrow(() -> new NoSuchPostingException("No such posting with id --> " + postingId));

        if (StringUtil.isEmpty(postingDto.getTitle())) {
            postingDto.setTitle(targetPosting.getTitle());
        }
        if (StringUtil.isEmpty(postingDto.getContents())) {
            postingDto.setContents(targetPosting.getContents());
        }

        postingStore.update(postingDto.toPostingIn(postingId, targetPosting.getBoardId()));
    }

    @Override
    public void remove(String postingId) {
        if (!postingStore.exists(postingId)) {
            throw new NoSuchPostingException("No such posting with id: " + postingId);
        }

        postingStore.delete(postingId);
    }

    @Override
    public void removeAllIn(String boardId) {
        postingStore.retrieveByBoardId(boardId).stream()
                .forEach(posting -> postingStore.delete(posting.getId()));
    }
}
