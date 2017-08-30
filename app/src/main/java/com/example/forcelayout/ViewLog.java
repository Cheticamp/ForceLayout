package com.example.forcelayout;

import android.util.Log;

public class ViewLog {
    private static boolean mLoggingEnabled = false;
    private static int[] mFlags = {0, 0, 0}; // 0-Grandparent, 1-Parent, 2-Child

    public static void logCall(String TAG, int source, int flag, String text) {
        if (mLoggingEnabled) {
            Log.i(TAG, text);
            mFlags[source] |= flag;
        }
    }

    public static void logCall(String TAG, String text) {
        if (mLoggingEnabled) {
            Log.i(TAG, text);
        }
    }

    public static void enableLogging(boolean enabled) {
        if (enabled && !mLoggingEnabled) {
            for (int i = 0; i < mFlags.length; i++) {
                mFlags[i] = 0;
            }
        }
        mLoggingEnabled = enabled;
    }

    public static int getFlags(int source) {
        return mFlags[source];
    }

    public static int GRANDPARENT_INDEX = 0;
    public static int PARENT_INDEX = 1;
    public static int CHILD_INDEX = 2;
}
