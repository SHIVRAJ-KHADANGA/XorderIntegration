package com.oretail.xorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.oretail.xorder.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}
    
	@Bean
	public ApiInfo metaData() {

		ApiInfo apiInfo = new ApiInfo(
				"Spring Boot REST API",
				"Spring Boot REST API for Xorders",
				"1.0",
				"Terms of service",
				new Contact("Shivraj Khadanga", "https://swagger.io/docs/specification/about/", "shivrajkhadanga@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0");

		return apiInfo;

	}

}
