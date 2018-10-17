package com.example.base.core.swagger2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
public class Swagger2AutoConfiguration {

    @Value("${swagger2.base-package:com}")
    private String basePackage;
    @Value("${swagger2.title:api文档}")
    private String title;
    @Value("${swagger2.description:默认描述}")
    private String description;
    @Value("${swagger2.service-url:http://com.example.base}")
    private String url;
    @Value("${swagger2.version:0.0.1}")
    private String version;

    public Swagger2AutoConfiguration() {
        log.info("启用Swagger2功能");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(url)
                .version(version)
                .build();
    }

}

