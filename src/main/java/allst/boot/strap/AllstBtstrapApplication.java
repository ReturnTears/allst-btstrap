package allst.boot.strap;

import com.fr.web.ReportServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * SpringBoot启动类
 * @author June
 * @version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "allst.boot.strap.mapper")
public class AllstBtstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllstBtstrapApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
        System.out.println("帆软 ReportServer 加载中.......");
        return new ServletRegistrationBean(new ReportServlet(), "/ReportServer");
    }
}
