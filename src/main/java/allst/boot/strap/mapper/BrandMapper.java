package allst.boot.strap.mapper;

import allst.boot.strap.bean.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
    @Select("select id, brandID, brandName, brandCTime from brand where Id = #{id}")
    Brand getBrandById(Integer id);

    /**
     * 获取所有的Brand信息
     * @return
     */
    @Select("select id, brandID, brandName, brandCTime from brand")
    List<Brand> getBrands();

    /**
     * 添加brand信息
     * @param brand
     * @return
     */
    @Insert("insert into brand(brandID, brandName) values (#{brandID}, #{brandName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertBrand(Brand brand);

    /**
     * 根据brandID删除Brand信息
     * @param brandID
     */
    @Delete("delete from brand where brandID = #{brandID}")
    void delBrand(Integer brandID);
}
