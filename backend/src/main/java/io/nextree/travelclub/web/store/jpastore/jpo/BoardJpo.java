package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.board.SocialBoard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="SOCIAL_BOARD")
public class BoardJpo {
    @Id
    private Long clubId;

    @OneToOne
    @JoinColumn(name = "clubId", referencedColumnName = "id")
    private TravelClubJpo club;

    private int sequence;
    private String name;
    private String adminEmail;
    private String createDate;

    public BoardJpo(SocialBoard board) {
        BeanUtils.copyProperties(board, this);
    }

    public SocialBoard toDomain() {
        SocialBoard board = new SocialBoard(this.clubId, this.name, this.adminEmail);

        return board;
    }
}
