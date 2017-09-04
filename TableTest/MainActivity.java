package com.example.tabletest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableRow;

/**
 * This sample app simply creates a two-row table on the screen. Each cell is an EditText that
 * can be edited. The screen also includes a check box. If the check box is not checked,
 * table cells will adjust to maintain the correct table strucure as the size of an EditText
 * changes. This is the correct behavior.
 * <p>
 * However, if the check box is checked, then as the content of a cell changes, the table
 * will not maintain is structure if the size of the edited cell changes. This is because,
 * with the check box checked, forceLayout() is disabled for both rows and code to adjust
 * the sizes of the cells is not executed.
 * <p>
 * This test only works if the width of the table does not change when the text view grows in
 * size. A change in the width of the table forces a remeasurement and makes forceLayout() moot.
 * To test this, set the width of the table layout to "wrap_content".
 * <p>
 * Comapanion files: activity_main.xml, MyTableRow.java. Tested on API 25.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((MyTableRow) findViewById(R.id.row1)).disableForceLayout = isChecked;
                ((MyTableRow) findViewById(R.id.row2)).disableForceLayout = isChecked;
            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(checkListener);
    }

}

class MyTableRow extends TableRow {

    public boolean disableForceLayout = false;

    public MyTableRow(Context context) {
        super(context);
    }

    public MyTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void forceLayout() {
        if (disableForceLayout) {
            Log.d(TAG, "<<<<NOT calling forceLayout()");
            return;
        }
        Log.d(TAG, "<<<<calling forceLayout()");
        super.forceLayout();
    }

    private static final String TAG = "MyTableRow";
}
