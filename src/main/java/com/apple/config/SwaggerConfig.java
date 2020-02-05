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
public class SwaggerConfig  extends WebMvcConfigurationSupport { 
	
	private ApiInfo apiInfo() {
	    Contact contact = new Contact("Rajashekharagouda Policepatil", "https://www.myhcl.com", "raj.policepatil2017@gmail.com");
	    return new ApiInfoBuilder()
	            .title("Spring Boot Rest API for Flag Picker.")
	            .description("Spring Boot Rest API to view the Continent,Country and Coutnry corresponding flag.")
	            .termsOfServiceUrl("https://github.com/ratpatil2020/FlagPicker")
	            .contact(contact)
	            .license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	            .version("1.0.0")
	            .build();
	}
		  
    @Bean 
    public Docket productApi() { 
        return new Docket(DocumentationType.SWAGGER_2) 
                .select() 
                .apis(RequestHandlerSelectors.basePackage("com.apple.controller.rest"))                       
                .paths(regex("/.*"))                
                .build() 
                .apiInfo(apiInfo()); 
        
       
    } 
    
    @Override 
    protected void addResourceHandlers(ResourceHandlerRegistry registry) { 
        registry.addResourceHandler("swagger-ui.html") 
                .addResourceLocations("classpath:/META-INF/resources/"); 
 
        registry.addResourceHandler("/webjars/**") 
                .addResourceLocations("classpath:/META-INF/resources/webjars/"); 
    } 
} 

