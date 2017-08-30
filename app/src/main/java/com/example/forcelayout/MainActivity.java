package com.example.forcelayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ViewGroupGrandparent viewGroupGrandparent;
    ViewGroupParent viewGroupParent;
    MyChildView childView;
    LinearLayout mLayout;
    private int mCombo = 0;
    final Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ViewLog.enableLogging(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buttonClick(View view) {
        Log.i("TAG", "buttonClick: ");
        mCombo = 0;
        resetContentView();
    }

    private void resetContentView() {
        ViewLog.enableLogging(false);
        if (mCombo > 0) {
            Log.i(TAG, getFlagString(mCombo, 6)
                    + getFlagString(ViewLog.getFlags(ViewLog.GRANDPARENT_INDEX), 3)
                    + getFlagString(ViewLog.getFlags(ViewLog.PARENT_INDEX), 3)
                    + getFlagString(ViewLog.getFlags(ViewLog.CHILD_INDEX), 3));
        }
        setContentView(R.layout.activity_main);
        mLayout = (LinearLayout) findViewById(R.id.layout);
        viewGroupGrandparent = (ViewGroupGrandparent) findViewById(R.id.view_group_grandparent);
        viewGroupParent = (ViewGroupParent) findViewById(R.id.view_group_parent);
        childView = (MyChildView) findViewById(R.id.child_view1);

        mLayout.post(new Runnable() {
            @Override
            public void run() {
                ViewLog.enableLogging(true);
                if (++mCombo < 64) {
                    testCombo(mCombo);
                }
            }
        });
    }

    //
    private void testCombo(final int combo) {
        int doLayout;
        int doForce;

        Log.i(TAG, combo + "*******************************************");
        doForce = combo & FORCELAYOUT_MASK;
        if (doForce != 0) {
            if ((doForce & CHILD_FLAG) != 0) {
                childView.forceLayout();
            }
            if ((doForce & PARENT_FLAG) != 0) {
                viewGroupParent.forceLayout();
            }
            if ((doForce & GRANDPARENT_FLAG) != 0) {
                viewGroupGrandparent.forceLayout();
            }
        }

        doLayout = combo >> 3;
        if (doLayout != 0) {
            if ((doLayout & CHILD_FLAG) != 0) {
                childView.requestLayout();
            }
            if ((doLayout & PARENT_FLAG) != 0) {
                viewGroupParent.requestLayout();
            }
            if ((doLayout & GRANDPARENT_FLAG) != 0) {
                viewGroupGrandparent.requestLayout();
            }
        }
        // Do a new combination test after two seconds. That should be more than enough time
        // for the framework to cycle through what it needs to do.
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetContentView();
            }
        }, 2000);
    }

    private String getFlagString(int combo, int bitLength) {
        StringBuilder sb = new StringBuilder("*,");
        int mask = 0x01 << (bitLength - 1);

        for (int i = 0; i < bitLength; i++) {
            sb.append((combo & mask) > 0 ? "X," : ",");
            mask >>= 1;
        }

        return sb.toString();
    }

    private static final int CHILD_FLAG = 0x01;
    private static final int PARENT_FLAG = 0x02;
    private static final int GRANDPARENT_FLAG = 0x04;
    private static final int FORCELAYOUT_MASK = 0x07;

    private static final String TAG = "MainActivity";

}