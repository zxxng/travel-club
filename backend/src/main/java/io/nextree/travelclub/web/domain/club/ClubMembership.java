package io.nextree.travelclub.web.domain.club;

import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubMembership {
    private String clubId;
    public String memberEmail;
    private RoleInClub role;
    private String joinDate;

    public ClubMembership(TravelClub club, CommunityMember member) {
        this.clubId = club.getUsid();
        this.memberEmail = member.getEmail();

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public ClubMembership(String clubId, String memberEmail) {
        this.clubId = clubId;
        this.memberEmail = memberEmail;

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[Membership] club Id: ").append(clubId);
        builder.append(", member email: ").append(memberEmail);
        builder.append(", role: ").append(role.name());
        builder.append(", join date: ").append(joinDate);

        return builder.toString();
    }

    public static ClubMembership getSample(TravelClub club, CommunityMember member) {
        ClubMembership membership = new ClubMembership(club, member);
        membership.setRole(RoleInClub.Member);

        return membership;
    }

    public static void main(String[] args) {
        TravelClub club = TravelClub.getSample(true);
        CommunityMember member = CommunityMember.getSample();
        ClubMembership sample = ClubMembership.getSample(club, member);

        System.out.println(sample);
    }
}
