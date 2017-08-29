package com.example.forcelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class ViewGroupGrandparent extends LinearLayout {
    private int mFlags = 0;
    private boolean mRecord = false;

    public ViewGroupGrandparent(Context context) {
        super(context);
    }

    public ViewGroupGrandparent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupGrandparent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        logCall(0x04, "onMeasure called");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        logCall(0x02, "onLayout called");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        logCall(0x01, "onDraw called");
    }

    public void logCall(int flag, String text) {
        if (mRecord) {
            mFlags |= flag;
            Log.i(TAG, text);
        }
    }

    public int getFlags() {
        return mFlags;
    }

    public void setRecord() {
        mRecord = true;
    }

    private static final String TAG = "ViewGroupGrandparent";
}