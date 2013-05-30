/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

/**
 *
 * @author Rovers
 */
public class StyledText {

    protected TextView tv;
    protected int size;
    protected Unit unit;
    protected boolean b;
    protected int gravity;
    protected Integer co;
    protected Matrix m;
    protected Object t;
    protected boolean strike;
    protected CharSequence txt;
    protected int[] padding;
    protected int maxl;

    public enum Unit {

        Px, Dip, Sp, Pt, Auto;
    }

    public StyledText() {
        unit = Unit.Sp;
        size = -1;
        txt = null;
        padding = new int[]{0, 0, 0, 0};
    }

    public StyledText color(int co) {
        this.co = co;
        return this;
    }

    public StyledText color(String coHex) {
        this.co = Color.parseColor(coHex);
        return this;
    }

    public StyledText bold() {
        b = true;
        return this;
    }

    public StyledText strike() {
        strike = true;
        return this;
    }

    public StyledText size(int size) {
        this.size = size;
        return this;
    }

    public StyledText size(Unit unit, int size) {
        this.size = size;
        this.unit = unit;
        return this;
    }

    public StyledText center() {
        this.gravity = Gravity.CENTER;
        return this;
    }

    public StyledText right() {
        this.gravity = Gravity.RIGHT;
        return this;
    }

    public StyledText gravity(int g) {
        this.gravity = g;
        return this;
    }

    public StyledText tag(Object tagObj) {
        t = tagObj;
        return this;
    }

    public StyledText text(CharSequence txt) {
        this.txt = txt;
        return this;
    }

    public StyledText html(String strHTML) {
        this.txt = Html.fromHtml(strHTML);
        return this;
    }

    public StyledText padding(int left, int top, int right, int bottom) {
        padding[0] = left;
        padding[1] = top;
        padding[2] = right;
        padding[3] = bottom;
        return this;
    }

    public StyledText maxLine(int line) {
        maxl = line;
        return this;
    }

    public TextView create(Context context) {
        TextView textView = new TextView(context);
        apply(textView);
        return textView;
    }

    public StyledText set(TextView tv) {
        this.tv = tv;
        return this;
    }

    public StyledText setMatrix(Matrix m) {
        this.m = m;
        return this;
    }

    public TextView get() {
        apply(tv, null);
        return tv;
    }

    public TextView apply(TextView tv) {
        return apply(tv, this.txt);
    }

    public TextView apply(TextView tv, CharSequence txt) {
        if (b) {
            tv.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (strike) {
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (gravity != 0) {
            tv.setGravity(gravity);
        }
        //Log.d("familife", "use co=" + co);
        if (co != null) {
            tv.setTextColor(co);
        }
        if (size > 0) {
            if (unit == Unit.Auto && m != null) {
                tv.setTextSize(unit.Px.ordinal(), m.mapRadius(size));
            } else {
                tv.setTextSize(unit.ordinal(), size);
            }
        }
        if (t != null) {
            tv.setTag(t);
        }
        if (txt != null) {
            tv.setText(txt);
        }
        if (m != null) {
            tv.setPadding((int) m.mapRadius(padding[0]), (int) m.mapRadius(padding[1]), (int) m.mapRadius(padding[2]), (int) m.mapRadius(padding[3]));
        } else {
            tv.setPadding(padding[0], padding[1], padding[2], padding[3]);
        }
        if (maxl > 0) {
            tv.setMaxLines(maxl);
        }
        return tv;
    }
}
