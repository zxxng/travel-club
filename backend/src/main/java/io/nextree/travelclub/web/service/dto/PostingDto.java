package io.nextree.travelclub.web.service.dto;

import io.nextree.travelclub.web.domain.board.Posting;
import io.nextree.travelclub.web.domain.board.SocialBoard;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostingDto {
    private String usid;
    private String title;
    private String writerEmail;
    private String contents;
    private String writtenDate;
    private int readCount;

    private PostingDto() {
        this.readCount = 0;
    }

    public PostingDto(String title, String writerEmail, String contents) {
        this();
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    public PostingDto(Posting posting) {
        this.usid = posting.getUsid();
        this.title = posting.getTitle();
        this.writerEmail = posting.getWriterEmail();
        this.contents = posting.getContents();
        this.writtenDate = posting.getWrittenDate();
        this.readCount = posting.getReadCount();
    }

    public Posting toPostingIn(SocialBoard board) {
        Posting posting = new Posting(board, title, writerEmail, contents);
        posting.setWrittenDate(writtenDate);
        posting.setReadCount(readCount);

        return posting;
    }

    public Posting toPostingIn(String postingId, String boardId) {
        Posting posting = new Posting(postingId, boardId, title, writerEmail, contents);
        posting.setWrittenDate(writtenDate);
        posting.setReadCount(readCount);

        return posting;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Posting id: " + usid);
        builder.append(", title: " + title);
        builder.append(", writer email: " + writerEmail);
        builder.append(", read count: " + readCount);
        builder.append(", written date: " + writtenDate);
        builder.append(", contents: " + contents);

        return builder.toString();
    }
}