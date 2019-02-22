package one.example.com.mysample;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import one.example.com.mysample.utile.ToastUtiles;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            }
            else {
                ToastUtiles.toastShort(this,
                        this.getApplicationContext().getResources().getString(R.string.agineBacktoast));
                firstTime = System.currentTimeMillis();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
