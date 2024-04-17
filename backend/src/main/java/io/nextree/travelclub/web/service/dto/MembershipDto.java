package io.nextree.travelclub.web.service.dto;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipDto {
    private Long id;
    private String clubId;
    private String memberEmail;
    private String memberName;
    private RoleInClub role;
    private String joinDate;

    public MembershipDto(Long id, String clubId, String memberEmail) {
        this.id = id;
        this.clubId = clubId;
        this.memberEmail = memberEmail;

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public MembershipDto(ClubMembership membership) {
        this.id = membership.getId();
        this.clubId = membership.getClubId();
        this.memberEmail = membership.getMemberEmail();
        this.role = membership.getRole();
        this.joinDate = membership.getJoinDate();
    }

    public ClubMembership toMembership() {
        ClubMembership membership = new ClubMembership(id, clubId, memberEmail);
        membership.setRole(role);
        membership.setJoinDate(joinDate);

        return membership;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Id: ").append(clubId);
        builder.append(", club Id: ").append(clubId);
        builder.append(", member email: ").append(memberEmail);
        builder.append(", name: ").append(memberName);
        builder.append(", role: ").append(role.name());
        builder.append(", join date: ").append(joinDate);

        return builder.toString();
    }
}
