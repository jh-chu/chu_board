package org.jh.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jh.board.dto.MemberDto;
import org.jh.board.entity.Member;
import org.jh.board.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Long register(MemberDto dto) {

        Member createMember = Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .build();

        memberRepository.save(createMember);

        return createMember.getId();
    }

    @Override
    public void remove(Long id) {

        memberRepository.deleteById(id);

    }

    @Override
    public void modify(MemberDto dto) {

        Optional<Member> findMember = memberRepository.findById(dto.getId());

        if(findMember.isPresent()) {
            Member member = findMember.get();
            member.changeNickname(dto.getNickname());
            member.changePassword(dto.getPassword());
        }

    }

    @Override
    public MemberDto getMember(Long id) {

        Optional<Member> findMember = memberRepository.findById(id);

        if(findMember.isPresent())
            return findMember.map(MemberDto::new).get();

        else
            return new MemberDto();
    }

    @Override
    public boolean isExistsEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean isExistsNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public MemberDto logIn(String email, String password) {

        Optional<Member> findMember = memberRepository.findByEmailAndPassword(email, password);

        if(findMember.isPresent())
            return findMember.map(MemberDto::new).get();

        return null;
    }
}
