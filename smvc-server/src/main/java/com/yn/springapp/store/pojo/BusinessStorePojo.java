package com.yn.springapp.store.pojo;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by pei.xu on 2015/10/23.
 * 商户注册信息
 */
public class BusinessStorePojo implements Serializable, Cloneable {
    private String bsid;
    private String bsname;
    private String nickname;
    private String idcard;
    private String phone;
    private String mobile;
    private String address;
    private String province;
    private String city;
    private String county;

    public BusinessStorePojo() {
    }

    public BusinessStorePojo(String bsid, String bsname, String nickname, String idcard, String phone, String mobile, String address, String province, String city, String county) {
        this.bsid = bsid;
        this.bsname = bsname;
        this.nickname = nickname;
        this.idcard = idcard;
        this.phone = phone;
        this.mobile = mobile;
        this.address = address;
        this.province = province;
        this.city = city;
        this.county = county;
    }

    public String getBsid() {
        return bsid;
    }

    public void setBsid(String bsid) {
        this.bsid = bsid;
    }

    public String getBsname() {
        return bsname;
    }

    public void setBsname(String bsname) {
        this.bsname = bsname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
