package io.nextree.travelclub.web.domain.club;

import com.google.gson.Gson;
import io.nextree.travelclub.web.domain.Entity;
import io.nextree.travelclub.web.domain.club.vo.Address;
import io.nextree.travelclub.web.util.exception.InvalidEmailException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommunityMember implements Entity {
    @Setter(AccessLevel.NONE)
    private String email;

    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;

    private List<Address> addresses;
    private List<ClubMembership> membershipList;

    private CommunityMember() {
        this.membershipList = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public CommunityMember(String email, String name, String phoneNumber) throws InvalidEmailException {
        this();
        setEmail(email);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[Member] name: ").append(name);
        builder.append(", email: ").append(email);
        builder.append(", nickname: ").append(nickName);
        builder.append(", phone number: ").append(phoneNumber);
        builder.append(", birthDay: ").append(birthDay);

        if (addresses != null) {
            int i = 1;
            for(Address address : addresses) {
                builder.append(", Address[" + i + "]").append(address.toString());
            }
        }

        return builder.toString();
    }

    public static CommunityMember getSample() {
        CommunityMember member = null;

        try {
            member = new CommunityMember("yoo@nextree.co.kr", "Jena", "010-1111-1111");
            member.setBirthDay("1998.07.26");
            member.getAddresses().add(Address.getHomeAddressSample());
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }

        return member;
    }

    @Override
    public String getId() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (!this.isValidEmailAddress(email)) {
            throw new InvalidEmailException("Email is not valid --> " + email);
        }

        this.email = email;
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        return m.matches();
    }

    public static void main(String[] args) {
        CommunityMember sample = CommunityMember.getSample();
        System.out.println(sample);

        System.out.println(new Gson().toJson(sample));
    }
}
