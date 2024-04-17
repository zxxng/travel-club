package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.service.MembershipService;
import io.nextree.travelclub.web.service.dto.MembershipDto;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.MemberStore;
import io.nextree.travelclub.web.store.MembershipStore;
import io.nextree.travelclub.web.util.exception.MemberDuplicationException;
import io.nextree.travelclub.web.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembershipServiceLogic implements MembershipService {
    private MemberStore memberStore;
    private MembershipStore membershipStore;

    public MembershipServiceLogic(MemberStore memberStore, MembershipStore membershipStore) {
        this.memberStore = memberStore;
        this.membershipStore = membershipStore;
    }

    @Override
    public void register(MembershipDto membershipDto) {
        // check validate memberId
        String memberId = membershipDto.getMemberEmail();
        Optional.ofNullable(memberStore.retrieve(memberId))
                .orElseThrow(() -> new NoSuchMemberException("No such member with email: " + memberId));

        // check memberId in club
        String clubId = membershipDto.getClubId();
        if (membershipStore.retrieveByClubIdAndMemberId(clubId, memberId) != null) {
            throw new MemberDuplicationException("Member already exists in the club --> " + memberId);
        }

        membershipStore.create(membershipDto.toMembership());
    }

    @Override
    public MembershipDto find(String membershipId) {
        return new MembershipDto(membershipStore.retrieve(membershipId));
    }

    @Override
    public MembershipDto findByClubIdAndMemberId(String clubId, String memberEmail) {
        ClubMembership foundedMembership = membershipStore.retrieveByClubIdAndMemberId(clubId, memberEmail);

        return new MembershipDto(foundedMembership);
    }

    @Override
    public List<MembershipDto> findAllByClubId(String clubId) {
        List<ClubMembership> memberships = membershipStore.retrieveByClubId(clubId);

        return memberships.stream()
                .map(membership -> new MembershipDto(membership))
                .collect(Collectors.toList());
    }

    @Override
    public List<MembershipDto> findAllByMemberId(String memberId) {
        List<ClubMembership> memberships = membershipStore.retrieveByMemberId(memberId);

        return memberships.stream()
                .map(membership -> new MembershipDto(membership))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(MembershipDto clubMembershipDto) {
        membershipStore.update(clubMembershipDto.toMembership());
    }

    @Override
    public void delete(String membershipId) {
        membershipStore.delete(membershipId);
    }
}
