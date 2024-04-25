package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
import io.nextree.travelclub.web.service.MembershipService;
import io.nextree.travelclub.web.service.dto.MembershipDto;
import io.nextree.travelclub.web.store.MemberStore;
import io.nextree.travelclub.web.store.MembershipStore;
import io.nextree.travelclub.web.store.jpastore.jpo.id.MembershipId;
import io.nextree.travelclub.web.util.exception.InvalidMemberRoleException;
import io.nextree.travelclub.web.util.exception.MemberDuplicationException;
import io.nextree.travelclub.web.util.exception.NoSuchMemberException;
import io.nextree.travelclub.web.util.exception.NoSuchMembershipException;
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
        // memberId validation
        String memberId = membershipDto.getMemberEmail();
        Optional.ofNullable(memberStore.retrieve(memberId))
                .orElseThrow(() -> new NoSuchMemberException("No such member with email: " + memberId));

        // memberId validation in club
        MembershipId membershipId = toMembershipId(membershipDto.getClubId(), memberId);
        if (membershipStore.retrieveById(membershipId) != null) {
            throw new MemberDuplicationException("Member already exists in the club --> " + memberId);
        }

        // member role validation
        if (membershipDto.getRole().equals(RoleInClub.President) && hasPresident(membershipDto.getClubId())) {
            throw new InvalidMemberRoleException("President role already exists.");
        }

        membershipStore.create(membershipDto.toMembership());
    }

    @Override
    public MembershipDto findById(Long clubId, String memberEmail) {
        MembershipId membershipId = toMembershipId(clubId, memberEmail);
        ClubMembership foundedMembership = membershipStore.retrieveById(membershipId);

        return new MembershipDto(foundedMembership);
    }

    @Override
    public List<MembershipDto> findAllByClubIdOrMemberId(Long clubId, String memberId) {
        List<ClubMembership> memberships = null;

        if (clubId != null && memberId == null) {
            memberships = membershipStore.retrieveByClubId(clubId);
        } else if (clubId == null && memberId != null) {
            memberships = membershipStore.retrieveByMemberId(memberId);
        } else if (clubId != null && memberId != null) {
            memberships = membershipStore.retrieveByClubId(clubId).stream()
                    .filter(membership -> membership.getMemberEmail().equals(memberId))
                    .collect(Collectors.toList());
        } else {
            throw new NoSuchMembershipException("Both clubId and memberId cannot be null.");
        }

        return memberships.stream().map(MembershipDto::new).collect(Collectors.toList());
    }

    @Override
    public void modify(MembershipDto clubMembershipDto) {
        if (clubMembershipDto.getRole().equals(RoleInClub.President) && hasPresident(clubMembershipDto.getClubId())){
            throw new InvalidMemberRoleException("President role already exists.");
        }

        membershipStore.update(clubMembershipDto.toMembership());
    }

    @Override
    public void delete(Long clubId, String memberEmail) {
        membershipStore.delete(toMembershipId(clubId,memberEmail));
    }

    private MembershipId toMembershipId(Long clubId, String memberId) {
        return new MembershipId(clubId, memberId);
    }

    private boolean hasPresident(Long clubId){
        return membershipStore.retrieveByClubId(clubId).stream()
                .anyMatch(membership -> membership.getRole().equals(RoleInClub.President));
    }
}
