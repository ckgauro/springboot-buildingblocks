package com.gauro.restservices.springbootbuildingblocks.restservices.config;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Chandra
 */

@Configuration
@EnableWebMvc
@EnableSwagger2
//@Component
//@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
     @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gauro.restservices"))
                .paths(PathSelectors.ant("/users/**"))
                .build()
                ;
    }
    //Swagger Metadata: http://localhost:8080/v2/api-docs
    //Swagger UI URL: http://localhost:8080/swagger-ui.html
    //http://localhost:8080/swagger-ui/index.html

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("StackSimplify User Management Service")
                .description("This page lists all API's of User Management")
                .version("2.0")
                .contact(new Contact("Chandra","https://chandra.com","chandra@gmail.com"))
                .license("License 2.0")
                .licenseUrl("https://abcd.com")
                .build();
    }


}
