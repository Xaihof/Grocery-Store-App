package com.xoksis.mygrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xoksis.mygrocerystore.R;
import com.xoksis.mygrocerystore.databinding.NavCatItemBinding;
import com.xoksis.mygrocerystore.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {


    Context context;
    List<NavCategoryModel> list;

    public NavCategoryAdapter(Context context, List<NavCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.itemBinding.catNavImg);
        holder.itemBinding.catNavName.setText(list.get(position).getName());
        holder.itemBinding.catNavDescription.setText(list.get(position).getDescription());
        holder.itemBinding.catNavDiscount.setText(list.get(position).getDiscount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        NavCatItemBinding itemBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = NavCatItemBinding.bind(itemView);
        }
    }
}
