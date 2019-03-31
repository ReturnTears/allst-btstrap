package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * WeekDateTime Controller
 * @author June 2019/03/19 下午 08:33
 * @version 1.0
 */
@Controller
public class WeekDateTimeController {

    @GetMapping("/week")
    public String weekDateTime() {
        return "WeekDateTime";
    }

    @GetMapping("/wea1")
    public String weather1() {
        return "Weather1";
    }
}
