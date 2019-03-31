package allst.boot.strap.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Vue调用后台数据的API Controller
 * @author June 2019/03/30 上午 10:20
 * @version 1.0
 */
@RestController
@RequestMapping("/vueApi")
public class AllstVueApiController {



    @GetMapping("/brand/lists")
    public List<Map<String, Object>> getBrandLists() {
        List<Map<String, Object>> list = null;
        return list;
    }
}
