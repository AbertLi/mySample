package one.example.com.mysample.utile;

import android.content.Context;
import android.widget.Toast;

public class ToastUtiles {
    public static void toastLong(Context context, String content) {
        Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    public static void toastShort(Context context, String content) {
        Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }
}
