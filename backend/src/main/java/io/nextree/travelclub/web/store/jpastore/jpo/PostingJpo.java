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
    @Column(unique = true)
    private String postingId;			// format - 1:1

    @JoinColumn(name = "clubId")
    private Long boardId;

    private String title;
    private String writerEmail;		// member email
    private String contents;
    private String writtenDate;
    private int readCount;

    public PostingJpo(Posting posting) {
        BeanUtils.copyProperties(posting, this);

        if (this.writtenDate == null) {
            this.writtenDate = DateUtil.today();
        }
    }

    public Posting toDomain() {
        Posting posting = new Posting(this.postingId, this.boardId, this.title, this.writerEmail, this.contents);
        posting.setWrittenDate(this.writtenDate);
        posting.setReadCount(this.readCount);

        return posting;
    }
}
