package am.hitech.config;

import am.hitech.model.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    public void configure(HttpSecurity http) throws Exception {
        http
//                .antMatcher("/user/**")//close specified urls
                .httpBasic().and()//for basic authentication
                .csrf().disable()//some error cases
                .cors().disable()//for cors errors, ask for access to make request to other server(http method - OPTION)
                .headers().frameOptions().disable()//close frames from other websites
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//to save(STATELESS) session or not(ALWAYS)
                .and().authorizeRequests()
                .antMatchers("/user/create").permitAll()
//                .antMatchers(HttpMethod.GET, "/task/**","/role/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/task/**","/role/**").permitAll()//not secure urls
//                .antMatchers("/address/**").hasRole(Roles.HR.toString())
                .anyRequest().authenticated();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
