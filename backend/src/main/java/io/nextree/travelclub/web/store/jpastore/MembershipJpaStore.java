package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.club.ClubMembership;
import io.nextree.travelclub.web.store.MembershipStore;
import io.nextree.travelclub.web.store.jpastore.jpo.MembershipJpo;
import io.nextree.travelclub.web.store.jpastore.jpo.id.MembershipId;
import io.nextree.travelclub.web.store.jpastore.repository.MembershipRepository;
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

        return membership.getClubIdToString();
    }

    @Override
    public ClubMembership retrieveById(MembershipId membershipId) {
        Optional<MembershipJpo> membership = membershipRepository.findById(membershipId);
        if (!membership.isPresent()) {
            return null;
//            throw new NoSuchMembershipException("No such membership with membership id: " + membershipId);
        }

        return membership.get().toDomain();
    }

    @Override
    public List<ClubMembership> retrieveByClubId(Long clubId) {
        List<MembershipJpo> membershipJpos = membershipRepository.findAllByClubId(clubId);

        return membershipJpos.stream()
                .map(MembershipJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClubMembership> retrieveByMemberId(String memberId) {
        List<MembershipJpo> membershipJpos = membershipRepository.findAllByMemberEmail(memberId);

        return membershipJpos.stream()
                .map(MembershipJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ClubMembership membership) {
        membershipRepository.save(new MembershipJpo(membership));
    }

    @Override
    public void delete(MembershipId membershipId) {
        membershipRepository.deleteById(membershipId);
    }

    @Override
    public boolean exists(MembershipId membershipId) {
        return membershipRepository.existsById(membershipId);
    }
}
