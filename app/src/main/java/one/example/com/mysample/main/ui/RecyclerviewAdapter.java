package one.example.com.mysample.main.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import one.example.com.mysample.R;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private String TAG = "RecyclerviewAdapter   ";
    private Context context;
    private List<String> data;

    public RecyclerviewAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(data.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Logs.eprintln(TAG, "这里是点击每一行item的响应事件", "" + position + item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private SimpleDraweeView imageView;
        private SimpleDraweeView icomImageView;
        private TextView icomName;

        private VideoView videoView;

        public ViewHolder(View itemView) {
            super(itemView);
//            title = itemView.findViewById(R.id.name);
        }
    }
}