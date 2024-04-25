package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.board.Posting;
import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.service.PostingService;
import io.nextree.travelclub.web.service.dto.BoardDto;
import io.nextree.travelclub.web.service.dto.MembershipDto;
import io.nextree.travelclub.web.service.dto.PostingDto;
import io.nextree.travelclub.web.store.BoardStore;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.MembershipStore;
import io.nextree.travelclub.web.store.PostingStore;
import io.nextree.travelclub.web.store.jpastore.jpo.id.MembershipId;
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
    private MembershipStore membershipStore;

    public PostingServiceLogic(BoardStore boardStore, PostingStore postingStore, MembershipStore membershipStore) {
        this.boardStore = boardStore;
        this.postingStore = postingStore;
        this.membershipStore = membershipStore;
    }

    @Override
    public String register(Long boardId, PostingDto postingDto) {
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board with id: " + boardId);
        }

        if (!membershipStore.exists(new MembershipId(boardId, postingDto.getWriterEmail()))) {
            throw new NoSuchMemberException("In the club, No such member with email: " + postingDto.getWriterEmail());
        }

        // posting update
        String postingId = Optional.ofNullable(boardStore.retrieve(boardId))
                .map(board -> postingStore.create(postingDto.toPostingIn(board)))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        // sequence update
        SocialBoard targetBoard = boardStore.retrieve(boardId);
        targetBoard.setSequence(targetBoard.getSequence() + 1);
        boardStore.update(targetBoard);

        return postingId;
    }

    @Override
    public PostingDto find(String postingId) {
        Posting foundPosting = Optional.ofNullable(postingStore.retrieve(postingId))
                .orElseThrow(() -> new NoSuchPostingException("No such posting whit id: " + postingId));

        foundPosting.setReadCount(foundPosting.getReadCount() + 1);

        return new PostingDto(foundPosting);
    }

    @Override
    public List<PostingDto> findByBoardId(Long boardId) {
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board whit id: " + boardId);
        }

        return postingStore.retrieveByBoardId(boardId).stream()
                .map(PostingDto::new).collect(Collectors.toList());
    }

    @Override
    public void modify(PostingDto postingDto) {
        String postingId = postingDto.getPostingId();
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
    public void removeAllIn(Long boardId) {
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board with id: " + boardId);
        }

        postingStore.deleteAllByBoardId(boardId);
    }
}
