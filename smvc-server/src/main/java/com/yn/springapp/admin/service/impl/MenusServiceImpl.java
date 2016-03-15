package com.yn.springapp.admin.service.impl;

import com.yn.springapp.admin.dao.MenusMapper;
import com.yn.springapp.admin.pojo.MenusPojo;
import com.yn.springapp.admin.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单类
 */
@Service("menusService")
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenusMapper menusMapper;

    @Override
    public List<MenusPojo> queryTreeMenus() {

        //查询
        List<MenusPojo> paramList = menusMapper.queryMenus();

        //返回结果
        List<MenusPojo> retList = new ArrayList<MenusPojo>();

        for (MenusPojo menusPojo : paramList) {

            if ("0".equals(menusPojo.getParentid())) {
                retList.add(menusPojo);
            }
        }

        for (MenusPojo tmpMenus : retList) {

            List<MenusPojo> tmpList = new ArrayList<MenusPojo>();

            for (MenusPojo menusPojo : paramList) {

                if ("0".equals(menusPojo.getParentid())) {
                    continue;
                }
                if (menusPojo.getParentid().equals(tmpMenus.getMenuid())) {
                    tmpList.add(menusPojo);
                }
            }
            tmpMenus.setChildMenus(tmpList);
        }
        return retList;
    }
}
