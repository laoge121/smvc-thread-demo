package com.yn.springapp.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.ContentEncodingHttpClient;

/**
 * httpClient4 版本
 * User: pei.xu
 * Date: 15-2-10
 * Time: 下午2:23
 */
public class HttpClientUntil4 {

    private static HttpClient httpclient = null;

    static {

    }

    public static String requestURL(String url) {


        //httpclient = new ContentEncodingHttpClient();

        //HttpHost httpHost = new HttpHost("*****.com", 80, null);

        //HttpPost httpPost = new HttpPost("******");

        //HttpEntity httpEntity = new StringEntity();

        // httpclient.execute();
        return "";
    }

}
