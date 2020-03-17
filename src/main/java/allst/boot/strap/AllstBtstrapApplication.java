package allst.boot.strap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
