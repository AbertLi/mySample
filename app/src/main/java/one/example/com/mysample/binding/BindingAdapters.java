package one.example.com.mysample.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * 共享的binding adapters
 */
public class BindingAdapters {
    private static final String TAG = "BindingAdapters";

    @BindingAdapter({"title", "label"})
    public static void setSpanText(TextView titleTextView, String title, String label) {

    }
}
