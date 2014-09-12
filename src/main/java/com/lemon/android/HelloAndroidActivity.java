package com.lemon.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lemon.android.model.User;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
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

    @ViewInject(R.id.imageView)
    private ImageView imageView;

    @OnClick(R.id.button)
    public void demo(View view) {
        DbUtils db = DbUtils.create(HelloAndroidActivity.this);
        User u = new User();
        u.setId(1);
        u.setAge(5);
        u.setName("aaa");
        try {
            db.save(u);
            User user = db.findById(User.class, 1);
            textView.setText("inserted new user name is " + user.getName());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.button3)
    public void demo3(View view) {
        BitmapUtils bitmap = new BitmapUtils(this);
        bitmap.display(imageView, "http://bbs.lidroid.com/static/image/common/logo.png");
    }

    private String url = "http://edu.gehealthcare.cn/api/v1/login?mobile=13333333333&password=333333";
//    String url = "http://www.baidu.com";

    @OnClick(R.id.button2)
    public void demo2(View view) {
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.GET,
                url,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        textView.setText("loading...");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        textView.setText(current + "/" + total);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        textView.setText("http response is " + responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        textView.setText("Error in http request " + msg);
                    }
                });
    }

}

