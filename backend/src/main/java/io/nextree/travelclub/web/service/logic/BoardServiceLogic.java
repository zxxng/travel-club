package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.service.BoardService;
import io.nextree.travelclub.web.service.dto.BoardDto;
import io.nextree.travelclub.web.store.BoardStore;
import io.nextree.travelclub.web.store.ClubStore;
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

    public BoardServiceLogic(BoardStore boardStore, ClubStore clubStore) {
        this.boardStore = boardStore;
        this.clubStore = clubStore;
    }

    @Override
    public String register(BoardDto boardDto) {
        String boardId = boardDto.getClubId();

        Optional.ofNullable(boardStore.retrieve(boardId))
                .ifPresent(board -> {
                    throw new BoardDuplicationException("Board already exists in the club --> " + boardId);
                });

        TravelClub clubFound = Optional.ofNullable(clubStore.retrieve(boardId))
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + boardId));

        return Optional.ofNullable(clubFound.getMembershipBy(boardDto.getAdminEmail()))
                .map(adminEmail -> boardStore.create(boardDto.toBoard()))
                .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email: " + boardDto.getAdminEmail()));
    }

    @Override
    public BoardDto find(String boardId) {
        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> new NoSuchBoardException("No such board with id: " + boardId));
    }

    @Override
    public List<BoardDto> findByName(String boardName) {
        List<SocialBoard> boards = boardStore.retrieveByName(boardName);
        if (boards.isEmpty()) {
            throw new NoSuchBoardException("No such board with name: " + boardName);
        }

        return boards.stream()
                .map(board -> new BoardDto(board))
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto findByClubName(String clubName) {
        System.out.println(String.format("[DEBUG] service logic findByClubName, clubName: %s", clubName));
        System.out.println(String.format("[DEBUG] service logic findByClubName, clubStore.retrieveByName(clubName): %s", clubStore.retrieveByName(clubName)));
        return Optional.ofNullable(clubStore.retrieveByName(clubName))
                .map(club -> boardStore.retrieve(club.getId()))
                .map(board -> new BoardDto(board))
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
            Optional.ofNullable(clubStore.retrieve(boardDto.getClubId()))
                    .map(club -> club.getMembershipBy(boardDto.getAdminEmail()))
                    .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email: " + boardDto.getAdminEmail()));
        }

        boardStore.update(boardDto.toBoard());
    }

    @Override
    public void remove(String boardId) {
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }

        boardStore.delete(boardId);
    }
}
