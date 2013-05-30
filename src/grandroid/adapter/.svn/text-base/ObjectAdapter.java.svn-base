/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

/**
 *
 * @param <T>
 * @author Rovers
 */
public abstract class ObjectAdapter<T> extends BaseAdapter implements ItemClickable<T> {

    /**
     *
     */
    protected Context context;
    /**
     *
     */
    protected List<T> list;
    protected boolean cycle;

    /**
     *
     * @param context
     * @param list
     */
    public ObjectAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    /**
     *
     * @param context
     * @param list
     */
    public ObjectAdapter(Context context, List<T> list, boolean cycle) {
        this.context = context;
        this.list = list;
        this.cycle = cycle;
    }

    public boolean isCycle() {
        return cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public int getCycleIndex() {
        return 3000 - (3000 % list.size());
    }

    /**
     *
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    /**
     *
     * @return
     */
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
            if (cycle) {
                return Integer.MAX_VALUE;
            } else {
                return list.size();
            }
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public T getItem(int index) {
        return list.get(cycle ? index % list.size() : index);
    }

    /**
     *
     * @param index
     * @return
     */
    public long getItemId(int index) {
        return index;
    }

    /**
     *
     * @param index
     * @param view
     * @param parent
     * @return
     */
    public View getView(int index, View view, ViewGroup parent) {
        if (view == null) {
            view = createRowView(index, list.get(cycle ? index % list.size() : index));
        }
        fillRowView(index, view, list.get(cycle ? index % list.size() : index));
        return view;
    }

    /**
     *
     * @param index
     * @param item
     * @return
     */
    public abstract View createRowView(int index, T item);

    /**
     *
     * @param index
     * @param cellRenderer
     * @param item
     */
    public abstract void fillRowView(int index, View cellRenderer, T item);

    /**
     *
     * @param <T>
     * @param v
     * @param tag
     * @param c
     * @return
     */
    protected <T extends View> T findView(View v, String tag, Class<T> c) {
        if (v.getTag() != null && v.getTag().equals(tag)) {
            return (T) v;
        }
        if (v instanceof ViewGroup) {
            View answer = null;
            for (int i = 0; i < ((ViewGroup) v).getChildCount(); i++) {
                answer = findView(((ViewGroup) v).getChildAt(i), tag, c);
                if (answer != null) {
                    return (T) answer;
                }
            }
            return null;
        }
        return null;
    }

    /**
     *
     * @param index
     * @param view
     * @param item
     */
    public void onClickItem(int index, View view, T item) {
        //Toast.makeText(context, "not override method 'onClickItem' at JSONAdapter instance yet!", Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param index
     * @param view
     * @param item
     */
    public void onLongPressItem(int index, View view, T item) {
        //Toast.makeText(context, "not override method 'onLongPressItem' at JSONAdapter instance yet!", Toast.LENGTH_SHORT).show();
    }
}
