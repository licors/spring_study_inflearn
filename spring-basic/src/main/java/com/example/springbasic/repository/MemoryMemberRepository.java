package com.example.springbasic.repository;

import com.example.springbasic.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 공유되는 변수는 동시성 문제가 있어서 원래는 컨커런트해시맵
    // 써야하지만 여기서는 간단히 해시맵 사용
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 8L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store.get(id) 가 널이어도 옵셔널 객체 처리로 에러 안남
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // findAny() 처음 찾는 것 리턴, 없으면 null 옵셔널에 감싸서 리턴
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findByAll() {
        return new ArrayList<>(store.values());
    }
}
