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
import com.xoksis.mygrocerystore.activities.DetailedActivity;
import com.xoksis.mygrocerystore.databinding.ViewAllItemBinding;
import com.xoksis.mygrocerystore.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> list;

    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Glide.with(context).load(list.get(position).getImg_url()).into(holder.itemBinding.viewImg);
        holder.itemBinding.viewName.setText(list.get(position).getName());
        holder.itemBinding.viewDescription.setText(list.get(position).getDescription());
        holder.itemBinding.viewRating.setText(list.get(position).getRating());
        holder.itemBinding.viewPrice.setText(list.get(position).getPrice() + "/kg");

        if (list.get(position).getType().equals("egg")) {
            holder.itemBinding.viewPrice.setText(list.get(position).getPrice() + "/dozen");

        }
        if (list.get(position).getType().equals("milk")) {
            holder.itemBinding.viewPrice.setText(list.get(position).getPrice() + "/liter");

        }

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra("detail",list.get(position));
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewAllItemBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = ViewAllItemBinding.bind(itemView);
        }
    }
}
