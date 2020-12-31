package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        // assertj라는 테스트 검증 라이브러리의검증 메소드
        // 검증하고 싶은 대상을 메소드 인자로 받음
        // 메소드 테이닝이 지원되어 isEqualTo오 ㅏ같이 메소드를 이어서 사용 가능
        assertThat(dto.getName()).isEqualTo(name); // assertj의 비교 메소드, assertThat에 있는 값과 같은지 검증
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}