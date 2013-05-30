/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.net;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * HTTP連線物件，用來擷取網頁的回傳結果，可用在取得JSON字串或網頁HTML的時候，支援POST。
 *
 * @author Rovers
 */
public class Mon {

    /**
     *
     */
    protected String uri;
    /**
     *
     */
    protected HashMap<String, String> param;
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
    public Mon(String uri) {
        this(uri, false);
    }

    /**
     *
     * @param uri 欲擷取資料的URL
     */
    public Mon(String uri, boolean keepCookie) {
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
    protected Mon getLoginMon() {
        return null;
    }

    /**
     *
     * @return
     */
    public Mon asLoginConnection() {
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
    public Mon setKeepingCookie(boolean keepingCookie) {
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
    public Mon encode(String encoding) {
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
    public Mon put(String key, String value) {
        param.put(key, value);
        return this;
    }

    /**
     * 清除變數
     */
    public void clear() {
        param.clear();
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
                Logger.getLogger(Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();
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

    /**
     *
     * @return
     */
    public Mon asPost() {
        method = 0;
        return this;
    }

    /**
     *
     * @return
     */
    public Mon asGet() {
        method = 1;
        return this;
    }

    /**
     *
     * @return
     */
    public Mon asPut() {
        method = 2;
        return this;
    }

    /**
     *
     * @return
     */
    public Mon asDelete() {
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
        String requestString = getParameters();
//            for (String key : param.keySet()) {
//                String encodedValue = key + "=" + URLEncoder.encode(param.get(key).replaceAll("\\\\/", "/"), "UTF-8");
//                requestString += requestString.length() == 0 ? encodedValue : "&" + encodedValue;
//            }
        //Log.d("grandroid", "real url = " + (uri + (method == 1 ? "?" + requestString : "")));
        URL url = new URL(uri + (method == 1 ? "?" + requestString : ""));
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        Log.d("grandroid", "keepingCookie=" + isKeepingCookie() + ", cookie=" + getCookie());
        if (isKeepingCookie()) {
            if (getCookie() == null) {
                Mon logMon = getLoginMon();
                if (logMon != null) {
                    Log.d("grandroid", "login...");
                    if (!handleLogin(logMon.asLoginConnection().send())) {
                        throw new Exception("mon auto-login fial");
                    }
                }
            }
        }
        ////设置连接属性
        if (method % 2 == 0) {
            httpConn.setDoOutput(true);//使用 URL 连接进行输出
        }
        httpConn.setDoInput(true);//使用 URL 连接进行输入
        httpConn.setUseCaches(false);//忽略缓存
        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        switch (method) {
            case 0:
                httpConn.setRequestMethod("POST");//设置URL请求方法
                break;
            case 1:
                httpConn.setRequestMethod("GET");//设置URL请求方法
                break;
            case 2:
                httpConn.setRequestMethod("PUT");//设置URL请求方法
                break;
            case 3:
                httpConn.setRequestMethod("DELETE");//设置URL请求方法
                break;
        }
        if (method % 2 == 0) {

            byte[] requestStringBytes = requestString.getBytes("UTF-8");
            //httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Charset", "UTF-8");
            if (keepingCookie && getCookie() != null) {
                Log.d("grandroid", "send Cookie = " + getCookie());
                httpConn.setRequestProperty("Cookie", getCookie());
            }
            httpConn.connect();
            //
            //建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(requestStringBytes);
            outputStream.close();
        } else {
            if (keepingCookie && getCookie() != null) {
                //Log.d("grandroid", "Cookie = " + cookie);
                httpConn.setRequestProperty("Cookie", getCookie());
            }
            httpConn.connect();
        }
        //获得响应状态
        int responseCode = httpConn.getResponseCode();
        if (HttpURLConnection.HTTP_OK == responseCode) {//连接成功
            if (loginConnection || isKeepingCookie()) {
                extractCookie(httpConn);
            }

            //当正确响应时处理数据
            StringBuilder sb = new StringBuilder();
            String readLine;
            BufferedReader responseReader;
            if (httpConn.getHeaderField("Content-Type") != null && httpConn.getHeaderField("Content-Type").contains("charset")) {
                encoding = httpConn.getHeaderField("Content-Type").substring(httpConn.getHeaderField("Content-Type").indexOf("charset=") + 8);
                if (encoding.contains(";")) {
                    encoding = encoding.substring(0, encoding.indexOf(";"));
                }
            }
            //处理响应流，必须与服务器响应流输出的编码一致
            responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), encoding));
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine).append("\n");
            }
            responseReader.close();
            return sb.toString().trim();
        } else {
            return "{\"msg\":\"Mon connect fail\",\"code\":" + responseCode + "}";
        }
    }

    /**
     *
     * @return
     */
    public static String getCookie() {
        return cookie;
    }

    /**
     *
     * @param connection
     */
    protected void extractCookie(HttpURLConnection connection) {
        String cookieVal = null;
//        cookie = "";
        String tempCookie = "";
        String key = null;
        for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
            if (key.equalsIgnoreCase("set-cookie")) {
                cookieVal = connection.getHeaderField(i);
                Log.d("grandroid", "cookie: " + cookieVal);
                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
                tempCookie = tempCookie + cookieVal + ";";
            }
        }
        if (tempCookie.length() != 0) {
            cookie = tempCookie;
        }
        Log.d("grandroid", "get cookie: " + cookie);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Mon mon = new Mon("http://www.dingschool.com/raingo");
        //System.out.println(mon.put("col", "test").put("act", "+").put("json", "{\"target\":[\"ruby\",\"楊峻武\"],\"count\":2}").send());
        //System.out.println(mon.put("col", "test").put("act", "/target").put("json", "{\"target\":\"楊峻武\"}").send());
        //System.out.println(mon.put("col", "test").put("act", "&target,abc").put("json", "{\"target\":\"ruby\",\"x\":1}").send());
        //System.out.println(mon.put("col", "test").put("act", "*").send());
        //System.out.println(mon.put("col", "test").put("act", "?").put("json", "{\"target\":\"ruby\"}").send());
        //System.out.println(mon.put("col", "WordBank").put("act", "-").put("json","{ \"_id\" : { \"$oid\" : \"4c4423503cb9a6998cf85edb\"}}").send());
        Mon mon = new Mon("http://m.family.com.tw/api/active.php");
        try {
            //System.out.println(mon.put("page", "get_mission_now").put("user_id", "9").send());
            //System.out.println(mon.put("page", "get_store").put("user_id", "9").put("update_time_last", "null").send());
            //System.out.println(mon.put("page", "save_pvc").put("user_id", "56").put("pvc_id", "1").put("mission_id", "1").put("store_id", "005891").send());
            //System.out.println(mon.put("page", "get_pvc").put("user_id", "56").put("mission_id", "1").put("store_id", "005891").send());
            //System.out.println(mon.put("page", "get_mission_pvc").put("user_id", "9").put("mission_id", "1").send());
            //System.out.println(mon.put("page", "get_all_action").put("user_id", "62").send());
            //System.out.println(mon.put("page", "get_info").put("user_id", "9").send());
            //System.out.println(mon.put("page", "close").put("user_id", "62").send());
            //Mon mon = new Mon("http://family.hiiir.com/api/active.php");
            //System.out.println(mon.put("page", "get_store").put("user_id", "9").put("update_time_last", "2011-03-20 08:00:00").send());
            //System.out.println(mon.put("page", "restart").put("user_id", "62").put("user_latitude", "24.5").put("user_longitude", "121.5421").send());
            //System.out.println(mon.put("page", "get_lbs").put("user_id", "1").put("time", "2011-04-01 10:10:10").send());
            //System.out.println(mon.put("page", "get_lbs_message").put("user_id", "1").send());
            //System.out.println(mon.put("page", "get_food").put("group", "1").send());
            JSONArray arr = mon.put("page", "get_food").put("group", "1").sendAndWrap().getJSONArray("food");
            for (int i = 0; i < arr.length(); i++) {
                System.out.println(arr.get(i).toString());
            }
            //System.out.println(mon.put("page", "get_food_today").put("date", "2011-04-13").put("user_id", "1").send());
            //System.out.println(mon.put("page", "save_activity").put("date", "2011-04-13").put("user_id", "1").put("step", "210").put("expend_time", "312").put("calorie", "36").send());
            //System.out.println(mon.put("page", "get_calorie").put("date", "2011-04-13").put("user_id", "1").send());
            //System.out.println(mon.put("page", "get_calorie").put("date", "2011-04-13").put("user_id", "1").send());
        } catch (Exception ex) {
            Logger.getLogger(Mon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
