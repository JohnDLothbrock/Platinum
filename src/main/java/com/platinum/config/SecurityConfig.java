/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platinum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Loa
 */

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(UserDetailsService userDetailsService,
                                                  PasswordEncoder passwordEncoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                // 1) Recursos estáticos
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

                // 2) Páginas públicas (todas las del menú)
                .requestMatchers(
                        "/", "/login", "/error/**",
                        "/agendar_cita",
                        "/cotizacion", "/galeria", "/testimonios", "/contacto",
                        "/membresia", "/promociones"
                ).permitAll()

                // 3) Público: enviar cita
                .requestMatchers(HttpMethod.POST, "/cita/guardar").permitAll()

                // 4) ADMIN: todo lo administrativo
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/usuario/**").hasRole("ADMIN")
                .requestMatchers("/servicio/**").hasRole("ADMIN")
                .requestMatchers("/membresia/**").hasRole("ADMIN")
                .requestMatchers("/promocion/**").hasRole("ADMIN")

                .requestMatchers("/cita/listado").hasRole("ADMIN")
                .requestMatchers("/cita/editar/**").hasRole("ADMIN")
                .requestMatchers("/cita/modificar/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/cita/actualizar").hasRole("ADMIN")
                .requestMatchers("/cita/eliminar/**").hasRole("ADMIN")

                // 5) Todo lo demás requiere login
                .anyRequest().authenticated()
        )

        .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
        )

        // Logout es POST /logout (por eso usamos form en el header)
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        return http.build();
    }
}

