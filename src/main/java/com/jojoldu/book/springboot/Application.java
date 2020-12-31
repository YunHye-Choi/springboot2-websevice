package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 이 어노테이션으로 인해 스프링부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
public class Application { // 앞으로 만들 프로젝트의 메인 클래스
    public static void main (String[] args) {
        // 내장 웹 어플리케이션 서버 (WAS) 실행, 톰캣 설치 불필요
        // 내장 WAS의 장점: 언제 어디서나 같은 환경에서 스프링부트를 배포할 수 있음
        SpringApplication.run(Application.class, args);
    }
}
