package allst.boot.strap.mapper;

import allst.boot.strap.bean.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 品牌列表Mapper类
 * @author June 2019/03/31 上午 11:25
 * @version 1.0
 */
public interface BrandMapper {
    /**
     * 通过id获取Brand
     * @param id
     * @return
     */
    @Select("select id,brandId, brandName, brandCTime from brand where Id = #{id}")
    Brand getBrandById(Integer id);

    /**
     * 获取所有的Brand信息
     * @return
     */
    @Select("select id, brandId, brandName, brandCTime from brand")
    List<Brand> getBrands();

    /**
     * 添加brand信息
     * @return
     */
    @Insert("insert into brand (brandId, brandName) values (#{brandID}, #{brandName})")
    Integer insertBrand(Integer brandId, String brandName);
}
