package allst.boot.strap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author June 2019/03/05 下午 09:23
 * @version 1.0
 */
@Controller
public class CssDemoController {

    @GetMapping("/css")
    public String cssDemo() {
        return "bootstrap/CSSDemo";
    }

    @GetMapping("/coder")
    public String cssCoder() {
        return "bootstrap/CSSCoder";
    }

    @GetMapping("/tab")
    public String cssTab() {
        return "bootstrap/CSSTab";
    }

    @GetMapping("/btn")
    public String cssBtn() {
        return "bootstrap/CSSButton";
    }

    @GetMapping("/menu")
    public String cssMenu() {
        return "bootstrap/CSSMenu";
    }

    @GetMapping("/btn-group")
    public String cssBtnGroup() {
        return "bootstrap/CSSButtonGroup";
    }

    @GetMapping("/btn-menu")
    public String cssBtnMenu() {
        return "bootstrap/CSSButtonMenu";
    }

    @GetMapping("/input")
    public String cssInput() {
        return "bootstrap/CSSInput";
    }

    @GetMapping("/nav")
    public String cssNav() {
        return "bootstrap/CSSNav";
    }

    @GetMapping("/select2")
    public String cssBootstrapSelect2() {
        return "bootstrap/bootstrapSelect2";
    }

    @GetMapping("/fr1")
    public String cssFineReport1() {
        return "fr/finereport1";
    }
}
