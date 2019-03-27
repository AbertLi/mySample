package one.example.com.mysample;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.concurrent.TimeUnit;

import one.example.com.mysample.main.ui.MainActivity;
import one.example.com.mysample.utile.AppExecutors;
import one.example.com.mysample.utile.Logs;

public class SplashActivity extends FragmentActivity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            Logs.eprintln(TAG, "isTaskRoot=false");
            finish();
            return;
        }
        else {
            Logs.eprintln(TAG, "isTaskRoot=true");
        }
        setContentView(R.layout.activity_splash);

//        Button btn = new Button(this.getApplicationContext());
//        btn.setGravity(View.Cen);
//        LinearLayout.LayoutParams llLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);



        AppExecutors.getNewScheduledThreadPool2().schedule(new Runnable() {
            @Override
            public void run() {
                AppExecutors.getNewScheduledThreadPool2();
                Intent intent = new Intent(SplashActivity.this, DbTestActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
                Logs.eprintln(TAG, "isMainThread=" + AppExecutors.isMainThread());
            }
        }, 1, TimeUnit.SECONDS);//停顿1s
    }
}
