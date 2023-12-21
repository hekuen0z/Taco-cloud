package edu.taco_cloud.config;

import edu.taco_cloud.models.User;
import edu.taco_cloud.repositories.UserRepository;
import edu.taco_cloud.services.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfig {

    /**
     * Метод для создания компонента шифрования
     * @return - объект, применяющий шифрование bcrypt
     *
     * Можно также использовать:
     * NoOpPasswordEncoder - нет
     * Pbkdf2PasswordEncoder - PBKDF2
     * SCryptPasswordEncoder - Scrypt
     * StandartPasswordEncoder - SHA-256
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Компонент собственного UserDetailService, который выполняет
     * поиск в БД и возвращает действительного пользователя или
     * выбрасывает исключение.
     * @param userRepo
     * @return - действительный пользователь
     * @throws - UsernameNotFoundException, если такого username нет в БД
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if(user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found!");
        };
    }

    /**
     * Метод для конфигурации поведения Spring Security в отношении пользователя
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
        throws Exception {
        return http
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers("/design", "/orders").permitAll() //.hasRole("USER")
                                .requestMatchers("/", "/**").permitAll())
                .formLogin((form) ->
                        form
                                .loginPage("/login")
                                .permitAll())
                .build();
    }
}
