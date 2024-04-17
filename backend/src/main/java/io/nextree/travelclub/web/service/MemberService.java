package io.nextree.travelclub.web.service;

import io.nextree.travelclub.web.service.dto.MembershipDto;
import io.nextree.travelclub.web.service.dto.MemberDto;
import io.nextree.travelclub.web.util.exception.InvalidEmailException;

import java.util.List;

public interface MemberService {
    void register(MemberDto memberDto) throws InvalidEmailException;
    MemberDto find(String memberId);
    List<MemberDto> findByName(String memberName);
    void modify(MemberDto memberDto) throws InvalidEmailException;
    void remove(String memberId);
}
