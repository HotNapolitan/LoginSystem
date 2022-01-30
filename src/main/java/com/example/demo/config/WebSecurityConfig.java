package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.auth.DatabaseUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DatabaseUserDetailsService userDetailsService;

//	//Userの認証方式を決定するメソッド
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		//user情報をメモリ上で管理していること
//		auth.inMemoryAuthentication()
//		.withUser("user").password(passwordEncoder().encode("password")).roles("USER");
//	}
	
	//DatabaseUserDetailsServiceのインスタンスを設定する
	//ログインの機能をmysql＆mybatisを使っているようになる
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	//WEBアプリケーションが管理しているリソースへのアクセス制御の設定をするメソッド
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin();
//		.httpBasic();
	}
	
	//@Beanは依存性注入の管理対象になる
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
