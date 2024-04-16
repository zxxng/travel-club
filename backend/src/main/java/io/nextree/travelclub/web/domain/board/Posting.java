package io.nextree.travelclub.web.domain.board;

import io.nextree.travelclub.web.domain.Entity;
import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Posting implements Entity {
    private String usid;			// format - BoardId:00021
    private String title;
    private String writerEmail;		// member email
    private String contents;
    private String writtenDate;
    private int readCount;

    private String boardId;

    private Posting() {
        this.readCount = 0;
    }

    public Posting(SocialBoard board, String title, String writerEmail, String contents) {
        this();
        this.usid = board.nextPostingId();
        this.boardId = board.getId();
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    public Posting(String postingId, String boardId, String title, String writerEmail, String contents) {
        this.usid = postingId;
        this.boardId = boardId;
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[Posting] id: " + usid);
        builder.append(", title: " + title);
        builder.append(", writer email: " + writerEmail);
        builder.append(", read count: " + readCount);
        builder.append(", written date: " + writtenDate);
        builder.append(", contents: " + contents);
        builder.append(", board id: " + boardId);

        return builder.toString();
    }

    public static List<Posting> getSamples(SocialBoard board) {
        List<Posting> postings = new ArrayList<Posting>();

        CommunityMember leader = CommunityMember.getSample();
        Posting leaderPosting = new Posting(board, "The club intro", leader.getEmail(), "Hello, it's good to see you.");
        postings.add(leaderPosting);

        String postingUsid = board.nextPostingId();

        CommunityMember member = CommunityMember.getSample();
        Posting memberPosting = new Posting(board, "self intro", member.getEmail(), "Hello, My name is jena.");
        memberPosting.setUsid(postingUsid);
        postings.add(memberPosting);

        return postings;
    }

    @Override
    public String getId() {
        return usid;
    }

    public static void main(String[] args) {
        TravelClub club = TravelClub.getSample(true);
        SocialBoard board = SocialBoard.getSample(club);
        List<Posting> sample = Posting.getSamples(board);

        System.out.println(club);
        System.out.println(board);
        sample.forEach(System.out::println);
    }
}
