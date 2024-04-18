package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
import io.nextree.travelclub.web.service.ClubService;
import io.nextree.travelclub.web.service.MembershipService;
import io.nextree.travelclub.web.service.dto.MembershipDto;
import io.nextree.travelclub.web.service.dto.TravelClubDto;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.MemberStore;
import io.nextree.travelclub.web.util.exception.ClubDuplicationException;
import io.nextree.travelclub.web.util.exception.MemberDuplicationException;
import io.nextree.travelclub.web.util.exception.NoSuchClubException;
import io.nextree.travelclub.web.util.exception.NoSuchMemberException;
import io.nextree.travelclub.web.util.helper.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubServiceLogic implements ClubService {
    private ClubStore clubStore;

    public ClubServiceLogic(ClubStore clubStore) {
        this.clubStore = clubStore;
    }

    @Override
    public void register(TravelClubDto clubDto) {
        Optional.ofNullable(clubStore.retrieveByName(clubDto.getName()))
                .ifPresent((clubName) -> {
                    throw new ClubDuplicationException("Club already exists with name: " + clubName);
                });

        Long clubId = clubStore.create(clubDto.toTravelClub());
        clubDto.setId(clubId);
    }

    @Override
    public TravelClubDto findClub(Long clubId) {
        return Optional.ofNullable(clubStore.retrieve(clubId))
                .map(TravelClubDto::new)
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubId));
    }

    @Override
    public TravelClubDto findClubByName(String name) {
        return Optional.ofNullable(clubStore.retrieveByName(name))
                .map(TravelClubDto::new)
                .orElseThrow(() -> new NoSuchClubException("No such club with name: " + name));
    }

    @Override
    public void modify(TravelClubDto clubDto) {
        Optional.ofNullable(clubStore.retrieveByName(clubDto.getName()))
                .ifPresent(clubName -> {
                    throw new ClubDuplicationException("Club already exists with name: " + clubName);
                });

        TravelClub targetClub = Optional.ofNullable(clubStore.retrieve(clubDto.getId()))
                .orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubDto.getId()));

        if (StringUtil.isEmpty(clubDto.getName())) {
            clubDto.setName(targetClub.getName());
        }
        if (StringUtil.isEmpty(clubDto.getIntro())) {
            clubDto.setIntro(targetClub.getIntro());
        }

        clubStore.update(clubDto.toTravelClub());
    }

    @Override
    public void remove(Long clubId) {
        if (!clubStore.exists(clubId)) {
            throw new NoSuchClubException("No such club with id: " + clubId);
        }

        clubStore.delete(clubId);
    }
}
