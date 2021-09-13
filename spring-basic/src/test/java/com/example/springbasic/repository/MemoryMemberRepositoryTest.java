package com.example.springbasic.repository;

import com.example.springbasic.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    // 테스트 하나 끝날 때마다 아래 코드가 실행되며 메모리 초기화
    @AfterEach
    public void afterEach() {
        // memory 비워야 다른 테스트 케이스에 영향이 없음
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        // 자동완성 작성 후 커맨드 + 쉬프트 + 엔터 하면 한 줄 내려옴
        Member result = repository.findById(member.getId()).get();
        // alt + 엔터 로 Assertions 를 import static 자동 등록 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        List<Member> result = repository.findAll();
    }
}
