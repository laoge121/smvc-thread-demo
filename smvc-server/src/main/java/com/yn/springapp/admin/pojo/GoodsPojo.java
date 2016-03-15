package com.yn.springapp.admin.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品对象
 */
public class GoodsPojo implements Serializable {

    private String gid;

    private String gname;

    private BigDecimal price;

    private int gnum;

    private String gdesc;

    private String gsdesc;

    public GoodsPojo(String gid, String gname, BigDecimal price, int gnum, String gdesc, String gsdesc) {
        this.gid = gid;
        this.gname = gname;
        this.price = price;
        this.gnum = gnum;
        this.gdesc = gdesc;
        this.gsdesc = gsdesc;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public String getGsdesc() {
        return gsdesc;
    }

    public void setGsdesc(String gsdesc) {
        this.gsdesc = gsdesc;
    }
}
