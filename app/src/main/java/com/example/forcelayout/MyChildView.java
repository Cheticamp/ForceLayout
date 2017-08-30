package com.example.forcelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class MyChildView extends View {

    private int mFlags = 0;
    private boolean mRecord = false;

    public MyChildView(Context context) {
        super(context);
    }

    public MyChildView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyChildView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String text = "onMeasure called (" + getTag() + ")";
        if (getId() == R.id.child_view1) {
            ViewLog.logCall(TAG, ViewLog.CHILD_INDEX, 0x04, text);
        } else {
            ViewLog.logCall(TAG, text);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        String text = "onLayout called (" + getTag() + ")";
        if (getId() == R.id.child_view1) {
            ViewLog.logCall(TAG, ViewLog.CHILD_INDEX, 0x02, text);
        } else {
            ViewLog.logCall(TAG, text);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = "onDraw called (" + getTag() + ")";
        if (getId() == R.id.child_view1) {
            ViewLog.logCall(TAG, ViewLog.CHILD_INDEX, 0x01, text);
        } else {
            ViewLog.logCall(TAG, text);
        }
        super.onDraw(canvas);
    }

    private static final String TAG = "MyChildView";
}