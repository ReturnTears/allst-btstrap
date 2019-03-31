package allst.boot.strap.controller.api;

import allst.boot.strap.bean.Brand;
import allst.boot.strap.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Vue调用后台数据的API Controller
 * @author June 2019/03/30 上午 10:20
 * @version 1.0
 */
@RestController
@RequestMapping("/vueApi")
public class AllstVueApiController {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据id获取brand信息
     * @param id
     * @return
     */
    @GetMapping("/brand/{id}")
    public Brand getBrandLists(@PathVariable("id") Integer id) {
        return brandMapper.getBrandById(id);
    }

    @GetMapping("/brand")
    public List<Brand> getBrands() {
        return brandMapper.getBrands();
    }

    @PostMapping("/brand")
    public Integer insertBrand(Integer brandId, String brandName) {
        return brandMapper.insertBrand(brandId, brandName);
    }
}
