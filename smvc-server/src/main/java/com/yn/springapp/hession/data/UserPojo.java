package com.yn.springapp.hession.data;

import java.io.Serializable;

/**
 * Created by pei.xu on 2016/3/11.
 */
public class UserPojo implements Serializable {

    private String name;

    private String pwd;

    public UserPojo() {
    }

    public UserPojo(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
