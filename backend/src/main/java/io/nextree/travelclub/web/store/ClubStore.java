package io.nextree.travelclub.web.store;


import io.nextree.travelclub.web.domain.club.TravelClub;

public interface ClubStore {
    Long create(TravelClub club);
    TravelClub retrieve(Long clubId);
    TravelClub retrieveByName(String name);
    void update(TravelClub club);
    void delete(Long clubId);

    boolean exists(Long clubId);
}
