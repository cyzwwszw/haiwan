//package com.lincomb.haiwan.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @Author: shylqian
// * @Description:
// * @Date: created on 下午1:38 17/10/21
// */
//@Configuration
//@EnableSwagger2
//public class Swagger2Conifg {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.lincomb.haiwan.controller.client"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("海湾工程 APIs")
//                .description("供联调使用")
//                .termsOfServiceUrl("http://baidu.com")
//                .contact("duke_qian")
//                .version("1.0")
//                .build();
//    }
//}http://test.xylbn.cn/haiwan/wechat/authorize?returnUrl=http://test.xylbn.cn/src/html/signIn.html

