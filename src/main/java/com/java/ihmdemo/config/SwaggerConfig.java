package com.java.ihmdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.java.ihmdemo"))
				.paths(regex("/advertiser.*"))
				.build()
				.apiInfo(metaData());
						
	}
	
	private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	    		.title("iHeart Media API")
	            .description("iHeart Media API for Advertiser")
	            .version("v1.0.0")
	            .termsOfServiceUrl("TERMS OF SERVICE URL")
	            .contact("Pravesh Jaiswal")
	            .license("LICENSE")
				.licenseUrl("www.iHeartMedia.com")
				.build();
	} 
}
