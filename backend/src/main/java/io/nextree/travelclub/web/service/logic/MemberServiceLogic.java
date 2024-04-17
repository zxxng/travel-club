package io.nextree.travelclub.web.service.logic;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.service.MemberService;
import io.nextree.travelclub.web.service.dto.MembershipDto;
import io.nextree.travelclub.web.service.dto.MemberDto;
import io.nextree.travelclub.web.store.ClubStore;
import io.nextree.travelclub.web.store.MemberStore;
import io.nextree.travelclub.web.util.exception.InvalidEmailException;
import io.nextree.travelclub.web.util.exception.MemberDuplicationException;
import io.nextree.travelclub.web.util.exception.NoSuchMemberException;
import io.nextree.travelclub.web.util.helper.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceLogic implements MemberService {
    private MemberStore memberStore;

    public MemberServiceLogic(MemberStore memberStore) {
        this.memberStore = memberStore;
    }

    @Override
    public void register(MemberDto memberDto) throws InvalidEmailException {
        Optional.ofNullable(memberStore.retrieve(memberDto.getEmail()))
                .ifPresent(member -> {
                    throw new MemberDuplicationException("Member already exists with email: " + memberDto.getEmail());
                });

        memberStore.create(memberDto.toMember());
    }

    @Override
    public MemberDto find(String memberId) {
        return Optional.ofNullable(memberStore.retrieve(memberId))
                .map(member -> new MemberDto(member))
                .orElseThrow(() -> new NoSuchMemberException("No such member with email: " + memberId));
    }

    @Override
    public List<MemberDto> findByName(String memberName) {
        List<CommunityMember> members = memberStore.retrieveByName(memberName);
        if (members.isEmpty()) {
            throw new NoSuchMemberException("No such member with name: " + memberName);
        }

        return members.stream()
                .map(member -> new MemberDto(member))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(MemberDto memberDto) throws InvalidEmailException {
        CommunityMember targetMember = Optional.ofNullable(memberStore.retrieve(memberDto.getEmail()))
                .orElseThrow(() -> new NoSuchMemberException("No such member with email: " + memberDto.getEmail()));

        if (StringUtil.isEmpty(memberDto.getName())) {
            memberDto.setName(targetMember.getName());
        }
        if (StringUtil.isEmpty(memberDto.getNickName())) {
            memberDto.setNickName(targetMember.getNickName());
        }
        if (StringUtil.isEmpty(memberDto.getPhoneNumber())) {
            memberDto.setPhoneNumber(targetMember.getPhoneNumber());
        }
        if (StringUtil.isEmpty(memberDto.getBirthDay())) {
            memberDto.setBirthDay(targetMember.getBirthDay());
        }

        memberStore.update(memberDto.toMember());
    }

    @Override
    public void remove(String memberId) {
        if (!memberStore.exists(memberId)) {
            throw new NoSuchMemberException("No such member with id: " + memberId);
        }

        memberStore.delete(memberId);
    }
}
