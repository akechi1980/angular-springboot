package com.exp.demo.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class ApiDemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
	}

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     // 静态资源访问
    //     registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    // }

    // @Override
    // public void addViewControllers(ViewControllerRegistry registry) {
    //     // 当访问根路径时，跳转到index.html
    //     registry.addViewController("/").setViewName("forward:/index.html");
    // }


    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        // corsConfiguration.addAllowedOrigin("https://akechi1980-curly-tribble-pj9q6r7pjxrh6vx9-8080.preview.app.github.dev");
        corsConfiguration.addAllowedHeader("*");
        
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }



}
