package sistema.gestao.finaceira.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {    
    http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF
        .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/usuario/**").permitAll()
                .anyRequest().authenticated())
        .httpBasic(httpBasic -> httpBasic.disable()); // Use the new httpBasic(Customizer) method

    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) 
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService) // carrega o usuário
                   .passwordEncoder(passwordEncoder) // serve para codificar a senha
                   .and()
                     .build();

    }
}
