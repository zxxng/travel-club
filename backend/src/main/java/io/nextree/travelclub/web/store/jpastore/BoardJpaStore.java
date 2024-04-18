package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.store.BoardStore;
import io.nextree.travelclub.web.store.jpastore.jpo.BoardJpo;
import io.nextree.travelclub.web.store.jpastore.repository.BoardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BoardJpaStore implements BoardStore {
    private BoardRepository boardRepository;

    public BoardJpaStore(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Long create(SocialBoard board) {
        boardRepository.save(new BoardJpo(board));

        return board.getClubId();
    }

    @Override
    public SocialBoard retrieve(Long boardId) {
        Optional<BoardJpo> boardJpo = boardRepository.findById(boardId);
        if (!boardJpo.isPresent()) {
            return null;
        }

        return boardJpo.get().toDomain();
    }

    @Override
    public List<SocialBoard> retrieveByName(String name) {
        List<BoardJpo> boardJpos = boardRepository.findAllByName(name);

        return boardJpos.stream().map(BoardJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(SocialBoard board) {
        boardRepository.save(new BoardJpo(board));
    }

    @Override
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public boolean exists(Long boardId) {
        return boardRepository.existsById(boardId);
    }
}
