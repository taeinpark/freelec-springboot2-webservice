package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //controllerをJSONを返還するコントローラーにする　
// @ResbonseBodyを各メソードに宣言したことを一回で使用出来るようにする
public class HelloController {

    @GetMapping("/hello")//HTTP MethodのGetの要請を受け入れられるAPIを作ってくれます
    public String hello(){
        return "hello";
    }

    @GetMapping("hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        //@RequestParamは外部からAPIに渡したパラメータを持ってくるアノテーションです
        return new HelloResponseDto(name, amount);
    }
}
