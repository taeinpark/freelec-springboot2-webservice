package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postRepository;

    @After //Junitで単位テストが終わるたびに実行されるメソッドを指定
    //全体テストする時テストの間データ侵犯を防ぐために使用します
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 掲示物の保存_読み込み(){
        //given
        String title = "テスト開示物";
        String content = "テスト本文";

        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());
        //テーブルpostsのinsert/updateクエリを実行します

        //when
        List<Posts> postsList = postRepository.findAll();
        //postsテーブルにある全てのデータを照会するメソッドです

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
