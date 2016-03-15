package com.yn.springapp.util.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * httpClient3.1 版本
 * User: pei.xu
 * Date: 15-2-10
 * Time: 上午10:51
 */
public class HttpClientUntil3 {

    private static HttpClient httpClient = null;

    static {
        HttpConnectionManagerParams httpConnectionManagerParams = new HttpConnectionManagerParams();

        HttpClientParams httpParams = new HttpClientParams();

        //设置每个请求最大的链接数
        httpConnectionManagerParams.setDefaultMaxConnectionsPerHost(100);

        //设置链接池中的最大数量
        httpConnectionManagerParams.setMaxTotalConnections(300);

        //请求链接超时
        httpConnectionManagerParams.setConnectionTimeout(100);

        //设置链接响应超时
        httpParams.setSoTimeout(30000);
        httpParams.setUriCharset("UTF-8");

        //模拟火狐浏览器
        httpParams.setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0");

        //设置内容编码
        httpParams.setContentCharset("UTF-8");
        httpConnectionManagerParams.setDefaults(httpParams);

        MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        httpConnectionManager.setParams(httpConnectionManagerParams);

        httpClient = new HttpClient();
        httpClient.setHttpConnectionManager(httpConnectionManager);
    }

    public HttpClient getInstance() {

        return httpClient;
    }

    /**
     * 请求url
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String requestUrl(String url, Map<String, String> paramMap) {

        PostMethod httpMethod = new PostMethod(url);
        String retStr = "";
        try {
            httpClient.executeMethod(httpMethod);
            retStr = httpMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpMethod.getResponseBodyAsStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return retStr;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        //银魂
        String str = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=" + URLEncoder.encode("看书", "UTF-8") + "&bk_length=600";
        String result = HttpClientUntil3.requestUrl(str, null);
        String sa = unicodeToUtf8(result);
        System.out.println(sa);
        // System.out.println(utf8ToUnicode(sa));

    }

    /**
     * utf-8 转换成 unicode
     *
     * @param inStr
     * @return
     */
    public static String utf8ToUnicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(myBuffer[i]);
            if (ub == Character.UnicodeBlock.BASIC_LATIN) {
                //英文及数字等
                sb.append(myBuffer[i]);
            } else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                //全角半角字符
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                //汉字
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * unicode 转换成 utf-8
     *
     * @param theString
     * @return
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
