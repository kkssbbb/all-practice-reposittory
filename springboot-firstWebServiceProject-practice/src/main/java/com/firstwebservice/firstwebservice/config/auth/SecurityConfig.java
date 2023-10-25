package com.firstwebservice.firstwebservice.config.auth;


import com.firstwebservice.firstwebservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //springSecurity 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final  CustomOAuth2UserService costomOAuth2UserService;

    @Override
    protected  void configure(HttpSecurity http) throws  Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기위해 해당 옵션들을 disable 한다.
                .and()
                .authorizeRequests() //url별 관리를 설정하는 옵션의 시적점 authorizeRequests가 선어 되어야만 antMatchers 옵션 사용 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                //antMatchers : 관리대상 지정 옵션 "/"등 지정된 url들은 permitAll() 옵션을 통해 전체 연람 권함을 줌
                //"/api/v1/**"주소를 가진 api는 USER 권한을 가진 사람만  가능하도록 함
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() //anyRequest 설정된 값들 이외 나머지 URL들을 나타낸다. authenticcated()을 추가하여 나머지 URL들은 모드 인증된 사용자들에게만 허용 ,즉 로그인한 사용자들만 허용한다.
                .and()
                .logout().logoutSuccessUrl("/")                //    .logout().logoutSuccessUrl("/") : 로그아웃 기능에 대한 여러 설정의 진입점 , 로그아웃 선공시 / 주소로 이동
                .and()
                .oauth2Login()  //OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 떄의 설정들을 담당한다.
                .userService(costomOAuth2UserService);  //소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록 ,
                                                       // 리소스 서버(즉 ,소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능
    }
}
