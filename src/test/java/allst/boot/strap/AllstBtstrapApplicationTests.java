package allst.boot.strap;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
// import com.alibaba.easyexcel.test.util.TestFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * https://blog.csdn.net/qq_35206261/article/details/88579151
 */
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

	@Test
	public void testLocalDate() {
		LocalDate local = LocalDate.now();
		System.out.println(local.toString() instanceof String);
	}

    @Test
    public void writeExcelOneSheetOnceWrite() throws IOException {

        // 生成EXCEL并指定输出路径
        OutputStream out = new FileOutputStream("E:\\TestData\\HelloExecl.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

        // 设置SHEET
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet1");

        // 设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<List<String>>();
        titles.add(Arrays.asList("用户ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Arrays.asList("年龄"));
        titles.add(Arrays.asList("生日"));
        table.setHead(titles);

        // 查询数据导出即可 比如说一次性总共查询出100条数据
        List<List<String>> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
        }

        writer.write0(userList, sheet, table);
        writer.finish();
    }

    @Test
    public void mergeWrite() {
        /*String fileName = TestFileUtil.getPath() + "mergeWrite" + System.currentTimeMillis() + ".xlsx";
        // 每隔2行会合并 把eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(loopMergeStrategy).sheet("模板").doWrite(data());*/
    }

}
