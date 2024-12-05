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
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ViewholderPupListBinding;
import com.example.onlineshop.domain.PopularDomain;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private final ArrayList<PopularDomain> items;
    private Context context;

    // Constructor to initialize context and items
    public PopularAdapter(Context context, ArrayList<PopularDomain> items) {
        this.context = context; // Assign the passed context
        this.items = items != null ? items : new ArrayList<>(); // Handle null items
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderPupListBinding binding = ViewholderPupListBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new ViewHolder(binding);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularDomain currentItem = items.get(position);

        // Bind data to the view
        holder.binding.titleTxt.setText(currentItem.getTitle());
        holder.binding.feeTxt.setText(String.format("$%.2f", currentItem.getPrice()));
        holder.binding.scoretxt.setText(String.valueOf(currentItem.getScore()));

        holder.binding.reviewTxt.setText(String.format("%d", items.get(position).getReview()));
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

        // Set click listener for item
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object",items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder class to hold item views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPupListBinding binding;

        public ViewHolder(ViewholderPupListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
