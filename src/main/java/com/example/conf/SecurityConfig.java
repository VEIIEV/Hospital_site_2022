package com.example.conf;


import com.example.Services.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//используется для работы аннотации @PreAuthorize(), её пример представлен в doctorController


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {




    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }




    //SecurityContext context = SecurityContextHolder.createEmptyContext();
    //        Authentication authentication= new TestingAuthenticationToken(" ", " ", " ");
    //        context.setAuthentication(authentication);
    //        SecurityContextHolder.setContext(context);


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**", "/hello", "/form","/verify", "/mailInfoPage","/registration", "/css/**","/MyCss.css", "/Navcss.css", "/js/**", "/images/**", "/static/**")
                .permitAll()
                .antMatchers("/hospital/create")
                .permitAll()
//                .antMatchers("/api/**")
//                .hasAuthority("DOCTOR")
//                .hasRole("DOCTOR")
                .anyRequest()
                .authenticated()
                .and()
//                .httpBasic()
//                .formLogin(withDefaults()) //предоставляет дефолтную форму для log in
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/hello", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                //если csrf токен не отключен, необходимо либо использовать пост запрос для лог аута
                // если хочется использовать гет, необходимо использоват ьстроку выше
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/hello");
        return http.build();
    }





    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return  new DataSourceProperties();
    }
    @Bean
    @Primary
    public DataSource dataSource() {
        return
                dataSourceProperties().initializeDataSourceBuilder().build();
    }

//    @Bean
//    UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(bCryptPasswordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }

//    @Bean
//    public UserDetailsManager users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.builder();
//        UserDetails user = users
//                .username("user")
//                .password(bCryptPasswordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        UserDetails admin = users
//                .username("admin")
//                .password(bCryptPasswordEncoder().encode("1234"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }



}
