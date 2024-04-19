package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.board.Posting;
import io.nextree.travelclub.web.util.helper.DateUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="POSTING")
public class PostingJpo {
    @Id
    private String usid;			// format - 1:00001

    private String title;
    private String writerEmail;		// member email
    private String contents;
    private String writtenDate;
    private int readCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_clubId")
    private BoardJpo board;

    private Long boardId;

    public PostingJpo(Posting posting) {
        BeanUtils.copyProperties(posting, this);

        if (this.writerEmail == null) {
            this.writerEmail = DateUtil.today();
        }
    }

    public Posting toDomain() {
        Posting posting = new Posting(this.usid, this.board.getClubId(), this.title, this.writerEmail, this.contents);
        posting.setWrittenDate(this.writtenDate);
        posting.setReadCount(this.readCount);

        return posting;
    }
}
