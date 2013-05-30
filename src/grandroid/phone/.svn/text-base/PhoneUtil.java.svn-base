/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.phone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 *
 * @author Rovers
 */
public class PhoneUtil {

    /**
     *
     * @param context
     * @return
     */
    public static boolean hasNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable();
    }

    //需要權限<uses-permission android:name="android.permission.READ_PHONE_STATE" />
    /**
     *
     * @param context
     * @return
     */
    public static String getDeviceID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String id = tm.getDeviceId();
        if (id == null || id.length() == 0) {
            id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;
    }

    public static void hideKeyboard(View view) {
        InputMethodManager mgr = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
