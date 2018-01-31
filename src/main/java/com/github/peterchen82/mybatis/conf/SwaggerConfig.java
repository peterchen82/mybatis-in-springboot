package com.github.peterchen82.mybatis.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    Docket ProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .build()
                .apiInfo(productApiInfo());
    }

    private ApiInfo productApiInfo() {
        return new ApiInfo("mybatis-in-springboot",
                "mybatis-in-springboot接口文档",
                "1.0",
                "https://github.com/peterchen82",
                "peterchen82@vip.qq.com",
                "apache-2.0",
                "http://www.apache.org/licenses/");
    }
}
