package cc.cmlx.system.config;

import cc.cmlx.system.properties.CmlxServerSystemProperties;
import cc.cmlx.system.properties.CmlxSwaggerProperties;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 16:57
 * @Desc ->
 **/
@Configuration
// 开启Swagger功能
@EnableSwagger2
public class CmlxWebConfigure {

    @Autowired
    private CmlxServerSystemProperties properties;

    /**
     * MyBatis Plus分页插件配置
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    @Bean
    public Docket swaggerApi() {
        CmlxSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 将路径下的所有Controller都添加进去
                .apis(RequestHandlerSelectors.basePackage("cc.cmlx.system.controller"))
                // 表示Controller里的所有方法都纳入
                .paths(PathSelectors.any())
                .build()
                // 定义一些API页面信息，比如作者名称，邮箱，网站链接，开源协议等等
                .apiInfo(apiInfo(swagger));
    }

    private ApiInfo apiInfo(CmlxSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }
    //    return new ApiInfo(
    //            "CMLX-Server-System API",
    //            "CMLX-Server-System API.",
    //            "1.0",
    //            null,
    //            new Contact("CMLX", "https://cmlx1218@163.com", "819952819@qq.com"),
    //            "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());
    //
    //}
}
