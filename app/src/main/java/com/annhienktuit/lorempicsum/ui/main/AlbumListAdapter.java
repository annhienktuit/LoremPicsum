package com.annhienktuit.lorempicsum.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.annhienktuit.lorempicsum.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder>{

    int index = 0;

    Context mContext;

    public AlbumListAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        index++;
        String baseURL = "https://picsum.photos/200/300/?temp=";
        Glide.with(mContext)
                .load(baseURL + HomePresenter.randomNumber())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.photoItem);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView photoItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoItem = itemView.findViewById(R.id.imgAlbumItem);
        }
    }
}
