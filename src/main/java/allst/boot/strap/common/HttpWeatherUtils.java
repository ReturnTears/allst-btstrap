package allst.boot.strap.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * 获取天气信息
 * @author June 2019/03/20 上午 11:32
 * @version 1.0
 */
public class HttpWeatherUtils {

    /**
     * 获取天气JSON信息
     */
    private static String getWeatherJsonInfo(String url_, String cityKey) throws UnsupportedEncodingException {
        if (url_.length() == 0) {
            url_ = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
        }
        String result = "";
        url_ += cityKey;
        ByteArrayOutputStream outputStream = null;
        try {
            URL url = new URL(url_);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/xhtml+xml,application/json,application/xml;charset=UTF-8, text/javascript, */*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
            conn.connect();
            GZIPInputStream gip = new GZIPInputStream(conn.getInputStream());
            outputStream = new ByteArrayOutputStream();
            // 缓冲
            byte[] bytes = new byte[1024];
            int len;
            while ((len = gip.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (new String(outputStream.toByteArray(), "UTF-8"));
    }

    /**
     * 天气接口 :
     * http://www.weather.com.cn/data/sk/101270101.html
     * @param url_      地址
     * @param cityKey   城市编码
     * @return
     */
    private static String getWeatherJsonInfos(String url_, String cityKey) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        BufferedReader in = null;
        String result = "";
        URL url = null;
        try {
            url = new URL(url_);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept", "application/xhtml+xml,application/json,application/xml;charset=UTF-8, text/javascript, */*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String key : map.keySet()) {
//                System.out.println(key + " --- " + map.get(key))
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    /**
     * 101270101"forecast":[{"date":"20日星期三","high":"高温 28℃","fengli":"<![CDATA[3-4级]]>","low":"低温 21℃","fengxiang":"南风","type":"阴"}
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String url_ = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
        String result = getWeatherJsonInfo(url_, "101270101");
        JSONObject object = JSON.parseObject(result);
        String city = object.getJSONObject("data").getString("city");
        Object array = object.getJSONObject("data").getJSONArray("forecast").get(0);
        JSONObject json = (JSONObject) JSONObject.toJSON(array);
        System.out.println(city + " > "  + array + " > " + json.getString("date"));
        // 分别解析各字段
        System.out.println("最高温度 : " + json.getString("high"));
        System.out.println("最低温度 : " + json.getString("low"));
        System.out.println("风力及风向 : " + json.getString("fengli") + " " + json.getString("fengxiang"));
        System.out.println("天气类型 : " + json.getString("type"));

        System.out.println("------------------分割线------------------------");
        String urls = "http://www.weather.com.cn/data/sk/101270101.html";
        String rs = getWeatherJsonInfos(urls, "");
        System.out.println(rs);
        JSONObject obj = JSON.parseObject(rs).getJSONObject("weatherinfo");

        // String info = obj.getString("weatherinfo")

        String cty = obj.getString("city");
        String ctyId = obj.getString("cityid");
        String wendu = obj.getString("temp");
        String fengli = obj.getString("WS");
        String fengxiang = obj.getString("WD");
        String shidu = obj.getString("WD");

        System.out.println("城市 " + cty + " 城市ID " + ctyId + " 温度 " + wendu + " 湿度 " + shidu + " 风向 " + fengxiang + fengli);
    }
}
