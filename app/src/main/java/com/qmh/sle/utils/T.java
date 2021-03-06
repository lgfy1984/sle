package com.qmh.sle.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qmh.sle.R;

/**
 * 常用Toast提醒，避免Toast多次提醒问题
 * Created by 黄海彬 on 2016/3/10.
 */
public class T {

    private static Toast toast;

    private static void makeText(Context context, CharSequence text, int gravity, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setDuration(duration);
            toast.setText(text);
        }
        if (gravity != 0) {
            toast.setGravity(gravity, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM, 0, 200);
        }
        toast.show();
    }

    /**
     * 显示一段较长时间的提示
     *
     * @param context
     * @param res
     */
    public static void showToastLong(Context context, int res) {
        showToastLong(context, context.getText(res));
    }

    public static void showToastLong(Context context, CharSequence message) {
        makeText(context, message, 0, Toast.LENGTH_LONG);
    }

    /**
     * 显示一段较短的提示
     *
     * @param context
     * @param res
     */
    public static void showToastShort(Context context, int res) {
        showToastShort(context, context.getText(res));
    }

    public static void showToastShort(Context context, CharSequence message) {
        makeText(context, message, 0, Toast.LENGTH_SHORT);
    }

    /**
     * 在指定位置显示
     *
     * @param context
     * @param gravity
     * @param res
     */
    public static void showToastInGravity(Context context, int gravity, int res) {
        showToastInGravity(context, gravity, context.getText(res));
    }

    public static void showToastInGravity(Context context, int gravity,
                                          CharSequence message) {
        makeText(context, message, gravity, Toast.LENGTH_SHORT);
    }

    /**
     * 显示提示和图标
     *
     * @param context
     * @param drawable
     * @param res
     */
    public static void showToastWithIcon(Context context, int drawable, int res) {
        showToastWithIcon(context, drawable, context.getText(res));
    }

    public static void showToastWithIcon(Context context, int drawable,
                                         CharSequence message) {
        Toast toast = getToast(context, drawable, message);
        toast.show();
    }

    /**
     * 在指定位置显示提示和图标
     *
     * @param context
     * @param drawable
     * @param res
     */
    public static void showToastWithIconInGravity(Context context,
                                                  int drawable, int gravity, int res) {
        showToastWithIconInGravity(context, drawable, gravity,
                context.getText(res));
    }

    public static void showToastWithIconInGravity(Context context,
                                                  int drawable, int gravity, CharSequence message) {
        Toast toast = getToast(context, drawable, message);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    private static Toast getToast(Context context, int drawable,
                                  CharSequence message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        ImageView iv = new ImageView(context);
        iv.setImageResource(drawable);
        linearLayout.addView(iv, 0);
        return toast;
    }

    /**
     * 自定义View带图片和文字
     *
     * @param context
     * @param drawable 为0不设置
     * @param message  为null不显示
     * @param gravity
     */
    public static void showCustomToast(Context context, int drawable,
                                       CharSequence message, int gravity) {
        Toast toast = new Toast(context);
        LinearLayout view = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.custom_toast, null);
        if (drawable != 0) {
            ImageView iv = (ImageView) view.getChildAt(0);
            iv.setImageResource(drawable);
        }
        if (message != null) {
            TextView tv = (TextView) view.getChildAt(1);
            tv.setText(message);
        }
        if (gravity != 0) {
            toast.setGravity(gravity, 0, 0);
        }
        toast.setView(view);
        toast.show();
    }

    /**
     * 获取下拉列表共用方法
     * @Title: getListPopupWindow
     * @param layOut
     * @param ac
     * @param width popwindow的宽
     * @param height popwindow的高
     * @param isMask 是否需要将背景色改变
     * @return
     * @return PopupWindow
     * @throws
     * @author
     */
    public static PopupWindow getListPopupWindow (View layOut, Activity ac, int width, int height, boolean isMask) {
        final Activity active = ac;
        final PopupWindow pop = new PopupWindow(layOut, width, height);
        pop.setBackgroundDrawable(active.getResources().getDrawable(R.drawable.yuanjiao));
        pop.update();
        pop.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pop.setWidth(width);
        pop.setHeight(height);
        pop.setBackgroundDrawable(null);
        pop.setBackgroundDrawable(ac.getResources().getDrawable(R.color.white));
        pop.setTouchable(true); // 设置popupwindow可点击
        pop.setOutsideTouchable(true); // 设置popupwindow外部可点击
        pop.setFocusable(true); // 获取焦点
        if(isMask){
            WindowManager.LayoutParams lp = active.getWindow().getAttributes();
            lp.alpha = 0.3f;
            active.getWindow().setAttributes(lp);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    if (!pop.isShowing()) {
                        WindowManager.LayoutParams lp = active.getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        active.getWindow().setAttributes(lp);
                    }
                }
            });
        }
        return pop;
    }
}
