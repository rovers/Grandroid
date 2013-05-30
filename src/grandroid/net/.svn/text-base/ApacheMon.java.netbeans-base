/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.net;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Rovers
 */
public class ApacheMon {

    /**
     *
     */
    protected String uri;
    /**
     *
     */
    protected HashMap<String, String> param;
    ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
    /**
     *
     */
    protected String encoding;
    /**
     *
     */
    public static String cookie = null;
    /**
     *
     */
    public boolean keepingCookie = false;
    /**
     *
     */
    protected boolean loginConnection = false;
    /**
     *
     */
    protected int method;
    /**
     *
     */
    public static final int POST = 0;
    /**
     *
     */
    public static final int GET = 1;
    /**
     *
     */
    public static final int PUT = 2;
    /**
     *
     */
    public static final int DELETE = 3;

    /**
     *
     * @param uri 欲擷取資料的URL
     */
    public ApacheMon(String uri) {
        this(uri, false);
    }

    /**
     *
     * @param uri 欲擷取資料的URL
     */
    public ApacheMon(String uri, boolean keepCookie) {
        this.uri = uri;
        this.encoding = "UTF-8";

        param = new HashMap<String, String>();
        method = 0;
        this.keepingCookie = keepCookie;
    }

    /**
     *
     * @return
     */
    protected ApacheMon getLoginMon() {
        return null;
    }

    /**
     *
     * @return
     */
    public ApacheMon asLoginConnection() {
        loginConnection = true;
        return this;
    }

    /**
     *
     * @param result
     * @return
     */
    protected boolean handleLogin(String result) {
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isKeepingCookie() {
        return keepingCookie;
    }

    /**
     *
     * @param keepingCookie
     */
    public ApacheMon setKeepingCookie(boolean keepingCookie) {
        this.keepingCookie = keepingCookie;
        return this;
    }

    /**
     * 伺服端服務網址
     *
     * @return
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param encoding
     * @return
     */
    public ApacheMon encode(String encoding) {
        this.encoding = encoding;
        return this;
    }

    /**
     * 新增一組傳輸參數
     *
     * @param key 參數的名字
     * @param value 參數值
     * @return Mon物件本身，方便串接
     */
    public ApacheMon put(String key, String value) {
        param.put(key, value);
        pairList.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * 清除變數
     */
    public void clear() {
        param.clear();
        pairList.clear();
    }

    /**
     * 取得參數內容 (實作方式為TreeMap.toString())
     *
     * @return
     */
    public String getOrderedParameters() {
        TreeMap<String, String> tm = new TreeMap<String, String>();
        for (String key : param.keySet()) {
            tm.put(key, param.get(key));
        }
        return tm.toString();
    }

    public String getParameters() {
        StringBuilder sb = new StringBuilder();
        for (String key : param.keySet()) {
            if (sb.length() != 0) {
                sb.append("&");
            }
            try {
                sb.append(key).append("=").append(URLEncoder.encode(param.get(key).replaceAll("\\\\/", "/"), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Log.e("grandroid", null, ex);
            }
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    public ApacheMon asPost() {
        method = 0;
        return this;
    }

    /**
     *
     * @return
     */
    public ApacheMon asGet() {
        method = 1;
        return this;
    }

    /**
     *
     * @return
     */
    public ApacheMon asPut() {
        method = 2;
        return this;
    }

    /**
     *
     * @return
     */
    public ApacheMon asDelete() {
        method = 3;
        return this;
    }

    /**
     * 開始連線傳輸
     *
     * @return server端回應的字串傳回
     */
    public String send() {
        try {
            return sendWithError();
        } catch (Exception ex) {
            Log.e("grandroid", null, ex);
            return "{msg:\"" + ex.toString() + "\"}";
        }
    }

    public String sendWithError() throws Exception {
        DefaultHttpClient demo = new DefaultHttpClient();
        demo.getParams().setParameter("http.protocol.content-charset", encoding);

        // Get Request Example，取得 google 查詢 httpclient 的結果
        HttpRequestBase request = null;
        switch (method) {

            case 0:
                request = new HttpPost(uri);

                if (pairList.size() > 0) {
                    StringEntity entity = new StringEntity(URLEncodedUtils.format(pairList, "UTF-8"));
                    ((HttpPost) request).setEntity(entity);
                }
                break;
            case 1:
                request = new HttpGet(uri + (param.isEmpty() ? "" : "?" + getParameters()));
                break;
            case 2:
                request = new HttpPut(uri);
                if (pairList.size() > 0) {
                    StringEntity entity = new StringEntity(URLEncodedUtils.format(pairList, "UTF-8"));
                    ((HttpPut) request).setEntity(entity);
                }
                break;
            case 3:
                request = new HttpDelete(uri + (param.isEmpty() ? "" : "?" + getParameters()));
                break;
        }

        HttpResponse response = demo.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 如果回傳是 200 OK 的話才輸出
            //System.out.println(responseString);
            return responseString;
        } else {
            throw new Exception("http connect fail code=" + response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 開始連線傳輸，並將結果JSON文字包成JSONObject回傳
     *
     * @return server端回傳的JSON文字所包裝成的JSONObject
     * @throws JSONException
     */
    public JSONObject sendAndWrap() throws Exception {

        try {
            String string = sendWithError();
            return new JSONObject(string);
        } catch (Exception e) {
            Log.e("grandroid", null, e);
            //Log.e("grandroid", "mon url = " + uri);
            //Log.e("grandroid", "mon params = " + getParameters());
            //Log.e("grandroid", "mon result = " + string);
            throw e;
        }
    }

    /**
     * 開始連線傳輸，並將結果JSON文字包成JSONArray回傳
     *
     * @return server端回傳的JSON文字所包裝成的JSONArray
     * @throws JSONException
     */
    public JSONArray sendAndWrapArray() throws Exception {
        return new JSONArray(sendWithError());
    }
}
