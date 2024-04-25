package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
import io.nextree.travelclub.web.store.jpastore.jpo.id.MembershipId;
import io.nextree.travelclub.web.util.helper.DateUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="CLUB_MEMBERSHIP")
@IdClass(MembershipId.class)
public class MembershipJpo {
    @Id
    @JoinColumn(name="clubId")
    private Long clubId;

    @Id
    @JoinColumn(name="memberEmail")
    private String memberEmail;

    @Enumerated(EnumType.STRING)
    private RoleInClub role;

    private String joinDate;

    public MembershipJpo(ClubMembership clubMembership) {
        BeanUtils.copyProperties(clubMembership, this);

        if (this.joinDate == null) {
            this.joinDate = DateUtil.today();
        }
    }

    public ClubMembership toDomain() {
        ClubMembership clubMembership = new ClubMembership(this.clubId, this.memberEmail);
        clubMembership.setRole(this.role);
        clubMembership.setJoinDate(this.joinDate);

        return clubMembership;
    }
}
