package com.improve.discountcalculator;

import com.google.common.collect.Collections2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.yaml.snakeyaml.resolver.Resolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableSwagger2
public class DiscountcalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscountcalculatorApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.improve.discountcalculator"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Discount Calculator",
				"Simple Java Spring APIs",
				"1.0",
				"MIT",
				new Contact("Alfred Nana Brown", "https://github.com/NanaAgyirBrown/DiscountCalculator", "nanabrown.agyir@gmail.com"),
				"API License",
				"",
				Collections.emptyList());
	}
}
