package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// 테스트진행시 JUnit에 내장된 실행자 외에 다른 실행자를 실행
// 여기서는 SpringRunner라는 스프링 실행자를 사용
// 즉, 스프링부트 테스트와 JUnit사이에 연결자 역할을 함
@RunWith(SpringRunner.class)

// 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언 시 @Controller, @ControllerAdvice는 사용 가능
// @Service, @Component, @Repository는 사용 불가
// 여기서는 컨트롤러만 사용하기 때문에 선언
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 Bean을 주입받음
    // 웹API 테스트 시 사용, 스프링 MVC테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST등에 대한 API테스트를 할 수 있음
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해서 /hello주소로 HTTP GET 요청함, 체이닝 지원, 아래와 같이 여러 검증기능을 이어서 선언 가능
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP Header의 status를 검증 (200, 404, 500등), 여기서는 200인지 아닌지 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증, Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 아닌지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        // param(): API테스트 시 사용될 요청 파라미터를 설정
        // 값은 String만 사용 가능

        // jsonPath: JSON응답값을 필드별로 검증할 수 있는 메소드
        // $을 기준으로 필드명을 암시
        // 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증
        mvc.perform(get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
