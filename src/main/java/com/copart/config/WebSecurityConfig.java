package com.copart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Objects;

import static com.copart.constant.Constants.HASHED;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private AuthenticationEntryPoint jwtAuthenticationEntryPoint;
  @Autowired private UserDetailsService jwtUserDetailsService;
  @Autowired private JwtRequestFilter jwtRequestFilter;

  @Autowired
  private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // configure AuthenticationManager so that it knows from where to load
    // user for matching credentials
    // Use BCryptPasswordEncoder
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(final CharSequence charSequence) {
        return encode(charSequence.toString());
      }

      @Override
      public boolean matches(final CharSequence charSequence, final String str) {
        if (str.startsWith(HASHED)) {
          return Objects.equals(charSequence.toString(), encode(str.split(HASHED)[1]));
        }
        return Objects.equals(charSequence.toString(), str);
      }

      private String encode(String chars) {
        String str = "!@" + chars + "$%";

        int INIT_HASH = 7217;
        int LOOP_VAL = 39;
        int HASH_PRIME = 10000001;

        int result = INIT_HASH;
        for (int x = 0; x < str.length(); x++) {
          result = (result + str.charAt(x) * LOOP_VAL) % HASH_PRIME;
        }

        return "" + result;
      }
    };
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    // We don't need CSRF for this example
    httpSecurity
        .csrf()
        .disable() // dont authenticate this particular request
        .authorizeRequests()
        .antMatchers("/authenticate", "/ping")
        .permitAll()
        .anyRequest() // all other requests need to be authenticated
        .authenticated()
        .and()
        .exceptionHandling() // make sure we use stateless session; session won't be used to
        // store user's state.
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
