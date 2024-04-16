package io.nextree.travelclub.web.store.jpastore;

import io.nextree.travelclub.web.domain.club.CommunityMember;
import io.nextree.travelclub.web.store.MemberStore;
import io.nextree.travelclub.web.store.jpastore.jpo.MemberJpo;
import io.nextree.travelclub.web.store.jpastore.repository.MemberRepository;
import org.springframework.stereotype.Repository;

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
            // TODO: 데이터베이스에 멤버가 없을 시 등록 안되는 문제 발생. 로직 검토 필요
//            throw new NoSuchMemberException(String.format("CommunityMember(%s) is not found.", email));
        }

        return memberJpo.get().toDomain();
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        List<MemberJpo> memberJpos = memberRepository.findAllByName(name);

        return memberJpos.stream().map(MemberJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(CommunityMember member) {
        memberRepository.save(new MemberJpo(member));
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
