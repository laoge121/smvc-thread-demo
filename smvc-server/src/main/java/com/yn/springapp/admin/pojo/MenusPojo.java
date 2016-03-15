package com.yn.springapp.admin.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单实体
 */
public class MenusPojo implements Serializable {

    private String id;

    private String menuid;

    private String menuname;

    private String parentid;

    private String menuurl;

    private String desc;

    private int sort;

    private int mstatus;

    private List<MenusPojo> childMenus;

    public MenusPojo() {
    }

    public int getMstatus() {
        return mstatus;
    }

    public void setMstatus(int mstatus) {
        this.mstatus = mstatus;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<MenusPojo> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<MenusPojo> childMenus) {
        this.childMenus = childMenus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
