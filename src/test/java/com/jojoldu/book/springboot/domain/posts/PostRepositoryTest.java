package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //Junitで単位テストが終わるたびに実行されるメソッドを指定
    //全体テストする時テストの間データ侵犯を防ぐために使用します
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 掲示物の保存_読み込み(){
        //given
        String title = "テスト開示物";
        String content = "テスト本文";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());
        //テーブルpostsのinsert/updateクエリを実行します

        //when
        List<Posts> postsList = postsRepository.findAll();
        //postsテーブルにある全てのデータを照会するメソッドです

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntitiy_登録() {

        //given
        LocalDateTime now = LocalDateTime.of(2020,7,20,0,0,0);
        postsRepository.save(Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build());

        //when

        List<Posts> postsList = postsRepository.findAll();

        //then

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }

}
