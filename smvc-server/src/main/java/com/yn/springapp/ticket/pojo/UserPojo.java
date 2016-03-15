package com.yn.springapp.ticket.pojo;

import java.io.Serializable;

/**
 * User: pei.xu
 * Date: 15-2-2
 * Time: 上午11:49
 */
public class UserPojo implements Serializable {

    private String name;

    private String password;

    private String idCard;

    private String sex;

    private int age;

    public UserPojo() {
    }

    public UserPojo(String name, String password, String sex, int age) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
