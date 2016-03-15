package com.yn.springapp.admin.dao;

import com.yn.springapp.admin.pojo.MenusPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pei.xu on 2015/10/26.
 */
@Repository
public interface MenusMapper {

    /**
     * 查询菜单
     *
     * @return
     */
    public List<MenusPojo> queryMenus();
}
