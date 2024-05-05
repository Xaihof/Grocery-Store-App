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
import com.xoksis.mygrocerystore.databinding.PopularItemBinding;
import com.xoksis.mygrocerystore.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.itemBinding.popImg);
        holder.itemBinding.popName.setText(popularModelList.get(position).getName());
        holder.itemBinding.popRating.setText(popularModelList.get(position).getRating());
        holder.itemBinding.popDes.setText(popularModelList.get(position).getDescription());
        holder.itemBinding.popDiscount.setText(popularModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ViewAllActivity.class);
            intent.putExtra("type", popularModelList.get(position).getType());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        PopularItemBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = PopularItemBinding.bind(itemView);
        }
    }
}
