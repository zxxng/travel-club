package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.board.Posting;
import io.nextree.travelclub.web.store.PostingStore;
import io.nextree.travelclub.web.store.jpastore.jpo.PostingJpo;
import io.nextree.travelclub.web.store.jpastore.repository.PostingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostingJpaStore implements PostingStore {
    private PostingRepository postingRepository;

    public PostingJpaStore(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    @Override
    public String create(Posting posting) {
        PostingJpo postingJpo = new PostingJpo(posting);
        postingRepository.save(postingJpo);

        return posting.getPostingId();
    }

    @Override
    public Posting retrieve(String postingId) {
        Optional<PostingJpo> postingJpo = postingRepository.findById(postingId);
        if (!postingJpo.isPresent()) {
            return null;
        }

        return postingJpo.get().toDomain();
    }

    @Override
    public List<Posting> retrieveByBoardId(Long boardId) {
        List<PostingJpo> postingJpos = postingRepository.findAllByBoardId(boardId);

        return postingJpos.stream().map(PostingJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Posting> retrieveByTitle(String title) {
        List<PostingJpo> postingJpos = postingRepository.findAllByTitle(title);

        return postingJpos.stream().map(PostingJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Posting posting) {
        postingRepository.save(new PostingJpo(posting));
    }

    @Override
    public void delete(String postingId) {
        postingRepository.deleteById(postingId);
    }

    @Override
    public void deleteAllByBoardId(Long boardId) {
        postingRepository.deleteAllByBoardId(boardId);
    }

    @Override
    public boolean exists(String postingId) {
        return postingRepository.existsById(postingId);
    }
}
