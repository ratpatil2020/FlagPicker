package com.apple.config;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry; 
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport; 
import springfox.documentation.builders.ApiInfoBuilder; 
import springfox.documentation.builders.RequestHandlerSelectors; 
import springfox.documentation.service.ApiInfo; 
import springfox.documentation.service.Contact; 
import springfox.documentation.spi.DocumentationType; 
import springfox.documentation.spring.web.plugins.Docket; 
import springfox.documentation.swagger2.annotations.EnableSwagger2; 
import static springfox.documentation.builders.PathSelectors.regex; 
 
@Configuration 
@EnableSwagger2 
public class SwaggerConfig extends WebMvcConfigurationSupport { 
    @Bean 
    public Docket productApi() { 
        return new Docket(DocumentationType.SWAGGER_2) 
                .select() 
                .apis(RequestHandlerSelectors.basePackage("com.apple.controller.rest"))
              //  .apis(RequestHandlerSelectors.basePackage("com.apple.controller.rest.metric"))               
                .paths(regex("/.*")) 
                //.paths(regex("/flagPicker/metricservice.*")) 
                .build() 
                .apiInfo(metaData()); 
        
       
    } 
    private ApiInfo metaData() { 
        return new ApiInfoBuilder() 
                .title("Spring Boot REST API") 
                .description("\"Spring Boot REST API for Flag Picker\"") 
                .version("1.0.0")               
                .contact(new Contact(" : Rajashekharagouda Policepatil", "", "raj.policepatil2014@gmail.com")) 
                .build(); 
    } 
    @Override 
    protected void addResourceHandlers(ResourceHandlerRegistry registry) { 
        registry.addResourceHandler("swagger-ui.html") 
                .addResourceLocations("classpath:/META-INF/resources/"); 
 
        registry.addResourceHandler("/webjars/**") 
                .addResourceLocations("classpath:/META-INF/resources/webjars/"); 
    } 
} 

