package com.lemon.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_main)
public class HelloAndroidActivity extends Activity {

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //should use the following code line to initial content view.
        ViewUtils.inject(this);
    }

    @ViewInject(R.id.textView)
    private TextView textView;

    @OnClick(R.id.button)
    public void demo(View view) {
        textView.setText("button is clicked!");
    }

}

