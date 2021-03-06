package com.example.springbasic;

import com.example.springbasic.repository.JpaMemberRepository;
import com.example.springbasic.repository.*;
import com.example.springbasic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/*
    @Component 기타
    @Controller 외부 요청을 받음
    @Service 비지니스 로직 구현
    @Repository 데이터 저장
 */

@Configuration
public class SpringConfig {

//    private final MemberRepository memberRepository;

    // spring data jpa 에서 인터페이스 기반으로 쿼리문 등을 만들고 넘겨준다.
    // 또한 SpringDataJpaMemberRepository 를 빈으로 등록해준다.
//    public SpringConfig(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository);
//    }


    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    // 구현 시 어떤 DB 를 붙일지 못정한 경우
    // 이런 식으로 config 파일에 등록시켜 놓는다면 나중에 변경 시 편하다.
//    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
