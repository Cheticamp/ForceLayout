package com.example.forcelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ViewGroupParent extends LinearLayout {
    private int mFlags = 0;
    private boolean mRecord = false;

    public ViewGroupParent(Context context) {
        super(context);
    }

    public ViewGroupParent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ViewLog.logCall(TAG, ViewLog.PARENT_INDEX, 0x04, "onMeasure called");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        ViewLog.logCall(TAG, ViewLog.PARENT_INDEX, 0x02, "onLayout called");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ViewLog.logCall(TAG, ViewLog.PARENT_INDEX, 0x01, "onDraw called");
        super.onDraw(canvas);
    }

    private static final String TAG = "ViewGroupParent";
}