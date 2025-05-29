package com.javalord.welcome.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

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
                       .usernameParameter("email")
                       .passwordParameter("password")
                       .permitAll()
               )

               //.csrf(csrf -> csrf.e)
               .cors(cors -> cors.disable())

               .headers(headers -> headers.frameOptions(option -> option.disable()))
               .build();
   }
}
