package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 해당 컨트롤러를 JSON을 반환하는 컨트롤러를 만들어줌
public class HelloController {

    // HTTP method인 Get의 요청을 받을 수 있는 API를 생성
    // 본 프로젝트는 아래코드를 통해 /hello로 요청이 오면 문자열 "hello"를 반하는 기능을 가지게 됨
    @GetMapping("/hello")
    public String hello () {
        return "hello";
    }

    @GetMapping("/hello/dto")
       public HelloResponseDto helloDto (@RequestParam("name") String name,
                                         @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);

        // @RequestParam: 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        // 여기서는 외부에서 name, amount로 넘긴 파라미터를 메소드 파라미터 String name, int amount에 넘겨줌줌
    }
}
