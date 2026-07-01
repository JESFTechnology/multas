package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.details.PoliceDetails;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.PoliceRepository;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()
                .anyRequest().authenticated()
            )

            .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error")
                    .permitAll()
            )

            .logout(logout -> logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            );

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(PoliceRepository repository){

        return username -> repository.findByEmail(username)
                .map(PoliceDetails::new)
                .orElseThrow(() ->
                    new UsernameNotFoundException("Policial não encontrado.")
                );

    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}