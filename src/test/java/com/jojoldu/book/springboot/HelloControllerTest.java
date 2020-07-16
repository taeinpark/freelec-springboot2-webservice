

package com.jojoldu.book.springboot;

import com.jojoldu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //テストをする時jUnit内の実行者以外の実行者を実行します
//ここではSpringRunnerって言うスプリングの実行者を使います
//つまりspringboottestとJUnit間の連結の役割をします
@WebMvcTest(controllers = HelloController.class)//色んなspring アノテーションの中、Web(Spring MVC)に集中できるアノテーションです
//@Controller, "ControllerAdivce等をつかいますが@Service, @Component, @Repositoryは使用できません
public class HelloControllerTest {

    @Autowired//springが管理するbeanを注入されます
    private MockMvc mvc;//webAPIをテストする時使います
    //springMVCテストのスタート地点です。このクラスを通じてHTTP GET,POST等のAPIテストができます

    @Test
    public void helloがリターンされる() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))//MockMvcを通じて/hello住所にHTTP GET 要請する　
                .andExpect(status().isOk())//mvc.performの結果を検証しますHTTP HeaderのStatusを検証します 200,404,500等の状態を検証します
                .andExpect(content().string(hello));//同じくmvc.performの結果を検証します 回答本文の内容を検証します
    }

    @Test
    public void helloDtoがリターンされる() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name", is(name)))
         .andExpect(jsonPath("$.amount", is(amount)));


        /*
        *
        * param
        * APIテストする時使用される要請パラメータを設定します
        * Stringしか使用できない
        *
        * jsonPath
        * JSON応答値をフィールド別に検証できるメソッド
        * $を基準にフィールド名を明示します
        */

    }
}
