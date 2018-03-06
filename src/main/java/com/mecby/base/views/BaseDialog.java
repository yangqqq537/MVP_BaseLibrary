package com.mecby.base.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mecby.base.R;

/**
 * Author:Jerry
 * Time:2017/10/23 0023   9:59
 * Des:仿ios的基本dialog
 */

public class BaseDialog {
    private Dialog mDialog;
    protected Context mContext;
    protected TextView mMessage;
    protected TextView mPositiveBtnText;
    protected TextView mNegativeBtnText;
    protected DialogInterface.OnClickListener mPositiveBtnClickListener;
    protected DialogInterface.OnClickListener mNegativeBtnClickListener;

    public BaseDialog(Context context) {
        mContext = context;
    }

    /**
     * 创建BaseDialog实例
     *
     * @return
     */

    public BaseDialog builder() {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDialog = new Dialog(mContext, R.style.normalDialog);
        //设置dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        mDialog.setCanceledOnTouchOutside(false);
        View layout = inflater.inflate(R.layout.dialog_normal_layout, null);

        mDialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        //设置Dialog中展示的msg
        mMessage = (TextView) layout.findViewById(R.id.txtMsg);
        //设置确认按钮的处理事件
        mPositiveBtnText = (TextView) layout.findViewById(R.id.txtSubmit);
        mPositiveBtnText.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                if (mPositiveBtnClickListener != null) {
                    mPositiveBtnClickListener.onClick(mDialog, DialogInterface.BUTTON_POSITIVE);

                }

            }

        });

        mNegativeBtnText = (TextView) layout.findViewById(R.id.txtCancle);
        mNegativeBtnText.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                if (mNegativeBtnClickListener != null) {
                    mNegativeBtnClickListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);

                }

            }

        });
        mDialog.setContentView(layout);

        return this;

    }

    /**
     * 设置Msg
     * 支持Resource设置
     *
     * @param message
     */
    public BaseDialog setMessage(int message) {
        mMessage.setText(mContext.getText(message).toString());
        return this;
    }

    /**
     * @param message
     */
    public BaseDialog setMessage(String message) {
        mMessage.setText(message);
        return this;
    }


    public BaseDialog setPositiveButton(int positiveBtnText, DialogInterface.OnClickListener listener) {
        mPositiveBtnText.setText(mContext.getText(positiveBtnText));
        mPositiveBtnClickListener = listener;
        return this;
    }

    public BaseDialog setPositiveButton(String positiveBtnText, DialogInterface.OnClickListener listener) {
        mPositiveBtnText.setText(positiveBtnText);
        mPositiveBtnClickListener = listener;
        return this;
    }


    public BaseDialog setNegativeButton(int negativeBtnText, DialogInterface.OnClickListener listener) {
        mNegativeBtnText.setText(mContext.getText(negativeBtnText));
        mNegativeBtnClickListener = listener;
        return this;
    }


    public BaseDialog setNegativeButton(String negativeBtnText, DialogInterface.OnClickListener listener) {
        mNegativeBtnText.setText(negativeBtnText);
        mNegativeBtnClickListener = listener;
        return this;
    }


    public void show() {
        mDialog.show();
    }
}
