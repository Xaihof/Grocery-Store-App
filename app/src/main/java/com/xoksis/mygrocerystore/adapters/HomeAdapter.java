package com.xoksis.mygrocerystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xoksis.mygrocerystore.R;
import com.xoksis.mygrocerystore.activities.ViewAllActivity;
import com.xoksis.mygrocerystore.databinding.HomeCatItemsBinding;
import com.xoksis.mygrocerystore.models.HomeCategory;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    List<HomeCategory> categoryList;

    public HomeAdapter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(categoryList.get(position).getImg_url()).into(holder.itemsBinding.homeCatImg);
        holder.itemsBinding.homeCatName.setText(categoryList.get(position).getName());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ViewAllActivity.class);
            intent.putExtra("type", categoryList.get(position).getType());
            context.startActivity(intent);
        });




    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        HomeCatItemsBinding itemsBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemsBinding = HomeCatItemsBinding.bind(itemView);
        }
    }
}
