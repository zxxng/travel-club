package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.service.ClubService;
import io.nextree.travelclub.web.service.dto.TravelClubDto;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.util.exception.ClubDuplicationException;
import io.nextree.travelclub.web.util.exception.NoSuchClubException;
import io.nextree.travelclub.web.util.helper.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubServiceLogic implements ClubService {
    private ClubStore clubStore;
//    private MemberStore memberStore;

    public ClubServiceLogic(ClubStore clubStore) {
        this.clubStore = clubStore;
//        this.memberStore = ClubStoreMapLycler.getInstance().requestMemberStore();
    }

    @Override
    public void register(TravelClubDto clubDto) {
        Optional.ofNullable(clubStore.retrieveByName(clubDto.getName()))
                .ifPresent((clubName) -> {
                    throw new ClubDuplicationException("Club already exists with name: " + clubName);
                });

        String clubId = clubStore.create(clubDto.toTravelClub());
        clubDto.setUsid(clubId);
    }

    @Override
    public TravelClubDto findClub(String clubId) {
        return Optional.ofNullable(clubStore.retrieve(clubId))
                .map(club -> new TravelClubDto(club))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubId));
    }

    @Override
    public TravelClubDto findClubByName(String name) {
        return Optional.ofNullable(clubStore.retrieveByName(name))
                .map(club -> new TravelClubDto(club))
                .orElseThrow(() -> new NoSuchClubException("No such club with name: " + name));
    }

    @Override
    public void modify(TravelClubDto clubDto) {
        Optional.ofNullable(clubStore.retrieveByName(clubDto.getName()))
                .ifPresent(clubName -> {
                    throw new ClubDuplicationException("Club already exists with name: " + clubName);
                });

        TravelClub targetClub = Optional.ofNullable(clubStore.retrieve(clubDto.getUsid()))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubDto.getUsid()));

        if (StringUtil.isEmpty(clubDto.getName())) {
            clubDto.setName(targetClub.getName());
        }
        if (StringUtil.isEmpty(clubDto.getIntro())) {
            clubDto.setIntro(targetClub.getIntro());
        }

        clubStore.update(clubDto.toTravelClub());
    }

    @Override
    public void remove(String clubId) {
        if (!clubStore.exists(clubId)) {
            throw new NoSuchClubException("No such club with id: " + clubId);
        }

        TravelClub club = clubStore.retrieve(clubId);

//        List<ClubMembershipDto> membershipDtoList = findAllMembershipsIn(clubId);

        // modify logic
//        membershipDtoList.forEach(membershipDto -> {
//            removeMembership(clubId, membershipDto.getMemberEmail());
//        });

        clubStore.delete(clubId);
    }

    // TODO: mambership service logic 추가
}
