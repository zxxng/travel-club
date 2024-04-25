package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.store.MemberStore;
import io.nextree.travelclub.web.store.jpastore.jpo.MemberJpo;
import io.nextree.travelclub.web.store.jpastore.repository.MemberRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberJpaStore implements MemberStore {
    private MemberRepository memberRepository;

    public MemberJpaStore(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String create(CommunityMember member) {
        memberRepository.save(new MemberJpo(member));

        return member.getId();
    }

    @Override
    public CommunityMember retrieve(String email) {
        Optional<MemberJpo> memberJpo = memberRepository.findById(email);
        if (!memberJpo.isPresent()) {
            return null;
        }

        return memberJpo.get().toDomain();
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        List<MemberJpo> memberJpos = memberRepository.findAllByName(name);

        return memberJpos.stream().map(MemberJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(CommunityMember member) {
        memberRepository.updateMemberInfoById(member.getEmail(), member.getName(), member.getNickName(), member.getPhoneNumber(), member.getBirthDay());
    }

    @Override
    public void delete(String email) {
        memberRepository.deleteById(email);
    }

    @Override
    public boolean exists(String email) {
        return memberRepository.existsById(email);
    }
}
