package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.jpastore.jpo.TravelClubJpo;
import io.nextree.travelclub.web.store.jpastore.repository.ClubRepository;
import io.nextree.travelclub.web.util.exception.NoSuchClubException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClubJpaStore implements ClubStore {
    private ClubRepository clubRepository;

    public ClubJpaStore(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Long create(TravelClub club) {
        TravelClubJpo clubJpo = new TravelClubJpo(club);
        clubJpo = clubRepository.save(clubJpo);

        return clubJpo.getId();
    }

    @Override
    public TravelClub retrieve(Long clubId) {
        Optional<TravelClubJpo> clubJpo = clubRepository.findById(clubId);
        if (!clubJpo.isPresent()) {
            throw new NoSuchClubException(String.format("TravelClub(%s) is not found.", clubId));
        }

        return clubJpo.get().toDomain();
    }

    @Override
    public TravelClub retrieveByName(String name) {
        // TODO: 반환 타입 확인 필요
        return  clubRepository.findAllByName(name).stream().findAny()
                .map(TravelClubJpo::toDomain).orElse(null);
    }

    @Override
    public void update(TravelClub club) {
        clubRepository.save(new TravelClubJpo(club));
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public boolean exists(Long clubId) {
        return clubRepository.existsById(clubId);
    }
}
