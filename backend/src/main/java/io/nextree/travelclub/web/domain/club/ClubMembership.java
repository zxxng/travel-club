package io.nextree.travelclub.web.domain.club;

import com.google.gson.Gson;
import io.nextree.travelclub.web.domain.club.vo.RoleInClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubMembership {
    private Long id;
    private String clubId;
    public String memberEmail;
    private RoleInClub role;
    private String joinDate;

    public ClubMembership(Long id, TravelClub club, CommunityMember member) {
        this.id = id;
        this.clubId = club.getUsid();
        this.memberEmail = member.getEmail();

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public ClubMembership(Long id, String clubId, String memberEmail) {
        this.id = id;
        this.clubId = clubId;
        this.memberEmail = memberEmail;

        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[Membership] Id: ").append(id);
        builder.append(", club Id: ").append(clubId);
        builder.append(", member email: ").append(memberEmail);
        builder.append(", role: ").append(role.name());
        builder.append(", join date: ").append(joinDate);

        return builder.toString();
    }

    public static ClubMembership getSample(TravelClub club, CommunityMember member) {
        ClubMembership membership = new ClubMembership(1L, club, member);
        membership.setRole(RoleInClub.Member);

        return membership;
    }

    public static void main(String[] args) {
        TravelClub club = TravelClub.getSample(true);
        CommunityMember member = CommunityMember.getSample();
        ClubMembership sample = ClubMembership.getSample(club, member);

        System.out.println(sample);

        System.out.println(new Gson().toJson(sample));
    }
}
