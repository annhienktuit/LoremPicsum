package com.annhienktuit.lorempicsum.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.annhienktuit.lorempicsum.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Random;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> {

    int index = 0;

    Context mContext;

    public AlbumListAdapter(Context context) {
        this.mContext = context;
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        index++;
        int[] heightList = {200, 250, 300};
        int height = getRandom(heightList);
        holder.cardViewItem.getLayoutParams().height = dpToPx(height, mContext);
        String baseURL = "https://picsum.photos/200/" + height + "/?temp=";
        Glide.with(mContext)
                .load(baseURL + HomePresenterImpl.randomNumber())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.photoItem);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView photoItem;
        private CardView cardViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoItem = itemView.findViewById(R.id.imgAlbumItem);
            cardViewItem = itemView.findViewById(R.id.cardViewItem);
        }
    }
}
