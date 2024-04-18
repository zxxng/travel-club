package io.nextree.travelclub.web.service.dto;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipDto {
    private Long clubId;
    private String memberEmail;
    private RoleInClub role;
    private String joinDate;

    public MembershipDto(Long clubId, String memberEmail) {
        this.clubId = clubId;
        this.memberEmail = memberEmail;

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public MembershipDto(ClubMembership membership) {
        this.clubId = membership.getClubId();
        this.memberEmail = membership.getMemberEmail();
        this.role = membership.getRole();
        this.joinDate = membership.getJoinDate();
    }

    public ClubMembership toMembership() {
        ClubMembership membership = new ClubMembership(clubId, memberEmail);
        membership.setRole(role);
        membership.setJoinDate(joinDate);

        return membership;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Id: ").append(clubId);
        builder.append(", member email: ").append(memberEmail);
        builder.append(", role: ").append(role.name());
        builder.append(", join date: ").append(joinDate);

        return builder.toString();
    }
}
