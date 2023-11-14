package cn.chef.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource sources = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config  = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        sources.registerCorsConfiguration("/**",config);
        return new CorsWebFilter(sources);
    }
}
