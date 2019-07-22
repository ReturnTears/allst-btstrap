package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther Junn
 * @Date 2019/7/22 0022下午 18:03
 */
@Controller
public class JsSinceJavaController {

    @GetMapping("/js")
    public String jsSinceJava() {
        return "jsTemp/jsSinceJava";
    }
}
