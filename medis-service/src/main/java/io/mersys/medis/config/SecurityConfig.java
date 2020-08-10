package io.mersys.medis.config;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.mersys.medis.security.filter.AuthenticationTokenFilter;
import io.mersys.medis.security.service.TokenAuthenticationService;
import io.mersys.medis.service.UserService;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationService tokenAuthenticationService;

    @Resource(name = "userService")
    private UserDetailsService  userDetailsService;
    
    @Autowired
    protected SecurityConfig(final TokenAuthenticationService tokenAuthenticationService) {
        super();
        this.tokenAuthenticationService = tokenAuthenticationService;
    }
    @Bean
    public SecurityConfiguration swaggerSecurityConfiguration() {
      return new SecurityConfiguration("client-id", "client-secret", "realm",
          "", "{{X-XSRF-COOKIE}}", ApiKeyVehicle.HEADER, "X-XSRF-TOKEN", ",");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
//        		.antMatchers("/**").permitAll()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated()                
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
//                .oauth2Login()
                ;
        
        

    }
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
