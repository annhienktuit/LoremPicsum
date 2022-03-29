package com.annhienktuit.lorempicsum.ui.favorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.annhienktuit.lorempicsum.BaseActivity;
import com.annhienktuit.lorempicsum.R;
import com.annhienktuit.lorempicsum.databinding.ActivityFavoriteBinding;
import com.annhienktuit.lorempicsum.databinding.ActivityHomeBinding;
import com.annhienktuit.lorempicsum.db.DatabaseHandler;

public class FavoriteActivity extends BaseActivity implements FavoriteView {

    ActivityFavoriteBinding binding;
    
    RecyclerView recyclerViewFavorite;

    TextView tvFavoriteQuantity;

    DatabaseHandler dbHandler;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHandler = new DatabaseHandler(this);
        bindView();
        loadRecyclerView();
        tvFavoriteQuantity.setText("You loved " + dbHandler.countItem() + " photos");
    }

    private void loadRecyclerView() {
        FavoriteListAdapter adapter = new FavoriteListAdapter(this, dbHandler.getAllFavoritePhoto());
        recyclerViewFavorite.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerViewFavorite.setAdapter(adapter);
    }

    private void bindView() {
        recyclerViewFavorite = binding.recyclerViewFavoriteList;
        tvFavoriteQuantity = binding.tvFavoriteQuantity;
    }
}