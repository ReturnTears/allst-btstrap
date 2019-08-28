package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 地图
 * @author JUNN
 * @since 2019/8/28 0028 上午 11:48
 */
@Controller
public class MapController {

    @GetMapping("map")
    public String mapInfo() {
        return "map/mapIndex";
    }
}
