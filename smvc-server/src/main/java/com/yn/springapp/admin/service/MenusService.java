package com.yn.springapp.admin.service;

import com.yn.springapp.admin.pojo.MenusPojo;

import java.util.List;

/**
 * Created by pei.xu on 2015/10/26.
 */
public interface MenusService {

    /**
     * 查询树形菜单
     *
     * @return
     */
    public List<MenusPojo> queryTreeMenus();

}
