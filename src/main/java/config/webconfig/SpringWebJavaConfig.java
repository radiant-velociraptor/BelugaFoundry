package config.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author tmblount
 */
@Configuration
@ComponentScan(basePackages = {"web.controller, service.impl"})
@EnableWebMvc
public class SpringWebJavaConfig implements WebMvcConfigurer
{
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry)
    {
        // TODO
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver()
    {
        // TODO -- need to check on this. Ref ticket 708.

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer)
    {
        defaultServletHandlerConfigurer.enable();
    }
}
