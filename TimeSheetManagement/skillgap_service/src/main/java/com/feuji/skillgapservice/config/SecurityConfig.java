package com.feuji.skillgapservice.config;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.feuji.skillgapservice.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

//    @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/users/login").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/employee/**").hasAnyAuthority("**").anyRequest()
//                .authenticated().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
    
//    "/getBySkillId/{skillId}"
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/skill/getBySkillId/{skillId}",
                		"/skill/deleteSkill/{subSkillCategoryId}").permitAll()
                .and().authorizeHttpRequests()
                .requestMatchers("/skill/getAllForEmployee/{categoryId}","/skill/getAll/{categoryId}")
				.hasAnyAuthority("Software Engineer", "Manager","Admin")
				
				
				.requestMatchers("/skill/updateStatus")
				.hasAnyAuthority("Admin")
				
				.requestMatchers("/getRoles/{technicalCatId}",
						"/fetchBySkillId/{skillId}/{page}/{size}/{roleName}",
						"/skill/getSkillNames/{skillIds}",
						"/fetchallemployees/{skillIds}")
				.hasAnyAuthority("Manager")
				.anyRequest().authenticated()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       return http.csrf().disable()
//               .authorizeHttpRequests()
//               .requestMatchers("/products/signUp","/products/login","/products/refreshToken").permitAll()
//               .and()
//               .authorizeHttpRequests().requestMatchers("/products/all").hasAnyAuthority("ADMIN").requestMatchers("/products/{id}")
//				.hasAnyAuthority("USER").anyRequest()
//               .authenticated().and()
//               .sessionManagement()
//               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//               .and()
//               .authenticationProvider(authenticationProvider())
//               .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//               .build();
//   }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
