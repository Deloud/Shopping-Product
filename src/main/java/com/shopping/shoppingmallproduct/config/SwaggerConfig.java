package com.shopping.shoppingmallproduct.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    private static final Contact DEFAULT_CONTACT = new Contact("JIWON KIM", "http://www.naver.com", "jiwon803@gmail.com");
//
//    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("AWESOME API Title", "Shoppingmall REST API","1.0","urn:tos",
//            DEFAULT_CONTACT,"Apache 2.0",
//            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
//
//    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
//            Arrays.asList("application/json", "application/xml"));
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(DEFAULT_API_INFO)
//                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
//    }
//}
 
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
//                .paths(PathSelectors.regex(API_V1_BASE_PATH + "/.*"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo getApiInfo() {
        StringVendorExtension vendorExtension1 = new StringVendorExtension("name1", "value1");
        StringVendorExtension vendorExtension2 = new StringVendorExtension("name2", "value2");
        List<VendorExtension> vendorExtensionList = new ArrayList<>();
        vendorExtensionList.add(vendorExtension1);
        vendorExtensionList.add(vendorExtension2);

        return new ApiInfo(
                "This is swagger title",
                "This is swagger description.",
                "1.0.0",
                "TERMS OF SERVICE URL",
                new Contact("name","url","mail address"),
                "Samsung SDS",
                "www.samsungsds.com",
                vendorExtensionList
        );
    }
}

//@SwaggerDefinition 어노테이션 말고 아래와 같은 방법으로 정의 할 수 있습니다.


