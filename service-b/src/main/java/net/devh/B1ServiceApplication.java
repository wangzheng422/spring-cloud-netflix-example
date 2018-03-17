package net.devh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.swagger.annotations.Api;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 2016/6/3
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class B1ServiceApplication {

    @Bean
    public Docket docket() {
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).select();
        apiSelectorBuilder.apis(withClassAnnotation(Api.class));
        return apiSelectorBuilder
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfo("Service B API Doc", "Service B API Doc", "1.0", "https://github.com/wangzheng422/spring-cloud-netflix-example",
                        new Contact("Geroge", "https://github.com/wangzheng422", "wangzheng422@gmail.com"), null, null))
                .forCodeGeneration(true);
    }

    public static void main(String[] args) {
        SpringApplication.run(B1ServiceApplication.class, args);
    }
}
