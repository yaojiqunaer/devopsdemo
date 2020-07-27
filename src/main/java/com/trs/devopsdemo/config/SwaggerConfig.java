package com.trs.devopsdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description: swagger的配置类
 * @Author zhangxiaodong
 * @Date 2020/7/12 15:03
 * @Version V1.0
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Autowired
    private Environment environment;


//    @Bean
//    public Docket docket() {
//        // 设置要显示swagger的环境
//        Profiles profiles = Profiles.of("dev", "test");
//        // 判断当前是处于该环境，通过 enable() 接收此参数判断是否要显示
//        boolean b = environment.acceptsProfiles(profiles);
//        return new Docket(DocumentationType.SWAGGER_2)
//                .ignoredParameterTypes(HttpSession.class, HttpServletRequest.class)//忽略生成HttpSession的参数
//                .enable(b);
//    }

    //配置Docket以配置Swagger的具体参数
//    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))//带有RestController注解才生成接口
//                .build();
//    }


    //配置Docket以配置Swagger的具体参数
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.trs.devopsdemo.controller"))//basPackage代表包
                .build();
    }


    //配置Docket以配置Swagger的具体参数
//    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo());
//    }

//    private ApiInfo apiInfo() {
//        Contact contact = new Contact("张晓东", "www.aaa.com", "1907836146@qq.com");
//        return new ApiInfo("Swagegr接口标题",
//                "接口描述",
//                "v1.0",
//                "localhost:8080/devops",
//                contact,
//                "",
//                "",
//                new ArrayList<>()
//        );
//
//    }

}
