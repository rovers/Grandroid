/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.anim;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import grandroid.action.Action;

/**
 *
 * @author Rovers
 */
public class Granimator {

    public static Animation makerVisibleMotion(final View view, int startOffset1, int duration1, float fromAlpha1, float toAlpha1, final Action finishAction) {
        return makerVisibleMotion(view, startOffset1, duration1, fromAlpha1, toAlpha1, finishAction, 0, 0, 1.0f, 1.0f);
    }

    public static Animation makerVisibleMotion(final View view, int startOffset1, int duration1, float fromAlpha1, float toAlpha1, final Action finishAction, int startOffset2, int duration2, float fromAlpha2, float toAlpha2) {
        AnimationSet as = new AnimationSet(true);
        //1代表完全不透明，0代表完全透明
        AlphaAnimation aa1 = new AlphaAnimation(fromAlpha1, toAlpha1);
        aa1.setStartOffset(startOffset1);
        aa1.setDuration(duration1);
        as.addAnimation(aa1);
        if (duration2 > 0) {
            AlphaAnimation aa2 = new AlphaAnimation(fromAlpha2, toAlpha2);
            aa2.setStartOffset(startOffset2);
            aa2.setDuration(duration2);
            as.addAnimation(aa2);
        }
        
        as.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationEnd(Animation arg0) {
                view.postDelayed(new Runnable() {
                    public void run() {
                        if (finishAction != null) {
                            finishAction.execute();
                        }
                    }
                }, 0);
            }

            public void onAnimationRepeat(Animation arg0) {
            }
        });
        view.startAnimation(as);
        return as;
    }

    public static Animation makeVerticalMotion(final View view, int startOffset, int duration, final Action finishAction) {
        final TranslateAnimation mAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, -1);
        mAnimation2.setDuration(duration);
        mAnimation2.setStartOffset(startOffset);
        mAnimation2.setRepeatMode(Animation.RESTART);



//        final LinearLayout container = new LinearLayout(tvMarquee.getContext());
//        ViewGroup vg = (ViewGroup) tvMarquee.getParent();
//        ViewGroup.LayoutParams lp = tvMarquee.getLayoutParams();
//        vg.removeView(tvMarquee);
//        vg.addView(container, lp);
//        container.addView(tvMarquee, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, height));

        mAnimation2.setAnimationListener(new Animation.AnimationListener() {
            int index = -1;

            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationEnd(Animation arg0) {
                view.postDelayed(new Runnable() {
                    public void run() {
                        finishAction.execute();
                    }
                }, 0);
            }

            public void onAnimationRepeat(Animation arg0) {
            }
        });
        view.startAnimation(mAnimation2);
        return mAnimation2;
    }

    public static Animation makeVerticalMarquee(TextView tvMarquee, String[] msgs, int duration, int delay) {
        return makeVerticalMarquee(tvMarquee, msgs, null, duration, delay);
    }

    public static Animation makeVerticalMarquee(final TextView tvMarquee, final String[] msgs, final Action[] actions, int duration, final int delay) {
        final TranslateAnimation mAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        mAnimation2.setDuration(duration);
        mAnimation2.setStartOffset(200);
        mAnimation2.setRepeatMode(Animation.RESTART);
        tvMarquee.setAnimation(mAnimation2);

//        final LinearLayout container = new LinearLayout(tvMarquee.getContext());
//        ViewGroup vg = (ViewGroup) tvMarquee.getParent();
//        ViewGroup.LayoutParams lp = tvMarquee.getLayoutParams();
//        vg.removeView(tvMarquee);
//        vg.addView(container, lp);
//        container.addView(tvMarquee, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, height));


        //tvMarquee.setBackgroundColor(Color.rgb(155, 100, 100));
        if (actions != null) {
            tvMarquee.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (view.getTag() != null && view.getTag() instanceof Action) {
                        ((Action) view.getTag()).execute();
                    }
                }
            });
        }
        mAnimation2.setAnimationListener(new Animation.AnimationListener() {
            int index = -1;

            public void onAnimationStart(Animation arg0) {
                index = index >= msgs.length - 1 ? 0 : index + 1;
                tvMarquee.setText(msgs[index]);
                if (actions != null) {
                    tvMarquee.setTag(actions[index]);
                }
            }

            public void onAnimationEnd(Animation arg0) {
                tvMarquee.postDelayed(new Runnable() {
                    public void run() {
                        //mAnimation2.start();
                        tvMarquee.startAnimation(mAnimation2);
                    }
                }, delay);
            }

            public void onAnimationRepeat(Animation arg0) {
//                index = index >= msgs.length - 1 ? 0 : index + 1;
//                tvMarquee.setText(msgs[index]);
            }
        });
        return mAnimation2;
    }
}
