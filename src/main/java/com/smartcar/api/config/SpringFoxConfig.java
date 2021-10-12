package com.smartcar.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, List.of(
                        new ResponseBuilder().code("500")
                            .description("Internal Server Error").build(),
                        new ResponseBuilder().code("400")
                                .description("Bad Request").build(),
                        new ResponseBuilder().code("404")
                                .description("Not Found").build()
                ))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.smartcar.api"))
                .paths(PathSelectors.any())
                .build();
    }
}
