package com.sale.app;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

@SpringBootApplication(scanBasePackages = {"com.sale.common","com.sale.app","com.sale.baseinfo","com.sale.baseinfo.mapper","com.sale.baseinfo.service", "com.sale.business"})
@EnableJpaRepositories(basePackages= {"com.sale.baseinfo.database.repository","com.sale.business.database.repository"})
@EntityScan(basePackages= {"com.sale.baseinfo.database.entity","com.sale.business.database.entity"})
@ConfigurationPropertiesScan(basePackages = {"com.sale.app", "com.sale.baseinfo", "com.sale.business"})
@Slf4j
public class ApplicationRunner {

    @Value("${swagger.api.title}")
    private String apiTitle;

    @Value("${swagger.api.description}")
    private String apiDescription;

//    protected static final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {

        log.info("Application Starting...");

        SpringApplication application = new SpringApplication(ApplicationRunner.class);
        ConfigurableApplicationContext ctx = application.run(args);
        ctx.registerShutdownHook();

        log.info("Application Started");
    }

//    @Bean
//    public Docket productApi() {
//        logger.info("swagger Started");
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//
//                .apis(RequestHandlerSelectors.basePackage("com.sale")).build();
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiTitle)
                .description(apiDescription)
//                .version(buildProperties != null ? buildProperties.getVersion() : null)
                .build();
    }
}
