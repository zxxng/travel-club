package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.vo.Address;
import io.nextree.travelclub.web.store.jpastore.jpo.converter.AddressConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="COMMUNITY_MEMBER")
public class MemberJpo {
    @Id
    private String email;

    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    @Convert(converter = AddressConverter.class)
    private List<Address> addresses;

    @OneToMany
    @JoinColumn(name = "memberEmail", referencedColumnName = "email")
    private List<MembershipJpo> membershipList;

    public MemberJpo(CommunityMember member) {
        BeanUtils.copyProperties(member, this);
    }

    public CommunityMember toDomain() {
        CommunityMember member = new CommunityMember(this.email, this.name, this.phoneNumber);
        member.setNickName(this.nickName);
        member.setBirthDay(this.birthDay);

        for (Address address : this.addresses) {
            member.getAddresses().add(address);
        }

        for (MembershipJpo membershipJpo : this.membershipList) {
            member.getMembershipList().add(membershipJpo.toDomain());
        }

        return member;
    }
}
