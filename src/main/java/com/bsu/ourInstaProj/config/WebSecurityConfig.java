package com.bsu.ourInstaProj.config;


import com.bsu.ourInstaProj.entity.User;
import com.bsu.ourInstaProj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().disable();

        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    //Доступ только для не зарегистрированных пользователей
                    .antMatchers("/registration").not().fullyAuthenticated()
                    //Доступ разрешен всем пользователей
                    .antMatchers("/", "/resources/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/console/**").permitAll()//Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                    //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    //Перенарпавление на главную страницу после успешного входа
                .successHandler(successHandler())
                .failureUrl("/login?error")
                .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/")
        ;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }


    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                Authentication authentication) throws IOException {

                SecurityContext context = SecurityContextHolder.getContext();

                httpServletResponse.setStatus(200);

                httpServletResponse.getWriter().println(context.getAuthentication().getName());
                httpServletResponse.getWriter().println(context.getAuthentication().getAuthorities());

                String login = context.getAuthentication().getName();
                User user = userService.finUserByUsername(login);
                    user.setBoards(new ArrayList<>());


                System.out.println(user);
                httpServletResponse.getWriter().println(user);
                httpServletResponse.addHeader(context.getAuthentication().getName(), context.getAuthentication().getAuthorities().toString());
            }
        };
    }

    /*@Configuration
    @Order(1)
    public static class DisableSecurityConfigurationAdapater extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .antMatcher("/**").authorizeRequests().anyRequest().permitAll();
            http.headers().frameOptions().disable();
        }
    }*/
}
