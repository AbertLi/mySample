package one.example.com.mysample;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import one.example.com.mysample.main.ui.MainActivity;
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
        }else{
            Logs.eprintln(TAG, "isTaskRoot=true");
        }
        setContentView(R.layout.activity_splash);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }.start();
    }
}
