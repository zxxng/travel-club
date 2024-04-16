package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.domain.club.TravelClub;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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
//    private List<ClubMembership> membershipList;

    public TravelClubJpo(TravelClub travelClub) {
        BeanUtils.copyProperties(travelClub, this);
    }

    public TravelClub toDomain() {
        TravelClub travelClub = new TravelClub(this.name, this.intro);
        travelClub.setUsid(this.usid);
        travelClub.setFoundationDay(this.foundationDay);

        return travelClub;
    }
}
