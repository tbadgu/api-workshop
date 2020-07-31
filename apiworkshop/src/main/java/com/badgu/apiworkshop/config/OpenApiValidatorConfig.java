package com.badgu.apiworkshop.config;

import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.io.IOException;

@Configuration
@EnableWebMvc
@Profile("!generateSpec")
public class OpenApiValidatorConfig implements WebMvcConfigurer {
    private final OpenApiValidationInterceptor validationInterceptor;

    public OpenApiValidatorConfig() throws IOException {
        final EncodedResource specResource = new EncodedResource(
                new ClassPathResource("openapi.json"),
                "UTF-8");
        this.validationInterceptor = new OpenApiValidationInterceptor(specResource);
    }

    @Bean
    public Filter validationFilter() {
        return new OpenApiValidationFilter(
                true,
                true
        );
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor);
    }
}
