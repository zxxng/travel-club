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
    @JoinColumn(name = "clubId")
    private Long clubId;

    private String name;
    private String adminEmail;
    private String createDate;
    private int sequence;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="clubId", referencedColumnName = "clubId", updatable = false)
    private List<PostingJpo> postingList = new ArrayList<PostingJpo>();

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
