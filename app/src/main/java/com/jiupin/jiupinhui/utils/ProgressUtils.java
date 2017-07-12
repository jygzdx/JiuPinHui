package com.jiupin.jiupinhui.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.jiupin.jiupinhui.R;

/**
 * 作者：czb on 2017/7/12 14:17
 */

public class ProgressUtils {

    private static AlertDialog dialog;

    private ProgressUtils() {
    }

    public static AlertDialog show(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_cancel_form, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public static void dismiss(){
        if(dialog!=null){
            dialog.dismiss();
        }else {
            throw new UnsupportedOperationException("dialog不能为null");
        }

    }

}

