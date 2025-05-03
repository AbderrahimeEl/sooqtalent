package net.elm.sooqtalent.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**", "/v3/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers("/api/users/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .requestMatchers("/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,   "/api/users/*/client-profiles").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/api/client-profiles/**", "/api/users/*/client-profile").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers(HttpMethod.PUT,    "/api/client-profiles/**").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/client-profiles/**").hasAnyRole("CLIENT","ADMIN")
                        // .requestMatchers(HttpMethod.GET, "/api/freelancer-profiles/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,   "/api/users/*/freelancer-profiles").hasAnyRole("FREELANCER","ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/api/users/*/freelancer-profile").hasAnyRole("FREELANCER","ADMIN")
                        .requestMatchers(HttpMethod.PUT,    "/api/freelancer-profiles/**").hasAnyRole("FREELANCER","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/freelancer-profiles/**").hasAnyRole("FREELANCER","ADMIN")

                        .requestMatchers(HttpMethod.GET,  "/api/projects/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/projects/clients/*").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/projects/*/status").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/projects/**").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers(HttpMethod.POST,   "/api/applications/freelancers/*").hasAnyRole("FREELANCER","ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/api/applications/freelancers/**").hasAnyRole("FREELANCER","ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/api/applications/projects/**").hasAnyRole("CLIENT","ADMIN")

                        .requestMatchers(HttpMethod.PATCH,  "/api/applications/*/status").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/api/applications/*").hasAnyRole("FREELANCER","CLIENT","ADMIN")

                        .requestMatchers(HttpMethod.GET,  "/api/skills").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/skills").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
