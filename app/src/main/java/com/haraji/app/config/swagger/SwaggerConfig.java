package com.haraji.app.config.swagger;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Configuration("Haraji-SwaggerConfig")
@EnableSwagger2
@PropertySource("classpath:application.yml")
@Conditional(SwaggerEnabledCondition.class)
@Slf4j
public class SwaggerConfig {

//    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private final BuildProperties buildProperties;

    @Value("${swagger.api.title}")
    private String apiTitle;

    @Value("${swagger.api.description}")
    private String apiDescription;

    @Value("${swagger.api.version}")
    private String apiVersion;

    @Value("${security.oauth2.token-uri}")
    private String TOKEN_URL;

//    @Autowired
//    public SwaggerConfig(BuildProperties buildProperties) {
//        this.buildProperties = buildProperties;
//    }

    @PostConstruct
    private void InitLog() {
        log.info("Swagger enabled");
    }

    @Bean
    public Docket customImplementation() {
        List<Parameter> parameterBuildersList = new ArrayList<Parameter>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder
                .name("trackerId")
                .parameterType("query")
                .description("Used for tracking requests by UUID String that provided by clients")
                .modelRef(new ModelRef("uuid"))
                .required(false);
        parameterBuildersList.add(parameterBuilder.build());

        ClientCredentialsGrant clientCredentialsGrant = new ClientCredentialsGrant(TOKEN_URL);

        AuthorizationScope[] scopes = {
                new AuthorizationScope("openid", "Getting access token")
        };

//        OAuthBuilder oAuthBuilder = new OAuthBuilder();
//        SecurityScheme oAuth = oAuthBuilder.name("auth.azadi.modern")
//                .grantTypes(Arrays.asList(clientCredentialsGrant))
//                .scopes(Arrays.asList(scopes))
//                .build();
//
//        SecurityContext keycloak = SecurityContext.builder()
//                .securityReferences(
//                        Arrays.asList(new SecurityReference("auth.azadi.modern", scopes))
//                )
//                .forPaths(PathSelectors.any())
//                .build();


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .globalOperationParameters(parameterBuildersList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sale"))
                .paths(PathSelectors.any())
                .build()
//                .directModelSubstitute(LocalDate.class, String.class)
//                .directModelSubstitute(LocalDateTime.class, String.class)
////                .securitySchemes(Arrays.asList(oAuth))
////                .securityContexts(Arrays.asList(keycloak))
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiTitle)
                .description(apiDescription)
//                .version(buildProperties != null ? buildProperties.getVersion() : null)
                .build();
    }


    @Bean
    @Profile("dev")
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}

