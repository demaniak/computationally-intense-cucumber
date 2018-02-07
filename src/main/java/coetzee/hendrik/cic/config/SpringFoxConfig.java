package coetzee.hendrik.cic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import coetzee.hendrik.cic.rest.CicController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Configures springfox (swagger).
 *
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        CicController.class
    })
public class SpringFoxConfig {
    /**
     * 
     * @return configuration Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/cic.*")).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.contact(new springfox.documentation.service.Contact("HS Coetzee", "https://github.com/demaniak/", ""));
        builder.description("The Cool Cucumber. Sorry, nothing to do with BDD!");
        builder.version("1");

        return builder.build();
    }

}
