package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // Getter메서드 자동 생성
@RequiredArgsConstructor // 모든 final 필드가 포함된 생성자를 생성
public class HelloResponseDto {
    private final String name;
    private final int amount; //
}
