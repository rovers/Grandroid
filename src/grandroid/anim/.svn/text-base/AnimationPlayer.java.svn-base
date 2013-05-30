/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.anim;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

/**
 *
 * @author Rovers
 */
public class AnimationPlayer {

    public static int baseIndex = 1;
    protected ImageView iv;
    protected int interval = 1000;//unit=ms
    protected int index;
    protected String resourceFormat;
    protected HandlerThread thread;
    protected Handler handler;
    protected int maxCycles;
    protected int cycle;
    protected boolean changeBackground;
    protected long startMS = 0;
    protected Animation.AnimationListener listener;

    public AnimationPlayer(ImageView iv, String resourceFormat, int interval, int maxCycles, boolean changeBackground) {
        this.iv = iv;
        this.resourceFormat = resourceFormat;
        this.interval = interval;
        this.index = baseIndex;
        this.maxCycles = maxCycles;
        this.changeBackground = changeBackground;
    }

    public AnimationPlayer(ImageView iv, String resourceFormat, int interval) {
        this(iv, resourceFormat, interval, -1, false);
    }

    public ImageView getImageView() {
        return iv;
    }

    public void play() {
        if (thread != null) {
            stop();
        }
        thread = new HandlerThread("animationPlayer");
        thread.start();
        handler = new Handler(thread.getLooper());
        handler.post(createTask());
    }

    public void stop() {
        if (thread != null) {
            try {
                thread.interrupt();
            } catch (Exception ex) {
                Log.e("familife", null, ex);
            }
            thread = null;
            handler = null;
        }
    }

    public Runnable createTask() {
        return new Runnable() {
            @Override
            public void run() {

                ((Activity) iv.getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        if (startMS == 0) {
                            index = baseIndex;
                            startMS = System.currentTimeMillis();
                            if (listener != null) {
                                listener.onAnimationStart(null);
                            }
                        } else {
                            index = (int) ((System.currentTimeMillis() - startMS) / interval);
                        }

                        String uri = "drawable/" + String.format(resourceFormat, index);
                        //Log.d("Starbucks", "play " + uri);
                        int imageResource = 0;
                        try {
                            imageResource = iv.getContext().getResources().getIdentifier(uri, null, iv.getContext().getPackageName());
                            if (imageResource == 0) {
                                cycle++;
                                if (maxCycles != -1 && cycle >= maxCycles) {
                                    stop();
                                    if (listener != null) {
                                        listener.onAnimationEnd(null);
                                    }
                                    return;
                                } else {
                                    index = baseIndex;
                                    startMS = System.currentTimeMillis();
                                    uri = "drawable/" + String.format(resourceFormat, index);
                                    imageResource = iv.getContext().getResources().getIdentifier(uri, null, iv.getContext().getPackageName());
                                    if (listener != null) {
                                        listener.onAnimationRepeat(null);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            Log.e("familife", "can't found resource " + imageResource, ex);
                        }
                        //Log.d("familife", "play " + uri + " image...");
                        if (changeBackground) {
                            iv.setBackgroundResource(imageResource);
                        } else {
                            iv.setImageResource(imageResource);
                        }
                        if (handler != null) {
                            handler.postDelayed(createTask(), Math.max((startMS + (index - baseIndex + 2) * interval) - System.currentTimeMillis(), 0));
                        }
                    }
                });
            }
        };
    }

    public AnimationListener getListener() {
        return listener;
    }

    public void setListener(AnimationListener listener) {
        this.listener = listener;
    }
}
