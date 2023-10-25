package com.kkssbbb.blogs.config;

import com.kkssbbb.blogs.config.auth.PrincipalDetail;
import com.kkssbbb.blogs.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration// 빈등록: 스프링 컨테이너에서 객체를 관리할 수 있게 한다.
@EnableWebSecurity //시큐리// 티 필터설정파일 지정 : 스프링 시큐리티 설정을 이 파일(SecurityConfig)에서 하겠다는 의미.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 밎 인증을 미리 체크하겠다는 뜻.
//우 세개 어노테이션은 시큐리티 에서 세트 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
     private PrincipalDetailService principalDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean //Ioc
    public BCryptPasswordEncoder encoderPWD(){
        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할 수 있음


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable() //csrf토큰 비활성화 (테스트시 걸어두는게 좋음)
                .authorizeRequests()
                .antMatchers("/auth/**" ,"/js/**" ,"/css/**","/image/**","/","/dummy/**")
                .permitAll()//auth/이하는 누구나 들어올수 있다.
                .anyRequest() //다른요청은
                .authenticated() //인증이 되야한다.
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
           .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 로그인을 가로채서 대신 로그인 해줌
                .defaultSuccessUrl("/");


    }
}
