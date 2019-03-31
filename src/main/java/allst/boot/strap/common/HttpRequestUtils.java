package allst.boot.strap.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Http请求工具类
 * @author June 2019/03/15 下午 11:21
 * @version 1.0
 */
public class HttpRequestUtils {

    /**
     * http://wthrcdn.etouch.cn/weather_mini?city=嘉定
     * http://wthrcdn.etouch.cn/weather_mini?citykey=101020500
     * 发送请求获取天气JSON信息
     * @param url_ http地址
     * @return
     */
    public static String sendGet(String url_) {
        String result = "";
        BufferedReader in = null;
        try {
            URL url = new URL(url_);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + " --- " + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送Get请求异常!" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String url = "http://wthrcdn.etouch.cn/weather_mini?citykey=101270101";
        String url_ = "http://www.weather.com.cn/data/sk/101270101.html";
        String rs = sendGet(url_);
        System.out.println(rs);



//        JSONObject array = JSON.parseObject(sendGet(url));
//        // 获取状态码
//        String status = array.getString("status");
//        // 获取消息
//        String message = array.getString("message");
//        // 获取当天日期信息
//        JSONObject curr = array.getJSONObject("data").getJSONArray("forecast").getJSONObject(0);
//        // 获取最高气温
//        String high = curr.getString("high");
//        // 获取最低气温
//        String low = curr.getString("low");
//        // 获取天气类型
//        String type = curr.getString("type");
//        System.out.println("status : " + status + " , message : " + message + " , high : " + high + " , low : " + low + " , type : " + type);
//        // 将需要的信息包装成JSON字符串
//        Map<String, Object> map = new HashMap<>();
//        map.put("status", status);
//        map.put("message", message);
//        map.put("high", high);
//        map.put("low", low);
//        map.put("type", type);
//        JSONObject jsonObject = new JSONObject(map);
//        System.out.println(jsonObject.toJSONString());
    }
}
