package io.nextree.travelclub.web.service.dto;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TravelClubDto {
    private Long id;
    private String name;
    private String intro;
    private String foundationDay;

    private List<MembershipDto> membershipDtoList;

    private TravelClubDto() {
        this.membershipDtoList = new ArrayList<MembershipDto>();
    }

    public TravelClubDto(String name, String intro) {
        this();
        this.name = name;
        this.intro = intro;
        this.foundationDay = DateUtil.today();
    }

    public TravelClubDto(TravelClub club) {
        this();
        this.id = club.getId();
        this.name = club.getName();
        this.intro = club.getIntro();
        this.foundationDay = club.getFoundationDay();

        for (ClubMembership membership : club.getMembershipList()) {
            this.membershipDtoList.add(new MembershipDto(membership));
        }
    }

    public TravelClub toTravelClub() {
        TravelClub travelClub = new TravelClub(name, intro);
        travelClub.setId(id);
        travelClub.setFoundationDay(foundationDay);

        for (MembershipDto membershipDto : membershipDtoList) {
            travelClub.getMembershipList().add(membershipDto.toMembership());
        }

        return travelClub;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("club Id: ").append(id);
        builder.append(", name: ").append(name);
        builder.append(", intro: ").append(intro);
        builder.append(", foundation day: ").append(foundationDay);
        builder.append("\n");

        int i = 0;
        for (MembershipDto membership : membershipDtoList) {
            builder.append(" ["+ i +"] Club's member: ").append(membership.toString()).append("\n");
            i++;
        }

        return builder.toString();
    }
}
