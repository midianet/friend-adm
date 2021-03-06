package midianet.friend.adm;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/resposta-tipo").setViewName("index");
        registry.addViewController("/resposta-tipo-form/**").setViewName("index");
        registry.addViewController("/resposta").setViewName("index");
        registry.addViewController("/resposta-form/**").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

}