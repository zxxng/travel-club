package io.nextree.travelclub.web.service.dto;

import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.vo.Address;
import io.nextree.travelclub.web.util.exception.InvalidEmailException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberDto {
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private List<Address> addresses;
    private List<MembershipDto> membershipDtoList;

    private MemberDto() {
        this.addresses = new ArrayList<Address>();
        this.membershipDtoList = new ArrayList<MembershipDto>();
    }

    public MemberDto(String email, String name, String phoneNumber) {
        this();
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public MemberDto(CommunityMember member) {
        this(member.getEmail(), member.getName(), member.getPhoneNumber());
        this.nickName = member.getNickName();
        this.birthDay = member.getBirthDay();
        this.addresses = member.getAddresses();

        member.getMembershipList().forEach(membership -> {
            this.membershipDtoList.add(new MembershipDto(membership));
        });
    }

    public CommunityMember toMember() throws InvalidEmailException {
        CommunityMember member = new CommunityMember(email, name, phoneNumber);
        member.setNickName(nickName);
        member.setBirthDay(birthDay);

        for (Address address : addresses) {
            member.getAddresses().add(address);
        }

        for (MembershipDto membershipDto : membershipDtoList) {
            member.getMembershipList().add(membershipDto.toMembership());
        }

        return member;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("name: ").append(name);
        builder.append(", email: ").append(email);
        builder.append(", nickname: ").append(nickName);
        builder.append(", phone number: ").append(phoneNumber);
        builder.append(", birthDay: ").append(birthDay);
        builder.append("\n");


        if (addresses != null) {
            int i = 1;
            for(Address address : addresses) {
                builder.append(", Address[" + i + "]").append(address.toString());
            }
        }

        int i = 0;
        for (MembershipDto membership : membershipDtoList) {
            builder.append(" ["+ i +"] Club's member: ").append(membership.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }
}
