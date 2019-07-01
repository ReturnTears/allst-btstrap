package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author June 2019-05-14 上午 12:06
 * @version 1.0
 */
@Controller
@RequestMapping("/video")
public class VideoController {

    @RequestMapping("dh")
    public ModelAndView dhVideo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("video/dhVideo");
        return modelAndView;
    }

    @RequestMapping(value = "index/{id}", method = {RequestMethod.GET})
    public ModelAndView indexVideo(@PathVariable("id") String id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("id", id);
        modelAndView.setViewName("video/index");
        return modelAndView;
    }
}
