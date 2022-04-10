package team.kyp.kypcoffee.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService UserService;

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**")
                .antMatchers("/resources/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
//                .and()
//                .formLogin()
//                .usernameParameter("id")
//                .passwordParameter("pw")
//                .authorizeRequests()
//                .antMatchers( "/signin/**","/admin/**","/register/**","/product/**","/qnaBoard/**").permitAll() // login URL에는 누구나 접근 가능
//                .anyRequest().authenticated() // 그 이외에는 인증된 사용자만 접근 가능
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .oauth2Login().loginPage("/signin")
                .failureUrl("/signin/loginFailure")
                .defaultSuccessUrl("/signin/googleLogin",true)
                .userInfoEndpoint()
                .userService(UserService);
    }
}