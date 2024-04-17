package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.store.MembershipStore;
import io.nextree.travelclub.web.store.jpastore.jpo.MembershipJpo;
import io.nextree.travelclub.web.store.jpastore.repository.MembershipRepository;
import io.nextree.travelclub.web.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MembershipJpaStore implements MembershipStore {
    private MembershipRepository membershipRepository;

    public MembershipJpaStore(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public String create(ClubMembership membership) {
        membershipRepository.save(new MembershipJpo(membership));

        return membership.getClubId();
    }

    @Override
    public ClubMembership retrieve(String membershipId) {
        // TODO: membershipId test
        Optional<MembershipJpo> membershipJpo = membershipRepository.findById(membershipId);
        if (!membershipJpo.isPresent()) {
            throw new NoSuchMemberException(String.format("ClubMembership(%s) is not found.", membershipId));
        }

        return membershipJpo.get().toDomain();
    }

    @Override
    public ClubMembership retrieveByClubIdAndMemberId(String clubId, String memberId) {
        List<MembershipJpo> membershipJpos = membershipRepository.findAllByClubId(clubId);

        return membershipJpos.stream()
                .filter(membership -> membership.getMemberId().equals(memberId))
                .map(membership -> membership.toDomain())
                .findAny()
                .orElseThrow(() -> new NoSuchMemberException(String.format("CommunityMember(%s) is not found.", memberId)));
    }

    @Override
    public List<ClubMembership> retrieveByClubId(String clubId) {
        List<MembershipJpo> membershipJpos = membershipRepository.findAllByClubId(clubId);

        return membershipJpos.stream()
                .map(MembershipJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClubMembership> retrieveByMemberId(String memberId) {
        List<MembershipJpo> membershipJpos = membershipRepository.findAllByMemberId(memberId);

        return membershipJpos.stream()
                .map(MembershipJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ClubMembership membership) {
        membershipRepository.save(new MembershipJpo(membership));
    }

    @Override
    public void delete(String membershipId) {
        membershipRepository.deleteById(membershipId);
    }

    @Override
    public boolean exists(String membershipId) {
        return membershipRepository.existsById(membershipId);
    }
}
