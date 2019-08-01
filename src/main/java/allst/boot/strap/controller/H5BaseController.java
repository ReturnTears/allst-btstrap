package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author JUNN
 * @since 2019/8/1 0001 下午 18:08
 */
@Controller
@RequestMapping("base")
public class H5BaseController {

    @RequestMapping(value = "h5/{val}", method= RequestMethod.GET)
    public String h5base(@PathVariable("val") String val) {
        if ("1".equals(val)) {
            return "h5/h5base";
        } else if ("2".equals(val)) {
            return "h5/h5site";
        }
        return null;
    }

}
