package com.github.peterchen82.mybatis.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * Swagger配置
 *
 * @author peterchen
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    Docket productApi() {
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
        Contact contact = new Contact("peterchen", "https://github.com/peterchen82", "peterchen82@vip.qq.com");
        return new ApiInfo("mybatis-in-springboot",
                "mybatis-in-springboot接口文档",
                "1.0",
                "https://github.com/peterchen82",
                contact,
                "apache-2.0",
                "http://www.apache.org/licenses/",
                new ArrayList()
        );
    }
}
