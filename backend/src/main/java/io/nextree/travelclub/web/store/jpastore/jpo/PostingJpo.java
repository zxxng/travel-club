package io.nextree.travelclub.web.store.jpastore.jpo;

import io.nextree.travelclub.web.domain.board.Posting;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String usid;			// format - 00021:00001
    private String title;
    private String writerEmail;		// member email
    private String contents;
    private String writtenDate;
    private int readCount;

    private String boardId;

    public PostingJpo(Posting posting) {
        BeanUtils.copyProperties(posting, this);
    }

    public Posting toDomain() {
        Posting posting = new Posting(this.usid, this.boardId, this.title, this.writerEmail, this.contents);
        posting.setWrittenDate(this.writtenDate);
        posting.setReadCount(this.readCount);

        return posting;
    }
}
