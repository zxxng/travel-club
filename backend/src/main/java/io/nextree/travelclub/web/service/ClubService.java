package io.nextree.travelclub.web.service;

import io.nextree.travelclub.web.service.dto.MembershipDto;
import io.nextree.travelclub.web.service.dto.TravelClubDto;

import java.util.List;

public interface ClubService {
    void register(TravelClubDto clubDto);
    TravelClubDto findClub(String clubId);
    TravelClubDto findClubByName(String name);
    void modify(TravelClubDto clubDto);
    void remove(String clubId);
}
