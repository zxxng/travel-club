package io.nextree.travelclub.web.domain.club;

import com.google.gson.Gson;
import io.nextree.travelclub.web.domain.AutoIdEntity;
import io.nextree.travelclub.web.util.helper.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TravelClub implements AutoIdEntity {
    private static final int MINIMUM_NAME_LENGTH =  3;
    private static final int MINIMUM_INTRO_LENGTH =  10;
    public static final String ID_FORMAT = "%05d";

    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private String boardId;
    private List<ClubMembership> membershipList;

    private TravelClub() {
        this.membershipList = new ArrayList<ClubMembership>();
    }

    public TravelClub(String name, String intro) {
        this();
        this.setName(name);
        this.setIntro(intro);
        this.foundationDay = DateUtil.today();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[TravelClub] id: ").append(usid);
        builder.append(", name: ").append(name);
        builder.append(", intro: ").append(intro);
        builder.append(", foundation day: ").append(foundationDay);

        return builder.toString();
    }

    public static TravelClub getSample(boolean keyIncluded) {
        String name = "TravelClub";
        String intro = "Travel club to the Java island.";
        TravelClub club = new TravelClub(name, intro);

        if (keyIncluded) {
            int sequence = 21;
            club.setAutoId(String.format(ID_FORMAT, sequence));
        }

        return club;
    }

    @Override
    public String getId() {
        return usid;
    }

    @Override
    public String getIdFormat() {
        return ID_FORMAT;
    }

    @Override
    public void setAutoId(String autoId) {
        this.usid = autoId;
    }

    public ClubMembership getMembershipBy(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }

        for (ClubMembership clubMembership : this.membershipList) {
            if (email.equals(clubMembership.getMemberEmail())){
                return clubMembership;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TravelClub sample = TravelClub.getSample(true);
        System.out.println(sample);

        System.out.println(new Gson().toJson(sample));
    }
}
