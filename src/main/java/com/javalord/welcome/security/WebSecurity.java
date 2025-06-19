package com.javalord.welcome.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
    @Bean
    public UserDetailsService userDetailsService() {
        User user1 = new User(
                "tobi",
                "tobi",
                true,
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_MANAGER"))

        );

        User user2 = new User(
                "joseph",
                "joseph",
                true,
                true,
                true,
                true,
                List.of()
        );

        return new InMemoryUserDetailsManager(List.of(user1, user2));
    }

    */



   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       return http
               .authorizeHttpRequests(request -> request
                       .requestMatchers(HttpMethod.POST, "/create").permitAll()
                       .requestMatchers(HttpMethod.GET, "/create").permitAll()
                       .requestMatchers("/h2-console/**").permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin( login -> login
                       .loginPage("/login")
                       .defaultSuccessUrl("/")
                       .failureUrl("/login?isError")
                       .usernameParameter("email")
                       .passwordParameter("password")
                       .permitAll()
               )

               .csrf(csrf -> csrf.disable())
               .cors(cors -> cors.disable())

               .headers(headers -> headers.frameOptions(option -> option.disable()))
               .build();
   }
}
