package allst.boot.strap.common;

import com.sun.deploy.net.HttpResponse;
import com.sun.deploy.net.HttpUtils;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Ali的天气预报API
 * @author June 2019/03/19 下午 09:46
 * @version 1.0
 */
public class AliWeatherApi {
    public static void main(String[] args) {
        String host = "http://freecityid.market.alicloudapi.com";
        String path = "/whapi/json/alicityweather/briefforecast3days";
        String method = "POST";
        String appcode = "a00df6a66b1d4d4282e66fc25aaade31";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("cityId", "2");
        bodys.put("token", "677282c2f1b3d718152c4e25ed434bc4");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
