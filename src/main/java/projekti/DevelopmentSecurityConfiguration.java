package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security profile to be used when no profile is set. For example when
 * developing on local machine. But beware, this profile is always used
 * if no profile is set. I'll repeat once more, SET PROFILE TO production
 * to use the SecureConfiguration.
 * @author danielko
 */
@Profile("default")
@Configuration
@EnableWebSecurity
public class DevelopmentSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //disable for h2-console
        //http.csrf().disable();
        
        //enable frames
        //http.headers().frameOptions().sameOrigin();
        
        http.authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/h2-console", "/h2-console/**").permitAll()
                    .antMatchers("/registration").permitAll()
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout().permitAll();
        
        http.formLogin()
                .loginPage("/login")
                //.defaultSuccessUrl("/")
                .permitAll().and()
                .logout().permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
