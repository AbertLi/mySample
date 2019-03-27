package one.example.com.mysample;

import android.content.Context;

public class AppInstance {
    private Context appContext;
    private static AppInstance mAppInstance = new AppInstance();

    public static AppInstance getInstance() {
        return mAppInstance;
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }
}
