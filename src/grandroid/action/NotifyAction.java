/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.action;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import grandroid.activity.DialogActivity;

/**
 *
 * @author Rovers 
 * 在AndroidManifest.xml要加上下面設定，才會顯示
 * <activity android:name="grandroid.activity.DialogActivity" android:theme = "@style/Transparent"/>
 */
public class NotifyAction extends ContextAction {

    /**
     *
     */
    protected String title;
    /**
     *
     */
    protected String msg;
    /**
     *
     */
    protected int icon;
    /**
     *
     */
    protected int group;
    /**
     *
     */
    protected Class target;
    /**
     *
     */
    protected Bundle extra;
    /**
     *
     */
    protected int flag;
    /**
     *
     */
    protected boolean autoCancel;
    /**
     *
     */
    protected boolean virbation;

    /**
     * 在AndroidManifest.xml要加上下面設定，才會顯示
     * <activity android:name="grandroid.activity.DialogActivity" android:theme
     * = "@style/Transparent"/>
     *
     * @param context
     * @param actionName
     */
    public NotifyAction(Context context, String actionName) {
        super(context, actionName);
        title = "未設定";
        icon = R.drawable.ic_dialog_info;
        group = 0;
        target = null;
        flag = PendingIntent.FLAG_UPDATE_CURRENT;
        autoCancel = true;
    }

    /**
     * 在AndroidManifest.xml要加上下面設定，才會顯示
     * <activity android:name="grandroid.activity.DialogActivity" android:theme
     * = "@style/Transparent"/>
     *
     * @param context
     */
    public NotifyAction(Context context) {
        this(context, "");
    }

    /**
     *
     * @param title
     * @param msg
     * @return
     */
    public NotifyAction setContent(String title, String msg) {
        this.title = title;
        this.msg = msg;
        return this;
    }

    /**
     *
     * @param group
     * @return
     */
    public NotifyAction setGroup(int group) {
        this.group = group;
        return this;
    }

    /**
     *
     * @param icon
     * @return
     */
    public NotifyAction setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    /**
     *
     * @param target
     * @param extra
     * @return
     */
    public NotifyAction setTarget(Class target, Bundle extra) {
        this.target = target;
        this.extra = extra;
        return this;
    }

    public NotifyAction useDefaultDialog() {
        this.target = DialogActivity.class;
        return this;
    }

    public NotifyAction useDefaultDialog(Bundle extra) {
        this.target = DialogActivity.class;
        if (this.extra != null) {
            this.extra.putAll(extra);
        } else {
            this.extra = extra;
        }
        return this;
    }

    public NotifyAction useDefaultDialog(Class target) {
        return useDefaultDialog(target, null);
    }

    public NotifyAction useDefaultDialog(Class target, Bundle extra) {
        this.target = DialogActivity.class;
        if (this.extra == null) {
            this.extra = new Bundle();
        }
        if (extra != null) {
            this.extra.putAll(extra);
        }
        this.extra.putString("TARGET", target.getName());
        return this;
    }

    public NotifyAction setDialogText(String btnTextOK, String btnTextGoto) {
        if (this.extra == null) {
            this.extra = new Bundle();
        }
        if (btnTextOK != null) {
            this.extra.putString("STR_OK", btnTextOK);
        }
        if (btnTextGoto != null) {
            this.extra.putString("STR_GOTO", btnTextGoto);
        }
        return this;
    }

    /**
     *
     * @param flag
     * @return
     */
    public NotifyAction setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    /**
     *
     * @param autoCancel
     * @return
     */
    public NotifyAction setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
        return this;
    }

    /**
     *
     * @return
     */
    public boolean isVirbation() {
        return virbation;
    }

    /**
     *
     * @param virbation
     * @return
     */
    public NotifyAction setVirbation(boolean virbation) {
        this.virbation = virbation;
        return this;
    }

    /**
     *
     * @param context
     * @return
     */
    @Override
    public boolean execute(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification no = new Notification(icon, msg, System.currentTimeMillis());
        if (autoCancel) {
            no.flags = Notification.FLAG_AUTO_CANCEL;;
        }
        CharSequence contentTitle = title;
        CharSequence contentText = msg;
        Intent notificationIntent = null;
        if (target != null) {
            notificationIntent = new Intent(context, target);
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (target == DialogActivity.class) {
                if (extra == null) {
                    extra = new Bundle();
                }
                extra.putString("TITLE", title);
                extra.putString("CONTENT", msg);
            }
            notificationIntent.putExtras(extra);
        }
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, flag);

        no.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        if (virbation) {
            no.defaults |= Notification.DEFAULT_VIBRATE;
            long[] vibrate = {0, 100, 200, 300};
            no.vibrate = vibrate;
        }
        manager.notify(group, no);
        return true;
    }
}
