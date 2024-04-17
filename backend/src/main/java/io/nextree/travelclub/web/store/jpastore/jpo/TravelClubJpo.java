package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.club.TravelClub;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="TRAVEL_CLUB")
public class TravelClubJpo {
    @Id
    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private String boardId;

    @OneToMany
    @JoinColumn(name = "clubId", referencedColumnName = "usid")
    private List<MembershipJpo> membershipList = new ArrayList<MembershipJpo>();

    public TravelClubJpo(TravelClub travelClub) {
        BeanUtils.copyProperties(travelClub, this);
    }

    public TravelClub toDomain() {
        TravelClub travelClub = new TravelClub(this.name, this.intro);
        travelClub.setUsid(this.usid);
        travelClub.setFoundationDay(this.foundationDay);

        for (MembershipJpo membershipJpo : this.membershipList) {
            travelClub.getMembershipList().add(membershipJpo.toDomain());
        }

        return travelClub;
    }
}
