package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.board.SocialBoard;
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
@Table(name="SOCIAL_BOARD")
public class BoardJpo {
    @Id
    private Long clubId;

    @OneToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private TravelClubJpo club;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostingJpo> postingJpos = new ArrayList<PostingJpo>();

    private String name;
    private String adminEmail;
    private String createDate;
    private int sequence;

    public BoardJpo(SocialBoard board) {
        BeanUtils.copyProperties(board, this);

        if (this.createDate == null) {
            this.createDate = DateUtil.today();
        }
    }

    public SocialBoard toDomain() {
        SocialBoard board = new SocialBoard(this.clubId, this.name, this.adminEmail);
        board.setCreateDate(this.createDate);
        board.setSequence(this.sequence);

        return board;
    }
}
