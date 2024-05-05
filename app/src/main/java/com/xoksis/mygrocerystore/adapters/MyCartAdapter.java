package com.xoksis.mygrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xoksis.mygrocerystore.R;
import com.xoksis.mygrocerystore.databinding.MyCartItemBinding;
import com.xoksis.mygrocerystore.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> cartModelList;

    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemBinding.productName.setText(cartModelList.get(position).getProductName());
        holder.itemBinding.productPrice.setText(cartModelList.get(position).getProductPrice());
        holder.itemBinding.currentDate.setText(cartModelList.get(position).getCurrentDate());
        holder.itemBinding.currentTime.setText(cartModelList.get(position).getCurrentTime());
        holder.itemBinding.totalQuantity.setText(cartModelList.get(position).getTotalQuantity());
        holder.itemBinding.totalPrice.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MyCartItemBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = MyCartItemBinding.bind(itemView);
        }
    }
}
