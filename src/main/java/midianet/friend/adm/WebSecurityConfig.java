package midianet.friend.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/app/**").authenticated()
            .antMatchers("/api/**").authenticated()
            .and()
          .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
          .logout()
            .permitAll();
    }

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/public/img/**")
                .addResourceLocations("/img");
        registry
                .addResourceHandler("/public/node_modules/**")
                .addResourceLocations("/node_mudules");

        registry.addResourceHandler("/public/js/**")
                .addResourceLocations("/js");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("admin").roles("ADMIN","USER")
        ;
    }
}
