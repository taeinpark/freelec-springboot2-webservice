package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //基本生成者自動生成
@Entity //テーブルとリンクするクラスってことを示します
public class Posts {

    @Id //該当テーブルのPKフィールドを示します
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PKの生成ルールを表示します
    //spring boot 2.0からGeneration Type.IDENTITYオプションを追加しないとauto_incrementされません。
    private Long id;

    @Column(length = 500, nullable = false)
    //テーブルのカラムを表示します。宣言しなくってもフィールドは全部カラムになります
    //基本値外に変更が必要なオプションがあれば使います
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //該当クラスのビルダーパターンクラス生成
    //生成者上段に宣言する時、生成者(constructor)に含まれたフィールドのみビルダーに含める
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
