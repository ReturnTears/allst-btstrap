package allst.boot.strap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 后端解决Vue resource跨域访问报错：
 * Access to XMLHttpRequest at 'http://localhost:8081/vueApi/brand' from origin 'http://127.0.0.1:8081' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
 * @author June 2019/03/31 下午 12:42
 * @version 1.0
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(1728000);
    }
}
