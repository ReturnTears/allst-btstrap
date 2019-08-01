package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author June 2019/04/04 下午 08:03
 * @version 1.0
 */
@Controller
@RequestMapping("/echarts")
public class AllstVueEcharts {

    @RequestMapping("/1")
    public String vuecharts1() {
        return "echarts/Vuecharts1";
    }

    @RequestMapping("/ech")
    public String echartsPage() {
        return "echarts/all";
    }
}
