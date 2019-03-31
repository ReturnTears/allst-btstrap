package allst.boot.strap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllstBtstrapApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	public void contextLoads() {
		String umengDevice_Token = "AtVXFK7Cip2in_ockE3waKCXBfXesX0UHTJCFbmufxqH";
		// 44位
		System.out.println(umengDevice_Token.length());
	}

	/**
	 * 测试druid的连接配置信息
	 */
	@Test
	public void currConnnectionTest() throws SQLException {
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}

}
