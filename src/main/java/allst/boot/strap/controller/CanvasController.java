package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Canvas Controller
 * @author JUNN
 * @since 2019/8/20 0020 上午 9:14
 */
@Controller
public class CanvasController {
    @GetMapping("/can1")
    public String allstVue1() {
        return "canvas/h5Canvas";
    }
}
