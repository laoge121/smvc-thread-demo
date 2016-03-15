package com.yn.springapp.util.result;

import java.io.Serializable;

/**
 * User: pei.xu
 * Date: 15-2-3
 * Time: 上午11:12
 */
public class ResultApi implements Serializable {

    private boolean ret;

    private String message;

    private Object data;

    public ResultApi(boolean ret, String message, Object data) {
        this.ret = ret;
        this.message = message;
        this.data = data;
    }

    public static ResultApi success(Object data) {
        return new ResultApi(true, "success", data);
    }

    public static ResultApi error(Object data) {
        return new ResultApi(false, "error", data);
    }


    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
