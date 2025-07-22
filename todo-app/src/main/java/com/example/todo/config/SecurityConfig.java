package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // API için genellikle kapatılır
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // H2 konsolu için gerekli
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()  // H2 konsoluna izin ver
                .requestMatchers("/api/todos/**").authenticated()  // /api/todos/** endpoint'leri login ister
                .anyRequest().permitAll()  // diğerleri açık
            )
            .formLogin(Customizer.withDefaults());  // basit form login

        return http.build();
    }

    // Kullanıcıyı hafızada tutan UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails user = User.builder()
            .username("buse")                    // kullanıcı adı
            .password(encoder.encode("1234"))   // şifre (şifrelenmiş)
            .roles("USER")                      // rol
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
