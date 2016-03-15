package com.yn.springapp.admin.dao;

import com.yn.springapp.admin.pojo.GoodsPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pei.xu on 2015/10/26.
 */
@Repository
public interface GoodsMapper {

    /**
     * @return
     */
    public List<GoodsPojo> queryGoodsInfo();
}
