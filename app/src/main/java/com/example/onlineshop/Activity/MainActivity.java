package com.example.onlineshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineshop.Adapter.PopularAdapter;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityMainBinding;
import com.example.onlineshop.domain.PopularDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        // Initialize the RecyclerView
        initRecyclerView();
        bottomNavigation();

        // Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void bottomNavigation() {
        binding.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.blue_dark));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();

        // Add items to the list
        items.add(new PopularDomain(
                "T-shirt black",
                "item_1",
                15,
                4,
                500,
                "Elevate your casual style with this classic Black T-Shirt, designed for comfort and versatility. " +
                        "Made from soft, breathable cotton, this t-shirt offers a relaxed fit with a classic crew neck and short sleeves, making it perfect for year-round wear. " +
                        "Whether you're pairing it with jeans for a laid-back look or layering it under a jacket, this black t-shirt is a timeless wardrobe essential. " +
                        "With its smooth finish and durable fabric, it ensures a comfortable fit throughout the day. Ideal for everyday wear, this t-shirt brings both style and comfort to your collection."
        ));
        items.add(new PopularDomain(
                "Smart Watch",
                "item_2",
                10,
                4.5,
                450,
                "Stay connected and stylish with this sleek smartwatch, designed to complement your active lifestyle. " +
                        "Featuring a vibrant touchscreen display, it offers real-time notifications, fitness tracking, and health monitoring, including heart rate, sleep analysis, and more. " +
                        "Its durable, lightweight design ensures comfort for all-day wear, while water resistance makes it perfect for workouts or outdoor adventures. " +
                        "With customizable watch faces, long battery life, and seamless compatibility with your smartphone, this smartwatch combines functionality and fashion in one ultimate accessory. " +
                        "Perfect for staying productive and healthy on the go."
        ));
        items.add(new PopularDomain(
                "Phone",
                "item_3",
                3,
                4.9,
                800,
                "Experience cutting-edge technology with the sleek and powerful smartphone, crafted for performance and style. " +
                        "Featuring a vibrant, edge-to-edge display for immersive visuals, this phone redefines clarity and detail. " +
                        "Powered by a lightning-fast processor and ample RAM, it ensures seamless multitasking and smooth app performance. " +
                        "Capture stunning photos and videos with its high-resolution camera setup, enhanced by AI features for perfect shots every time. " +
                        "With an all-day battery, fast charging support, and robust build, this smartphone is designed to keep up with your busy lifestyle. " +
                        "A perfect blend of innovation and elegance for the modern user."
        ));

        // Configure the RecyclerView
        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new PopularAdapter(this, items));
    }
}
