package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA EntityクラスたちがBaseTimeEntityを継承する場言、フィールドもコラムとして認識します
@EntityListeners(AuditingEntityListener.class)//BaseTimeEntityクラスにAuditing機能を含みます。
public class BaseTimeEntity {

    @CreatedDate //Entityが生成して保存するとき時間が自動保存します
    private LocalDateTime createdDate;

    @LastModifiedDate //照会したEntity値を変更するときに時間が自動的に保存されます。
    private LocalDateTime modifiedDate;
}
