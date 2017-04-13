package com.jiupin.jiupinhui.manage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.MainActivity;
import com.jiupin.jiupinhui.utils.LogUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/4/12.
 */

public class PopWinManager {
    private Context mContext;
    private Button btnToHome;
    private Button btnToFeedback;
    private Button btnToMine;
    private PopupWindow popupWindow;
    private View parent;

    /**
     * PopupWindow管理类
     * @param context 上下文
     * @param parent  PopupWindow相对于那个View
     */
    public PopWinManager(Context context,View parent) {
        this.mContext = context;
        this.parent = parent;
    }

    public void createPopupWindow(){
        if (popupWindow == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.label_popwindow, null);
            btnToHome = (Button) view.findViewById(R.id.btn_to_home);
            btnToFeedback = (Button) view.findViewById(R.id.btn_to_feedback);
            btnToMine = (Button) view.findViewById(R.id.btn_to_mine);
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        LogUtils.d(TAG, parent.getX() + "---------" + parent.getY());
        //        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        //        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int width = (int) (btnToHome.getWidth() - parent.getWidth());
        popupWindow.showAsDropDown(parent, -width-20, 0);
        btnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
            }
        });
        btnToFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
            }
        });
        btnToMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
            }
        });
    }


}
