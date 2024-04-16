package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.vo.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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

//    private List<Address> addresses;
//    private List<ClubMembership> membershipList;

    public MemberJpo(CommunityMember member) {
        BeanUtils.copyProperties(member, this);
    }

    public CommunityMember toDomain() {
        CommunityMember member = new CommunityMember(this.email, this.name, this.phoneNumber);
        setNickName(this.nickName);
        setBirthDay(this.birthDay);

        return member;
    }
}
