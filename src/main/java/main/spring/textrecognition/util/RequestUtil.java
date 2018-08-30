package main.spring.textrecognition.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class RequestUtil {


    public static String doGet(String url, Map<String, String> params, String charset, Object... proxys) {
        String result = null;
        CloseableHttpResponse response = null;
        try {
            response = doGetToHttpEntity(url, params, charset, false, proxys);
            if (response != null && response.getEntity() != null) {
                charset = charset == null || "".equals(charset) ? "UTF-8" : charset;
                result = EntityUtils.toString(response.getEntity(), charset);
                EntityUtils.consume(response.getEntity());
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    /*********** 属性: Http客户端 ***************/
    /**
     * Http客户端
     *
     */
    private static CloseableHttpClient _HttpClient;

    /**
     * 初始化
     */
    private static void init() {
        if (_HttpClient == null) {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(60000).setSocketTimeout(60000).build();
            _HttpClient = HttpClientBuilder.create()
                    .setDefaultRequestConfig(config).build();
        }
    }

    /**
     * HTTP Get 获取内容（HttpEntity）
     *
     * @param url
     *            请求的url地址 ?之前的地址
     * @param params
     *            请求的参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    private static CloseableHttpResponse doGetToHttpEntity(String url, Map<String, String> params, String charset, boolean isStream, Object... proxys) {
        //初始化
        init();

        // Http返回对象
        CloseableHttpResponse response = null;

        try {
            if (params != null && !params.isEmpty()) {
                List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>(
                        params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?"
                        + EntityUtils.toString(new UrlEncodedFormEntity(pairs,
                        charset));
            }
            HttpGet httpGet = new HttpGet(url);
            setProxy(httpGet, proxys);
            response = _HttpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                httpGet.abort();
                System.out.println("请求错误：" + statusCode);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return response;
    }

    /**
     * 设置代理
     * @param requestBase
     */
    protected static void setProxy(HttpRequestBase requestBase, Object... proxys) {
        if (proxys != null && proxys.length == 2) {
            String proxyIp = (String)proxys[0];
            int proxyPort = (Integer)proxys[1];
            if (proxyIp != null && !"".equals(proxyIp)) {
                HttpHost httpHost = new HttpHost(proxyIp, proxyPort);
                RequestConfig httpConfig = RequestConfig.custom().setProxy(httpHost).build();
                requestBase.setConfig(httpConfig);
            }
        }
    }

    /**
     * 发送HTTP请求
     * @param url 			接收请求地址
     * @param httpCharSet	请求字符编码
     * @return
     * @throws Throwable
     */
    public static String doPost(String url, String body, String httpCharSet, Map<String, String> headers, Object... proxys) throws Throwable{
        init();
        HttpPost post = new HttpPost(url);
        for(Map.Entry entry : headers.entrySet()) {
            post.setHeader((String)entry.getKey(), (String)entry.getValue());
        }
        StringEntity entity = new StringEntity(body, httpCharSet);
        post.setEntity(entity);
        setProxy(post, proxys);
        CloseableHttpResponse response = _HttpClient.execute(post);
        String string = EntityUtils.toString(response.getEntity(), httpCharSet);
        return string;
    }



}
