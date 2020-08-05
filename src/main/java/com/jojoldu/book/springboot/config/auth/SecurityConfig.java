package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity//Spring Security 設定を活性化させる
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()//h2-console画像を使うために該当オプションdisable
                .and()
                    .authorizeRequests() //URL各権限設定のオプションの初めです
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()//antMatchers 権限管理対象を指定します
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())//anyRequest 設定以外のURL
                    .anyRequest().authenticated()
                .and()
                    .logout() //Logout機能設定の初め
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() //oauth2機能設定の初め
                        .userInfoEndpoint() //Oauth2 ログイン成功後使用者の情報を持ってくる時の設定
                            .userService(customOAuth2UserService); //ソーシャルログイン成功時にフォローを行うUserServiceインタフェースの実装体を登録

    }
}
