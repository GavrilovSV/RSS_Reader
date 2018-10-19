package edu.gavrilov.configuration;

import edu.gavrilov.rss.NewsManager;
import edu.gavrilov.services.ChannelsManager;
import edu.gavrilov.services.FileChannelsManager;
import edu.gavrilov.services.FileUrlsManager;
import edu.gavrilov.services.UrlsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan(basePackages = "edu.gavrilov")
public class WebAppConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }


    @Bean
    public ViewResolver internalResourceViewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setSuffix(".jsp");
        viewResolver.setPrefix("/WEB-INF/views/");
        return viewResolver;

    }


    @Bean
    public UserDetailsService getUserDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        return manager;
    }


    @Bean("fileUrlsManager")
    public UrlsManager fileUrlsManager() {
        return new FileUrlsManager();
    }


    @Bean
    public NewsManager newsManager() {
        return new NewsManager(fileUrlsManager());
    }


    @Bean
    @DependsOn(value = {"fileUrlsManager"})
    public ChannelsManager fileChannelsManager() {
        FileChannelsManager channelsManager = new FileChannelsManager(fileUrlsManager());
        return channelsManager;
    }

}

