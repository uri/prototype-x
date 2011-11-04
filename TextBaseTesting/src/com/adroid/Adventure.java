package com.adroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Adventure extends Activity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String msg = "This is an example of how text can be displayed in an interesting way.";
        String str = "test";
        TextView tv = new TextView(this);
        setContentView(tv);
        
        tv.setTextSize(34f);
        tv.setText(str);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        tv.setText(msg);
        
        
    }
}