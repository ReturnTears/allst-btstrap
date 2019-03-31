package allst.boot.strap.wshttpcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static allst.boot.strap.common.HttpRequestUtils.sendGet;

/**
 * 通过HTTP获取Weather信息
 * http://wthrcdn.etouch.cn/weather_mini?citykey=101020500
 * http://www.weather.com.cn/data/sk/101110101.html
 * 城市编码: 成都 - 101270101
 * @author June 2019/03/15 下午 11:13
 * @version 1.0
 */
@RestController
public class WeatherController {

    @GetMapping("/wea")
    public String getWeatherByHttp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "http://wthrcdn.etouch.cn/weather_mini?citykey=101020500";
        String weas = sendGet(url);
        JSONObject array = JSON.parseObject(weas);
        // 获取状态码
        String status = array.getString("status");
        // 获取消息
        String message = array.getString("message");
        // 获取当天日期信息
        JSONObject curr = array.getJSONObject("data").getJSONArray("forecast").getJSONObject(0);
        // 获取最高气温
        String high = curr.getString("high");
        // 获取最低气温
        String low = curr.getString("low");
        // 获取天气类型
        String type = curr.getString("type");

        // 将查询的结果包装成集合
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        map.put("high", high);
        map.put("low", low);
        map.put("type", type);
        JSONObject jsonObject = new JSONObject(map);

        System.out.println("status : " + status + " , message : " + message + " , high : " + high + " , low : " + low + " , type : " + type);
        return jsonObject.toJSONString();
    }
}
