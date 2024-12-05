package com.example.onlineshop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.onlineshop.Activity.DetailActivity;
import com.example.onlineshop.Helper.ChangeNumberItemsListener;
import com.example.onlineshop.Helper.ManagmentCart;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ViewholderCartBinding;
import com.example.onlineshop.databinding.ViewholderPupListBinding;
import com.example.onlineshop.domain.PopularDomain;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final ArrayList<PopularDomain> items;
    private Context context;
    ChangeNumberItemsListener changeNumberItemsListener;
    ManagmentCart managmentCart;

    // Constructor to initialize context and items


    public CartAdapter(ArrayList<PopularDomain> items, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        managmentCart=new ManagmentCart(context);
        return new ViewHolder(binding);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularDomain currentItem = items.get(position);

        // Bind data to the view
        holder.binding.titleTxt.setText(currentItem.getTitle());
        holder.binding.feeEachItem.setText(String.format("$%s", items.get(position).getPrice()));
        holder.binding.totalEachItem.setText(String.format("$%s", items.get(position).getNumberInCart() * items.get(position).getPrice()));
        holder.binding.numberItemTxt.setText(String.valueOf(items.get(position).getNumberInCart()));

        @SuppressLint("DiscouragedApi")
        int drawableResource = holder.itemView.getResources().getIdentifier(
                currentItem.getPicUrl(), "drawable", holder.itemView.getContext().getPackageName()
        );

        // Fallback to a default image if resource is not found
        if (drawableResource == 0) {
            drawableResource = R.drawable.blue_background;
        }

        // Load the image using Glide with rounded corners
        Glide.with(context)
                .load(drawableResource)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.binding.pic);


        holder.binding.plusCartBtn.setOnClickListener(v -> managmentCart.plusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));

        holder.binding.minusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.minusNumberItem(items, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder class to hold item views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderCartBinding binding;

        public ViewHolder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
