package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.service.BoardService;
import io.nextree.travelclub.web.service.dto.BoardDto;
import io.nextree.travelclub.web.store.BoardStore;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.MembershipStore;
import io.nextree.travelclub.web.store.jpastore.jpo.id.MembershipId;
import io.nextree.travelclub.web.util.exception.BoardDuplicationException;
import io.nextree.travelclub.web.util.exception.NoSuchBoardException;
import io.nextree.travelclub.web.util.exception.NoSuchClubException;
import io.nextree.travelclub.web.util.exception.NoSuchMemberException;
import io.nextree.travelclub.web.util.helper.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceLogic implements BoardService {
    private BoardStore boardStore;
    private ClubStore clubStore;
    private MembershipStore membershipStore;

    public BoardServiceLogic(BoardStore boardStore, ClubStore clubStore, MembershipStore membershipStore) {
        this.boardStore = boardStore;
        this.clubStore = clubStore;
        this.membershipStore = membershipStore;
    }

    @Override
    public Long register(BoardDto boardDto) {
        Long boardId = boardDto.getClubId();

        // boardId validation
        Optional.ofNullable(boardStore.retrieve(boardId))
                .ifPresent(board -> {
                    throw new BoardDuplicationException("Board already exists in the club --> " + boardId);
                });

        // email validation
        validateAdminEmail(boardDto);

        return boardStore.create(boardDto.toBoard());
    }

    @Override
    public BoardDto find(Long boardId) {
        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(BoardDto::new)
                .orElseThrow(() -> new NoSuchBoardException("No such board with id: " + boardId));
    }

    @Override
    public List<BoardDto> findByName(String boardName) {
        List<SocialBoard> boards = boardStore.retrieveByName(boardName);
        if (boards.isEmpty()) {
            throw new NoSuchBoardException("No such board with name: " + boardName);
        }

        return boards.stream().map(BoardDto::new).collect(Collectors.toList());
    }

    @Override
    public BoardDto findByClubName(String clubName) {
        return Optional.ofNullable(clubStore.retrieveByName(clubName))
                .map(club -> boardStore.retrieve(club.getId()))
                .map(BoardDto::new)
                .orElseThrow(() -> new NoSuchClubException("No such club with name --> " + clubName));
    }

    @Override
    public void modify(BoardDto boardDto) {
        SocialBoard targetBoard = Optional.ofNullable(boardStore.retrieve(boardDto.getClubId()))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id: " + boardDto.getClubId()));

        if (StringUtil.isEmpty(boardDto.getName())) {
            boardDto.setName(targetBoard.getName());
        }
        if (StringUtil.isEmpty(boardDto.getAdminEmail())) {
            boardDto.setAdminEmail(targetBoard.getAdminEmail());
        } else {
            validateAdminEmail(boardDto);
        }

        boardStore.update(boardDto.toBoard());
    }

    @Override
    public void remove(Long boardId) {
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }

        boardStore.delete(boardId);
    }

    private void validateAdminEmail(BoardDto boardDto) {
        MembershipId membershipId = new MembershipId(boardDto.getClubId(), boardDto.getAdminEmail());
        Optional.ofNullable(membershipStore.retrieveById(membershipId))
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email: " + boardDto.getAdminEmail()));
    }
}
