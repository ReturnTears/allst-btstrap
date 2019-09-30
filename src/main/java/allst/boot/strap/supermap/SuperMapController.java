package allst.boot.strap.supermap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YiYa
 * @since 2019/9/30
 */
@Controller
@RequestMapping("super")
public class SuperMapController {

    @RequestMapping("map-one")
    public String superMapOne() {
        return "map/SuperMap-One";
    }

}
