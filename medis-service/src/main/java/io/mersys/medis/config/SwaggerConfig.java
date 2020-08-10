package io.mersys.medis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;
import springfox.documentation.service.Parameter;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private List<Parameter> listDocketParameters;

    public SwaggerConfig() {
        
        //Any parameter or header you want to require for all end_points
        Parameter oAuthHeader = new ParameterBuilder()
                                    .name("x-auth-token")
                                    .description("JWT Token")
                                    .defaultValue("Enter token here from /api/auth")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(false)
                                    .build();
    
        this.listDocketParameters = new ArrayList<Parameter>();
        this.listDocketParameters.add(oAuthHeader);
    }

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(listDocketParameters).select()
				.apis(RequestHandlerSelectors.basePackage("io.mersys.medis.web")).paths(regex("/api.*")).build();
	}
}
