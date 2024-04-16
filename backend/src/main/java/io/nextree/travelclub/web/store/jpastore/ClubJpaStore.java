package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.jpastore.jpo.TravelClubJpo;
import io.nextree.travelclub.web.store.jpastore.repository.ClubRepository;
import io.nextree.travelclub.web.util.exception.NoSuchClubException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClubJpaStore implements ClubStore {
    private ClubRepository clubRepository;

    public ClubJpaStore(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public String create(TravelClub club) {
        clubRepository.save(new TravelClubJpo(club));

        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        Optional<TravelClubJpo> clubJpo = clubRepository.findById(clubId);
        if (!clubJpo.isPresent()) {
            throw new NoSuchClubException(String.format("TravelClub(%s) is not found.", clubId));
        }

        return clubJpo.get().toDomain();
    }

    @Override
    public TravelClub retrieveByName(String name) {
        List<TravelClubJpo> clubJpos = clubRepository.findAllByName(name);

        // TODO: 반환 타입 확인 필요
        return null;
    }

    @Override
    public void update(TravelClub club) {
        clubRepository.save(new TravelClubJpo(club));
    }

    @Override
    public void delete(String clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        return clubRepository.existsById(clubId);
    }
}
