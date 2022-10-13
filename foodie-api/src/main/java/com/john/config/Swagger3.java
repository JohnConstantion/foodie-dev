package com.john.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author johnconstantine
 */
@Configuration
@EnableOpenApi
public class Swagger3 {
    //http://localhost:8088/swagger-ui/index.html#/ 接口路径

    /**
     * Swagger3
     *
     * @return 接口文档
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.john.controller")).paths(PathSelectors.any()).build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("接口文档").description("更多信息请咨询开发者mail").contact(new Contact("cui", "https://user.qzone.qq.com/575233534111/infocenter", "575233534111@qq.com")).version("1.0").termsOfServiceUrl("https://www.john.com").build();
    }
}
