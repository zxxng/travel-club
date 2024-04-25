package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
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
@SequenceGenerator(name = "club_generator", sequenceName = "club_seq", initialValue = 1)
public class TravelClubJpo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_generator")
    private Long id;

    @Column(unique = true)
    private String name;

    private String intro;
    private String foundationDay;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clubId", referencedColumnName = "id")
    private List<MembershipJpo> membershipList;

    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private BoardJpo board;

    public TravelClubJpo(TravelClub travelClub) {
        BeanUtils.copyProperties(travelClub, this);

        if (this.foundationDay == null) {
            this.foundationDay = DateUtil.today();
        }
    }

    public TravelClub toDomain() {
        TravelClub travelClub = new TravelClub(this.name, this.intro);
        travelClub.setId(this.id);
        travelClub.setFoundationDay(this.foundationDay);

        for (MembershipJpo membershipJpo : this.membershipList) {
            travelClub.getMembershipList().add(membershipJpo.toDomain());
        }

        return travelClub;
    }
}
