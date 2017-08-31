package com.example.tabletest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * This sample app simply creates a two-row table on the screen. The screen also includes a
 * check box and a button. Click on the button to make the text view of row 1, column 2 grow
 * in width by 5%. If the check box is not checked, then the bottom row will grow in tandem
 * with the first row. This is the correct behavior.
 * <p>
 * However, if the check box is checked, then the top row will grow but the bottom row will
 * not. This is because, with the check box checked, forceLayout() is disabled for the second
 * row and it is not aware that is should be a different size.
 * <p>
 * This test only works if the width of the table does not change when the text view grows in
 * size. A change in the width of the table forces a remeasurement and makes forceLayout() moot.
 * To test this, set the width of the table layout to "wrap_contents".
 * <p>
 * Comapanion file: activity_main.xml. Tested on API 25.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView1;
    MyTableRow tableRow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        findViewById(R.id.grow).setOnClickListener(this);
        tableRow2 = (MyTableRow) findViewById(R.id.row2);
        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tableRow2.disableForceLayout = isChecked;
            }
        });
    }

    @Override
    public void onClick(View view) {
        textView1.setWidth((int) (textView1.getWidth() * 1.05));
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
