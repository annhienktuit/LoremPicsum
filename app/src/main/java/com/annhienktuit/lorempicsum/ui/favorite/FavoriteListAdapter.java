package com.annhienktuit.lorempicsum.ui.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.annhienktuit.lorempicsum.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.annhienktuit.lorempicsum.db.DatabaseHandler;
import com.annhienktuit.lorempicsum.models.Photo;
import com.annhienktuit.lorempicsum.ui.main.AlbumListAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Nhien Nguyen on 3/29/2022
 */
public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {

    Context mContext;
    List<Photo> favoritePhotoList;

    public FavoriteListAdapter(Context context, List<Photo> favoritePhotoList){
        this.mContext = context;
        this.favoritePhotoList = favoritePhotoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_favorite_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvFavoriteAuthor.setText(favoritePhotoList.get(position).getAuthor());
        String id = favoritePhotoList.get(position).getId();
        Glide.with(mContext)
                .load("https://picsum.photos/id/" + id + "/200/250")
                .centerCrop()
                .into(holder.imgFavorite);
    }

    @Override
    public int getItemCount() {
        return favoritePhotoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgFavorite;

        TextView tvFavoriteAuthor;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgFavorite = itemView.findViewById(R.id.imgFavoriteItem);
            tvFavoriteAuthor = itemView.findViewById(R.id.tvFavoriteAuthor);
        }

    }
}
