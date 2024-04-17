package io.nextree.travelclub.web.service.dto;

import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private String clubId;
    private String name;
    private String adminEmail;
    private String createDate;

    public BoardDto(String clubId, String name, String adminEmail) {
        this.clubId = clubId;
        this.name = name;
        this.adminEmail = adminEmail;
        this.createDate = DateUtil.today();
    }

    public BoardDto(SocialBoard socialBoard) {
        if (socialBoard == null) {
            return;
        }
        this.clubId = socialBoard.getClubId();
        this.name = socialBoard.getName();
        this.adminEmail = socialBoard.getAdminEmail();
        this.createDate = socialBoard.getCreateDate();
    }

    public SocialBoard toBoard() {
        SocialBoard socialBoard = new SocialBoard(clubId, name, adminEmail);
        socialBoard.setCreateDate(createDate);

        return socialBoard;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("SocialBoard id: ").append(clubId);
        builder.append(", name: ").append(name);
        builder.append(", admin email: ").append(adminEmail);
        builder.append(", creation date: ").append(createDate);

        return builder.toString();
    }
}
