package com.piatnitsa.security.config;

import com.piatnitsa.jwt.JWTFilter;
import com.piatnitsa.security.OAuthLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTFilter jwtFilter;
    private static final String ADMIN_ROLE = "ADMIN";
    private final AuthenticationEntryPoint unauthenticatedEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final OAuthLoginSuccessHandler oAuthLoginSuccessHandler;

    @Autowired
    public WebSecurityConfig(JWTFilter jwtFilter,
                             AuthenticationEntryPoint unauthenticatedEntryPoint,
                             AccessDeniedHandler accessDeniedHandler,
                             OAuthLoginSuccessHandler oAuthLoginSuccessHandler) {
        this.jwtFilter = jwtFilter;
        this.unauthenticatedEntryPoint = unauthenticatedEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.oAuthLoginSuccessHandler = oAuthLoginSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        //All
                        .antMatchers(HttpMethod.GET, "/certificates/**").permitAll()
                        .antMatchers("/auth", "/register").permitAll()
                        //User
                        .antMatchers(HttpMethod.POST, "/orders/**").authenticated()
                        .antMatchers(HttpMethod.GET, "/tags/**", "/users/**").authenticated()
                        //Admin
                        .anyRequest().hasRole(ADMIN_ROLE)
                .and()
                    .exceptionHandling()
                        .authenticationEntryPoint(unauthenticatedEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                .and()
                    .oauth2Client()
                .and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
