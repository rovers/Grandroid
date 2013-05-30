/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * 記錄、載回app資料的物件，實作上採用Android的SharedPreference機制(類似Java的Properties)
 * 除了Service以外，Activity應繼承Face、呼叫getData()函數來取得實體
 *
 * @author Rovers
 */
public class DataAgent {

    //protected static ConcurrentHashMap data;
    /**
     *
     */
    protected SharedPreferences settings;

    /**
     *
     * @param context
     */
    public DataAgent(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     *
     * @param context
     */
    public DataAgent(SharedPreferences settings) {
        this.settings = settings;
    }

    /**
     * 將一組key-value放進SharedPreference
     *
     * @param key
     * @param value
     * @return 回傳value本身
     */
    public Object putPreference(String key, String value) {
        settings.edit().putString(key, value).commit();
        return value;
    }

    /**
     *
     * @return
     */
    public SharedPreferences getPreferences() {
        return settings;
    }

    /**
     *
     * @return
     */
    public Editor getEditor() {
        return settings.edit();
    }

    /**
     * 從SharedPreference裡取出對應至該key的值
     *
     * @param key
     * @return 對應至該key的值，若不存在則回傳空字串(非null)
     */
    public String getPreference(String key) {
        return settings.getString(key, "");
    }

    /**
     * 從SharedPreference裡取出對應至該key的值
     *
     * @param key
     * @param defaultValue 預設值
     * @return 對應至該key的值，若不存在則回傳defaultValue參數 (發現不存在後，並不會存進SharedPreference)
     */
    public String getPreference(String key, String defaultValue) {
        return settings.getString(key, defaultValue);
    }
}
