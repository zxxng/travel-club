package io.nextree.travelclub.web.domain.board;

import io.nextree.travelclub.web.domain.Entity;
import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialBoard implements Entity {
    private Long clubId;

    private String name;
    private String adminEmail;
    private String createDate;
    private int sequence;

    public SocialBoard() {
        this.sequence = 1;
    }

    public SocialBoard(Long clubId, String name, String adminEmail) {
        this();
        this.clubId = clubId;
        this.name = name;
        this.adminEmail = adminEmail;
        this.createDate = DateUtil.today();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[SocialBoard] id: ").append(clubId);
        builder.append(", name: ").append(name);
        builder.append(", admin email: ").append(adminEmail);
        builder.append(", creation date: ").append(createDate);

        return builder.toString();
    }

    public static SocialBoard getSample(TravelClub club) {
        CommunityMember member = CommunityMember.getSample();

        SocialBoard board = new SocialBoard(
                club.getId(),
                "Board for " + club.getName(),
                member.getEmail()
        );

        board.setCreateDate("2024.04.15");

        return board;
    }

    @Override
    public String getId() {
        return clubId.toString();
    }

    public String nextPostingId() {
        return String.format("%d:%d", clubId, sequence);
    }

    public static void main(String[] args) {
        TravelClub club = TravelClub.getSample(true);
        SocialBoard sample = SocialBoard.getSample(club);

        System.out.println(club);
        System.out.println(sample);
    }
}
