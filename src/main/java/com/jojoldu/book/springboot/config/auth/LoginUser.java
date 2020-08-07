package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //このアノテーションが作成できる位置を指定
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //このファイルをアノテーションクレスに指定


}
