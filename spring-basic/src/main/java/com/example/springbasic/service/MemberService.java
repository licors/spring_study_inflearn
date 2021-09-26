package com.example.springbasic.service;

import com.example.springbasic.domain.Member;
import com.example.springbasic.repository.MemberRepository;
import com.example.springbasic.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// service 에서는 비지니스 로직에 가까운 용어 선택
// 서비스는 주로 비지니스 로직 처리
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        // cmd + opt + v 로 자동변수 선언 가능
        // ctrl + t  refactory 여러 기능 호출
        // optional 에서 orElseGet() 이 많이 쓰임(있으면 리턴 없으면 뒤에 메소드 실행)
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                // isPresent() 와 혼동 주의
               .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
