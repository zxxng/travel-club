package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
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
public class MembershipJpo {
    @Id
    @GeneratedValue
    private Long id;

    private String clubId;
    private String memberEmail;

    private RoleInClub role;
    private String joinDate;

    public MembershipJpo(ClubMembership clubMembership) {
        BeanUtils.copyProperties(clubMembership, this);
    }

    public ClubMembership toDomain() {
        ClubMembership clubMembership = new ClubMembership(this.id, this.clubId, this.memberEmail);
        clubMembership.setRole(this.role);
        clubMembership.setJoinDate(this.joinDate);

        return clubMembership;
    }
}
