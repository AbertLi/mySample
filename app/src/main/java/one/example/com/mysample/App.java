package one.example.com.mysample;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppInstance.getInstance().setAppContext(this);
    }
}
