package com.yn.springapp.store.dao;

import com.yn.springapp.store.pojo.BusinessStorePojo;

/**
 * 商户注册商户
 */
public interface BusinessStoreMapper {

    public BusinessStorePojo queryBusinessStore(BusinessStorePojo businessStorePojo);

}
