package allst.boot.strap.bean;

import java.sql.Date;

/**
 * Brand Bean
 * @author June 2019/03/31 上午 11:26
 * @version 1.0
 */
public class Brand {
    private Integer id;
    private Integer brandID;
    private String brandName;
    private Date brandCTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getBrandCTime() {
        return brandCTime;
    }

    public void setBrandCTime(Date brandCTime) {
        this.brandCTime = brandCTime;
    }
}
