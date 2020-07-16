package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//宣言し全てのフィールドにgetメソード生成してくれます
@RequiredArgsConstructor//宣言した全てのfinalフィールドが含まれた生成者を生成してくれます
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
