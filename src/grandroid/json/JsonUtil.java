/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.json;

import android.util.Log;
import java.util.Comparator;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Rovers
 */
public class JsonUtil {

    /**
     *
     * @param arr
     * @param index
     * @return
     * @throws JSONException
     */
    public static JSONArray removeArrayItem(JSONArray arr, int index) throws JSONException {
        JSONArray newArr = new JSONArray();
        for (int i = 0; i < arr.length(); i++) {
            if (index != i) {
                newArr.put(arr.get(i));
            }
        }
        return newArr;
    }

    public static JSONArray sort(JSONArray arr, final String attr) {
        JSONArray f = new JSONArray();
        TreeSet<JSONObject> ts = new TreeSet<JSONObject>(new Comparator<JSONObject>() {
            public int compare(JSONObject j1, JSONObject j2) {
                try {
                    return j1.getString(attr).compareTo(j2.getString(attr));
                } catch (JSONException ex) {
                    Log.e("grandroid", null, ex);
                }
                return j1.toString().compareTo(j2.toString());
            }
        });
        for (int i = 0; i < arr.length(); i++) {
            try {
                ts.add(arr.getJSONObject(i));
            } catch (JSONException ex) {
                Log.e("grandroid", null, ex);
            }
        }
        for (JSONObject jo : ts) {
            f.put(jo);
        }
        return f;
    }
}
