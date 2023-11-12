package com.example.restohelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.restohelper.activities.DishPageActivity;
import com.example.restohelper.models.DishModel;
import com.example.restohelper.R;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private Context context;
    private List<DishModel> dishes;

    public DishAdapter(Context context, List<DishModel> dishes) {
        this.context = context;
        this.dishes = dishes;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View dishItems = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        return new DishViewHolder(dishItems);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {

        Glide.with(context).load(dishes.get(position).getImg_url()).into(holder.dishImage);
        holder.dishName.setText(dishes.get(position).getName());
        holder.dishPrice.setText(String.valueOf(dishes.get(position).getPrice()));

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, DishPageActivity.class);
            intent.putExtra("detailed", dishes.get(position));
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() { return dishes.size(); }

    public class DishViewHolder extends RecyclerView.ViewHolder {

        ImageView dishImage;
        TextView dishName, dishPrice;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);

            dishImage = itemView.findViewById(R.id.dishImage);
            dishName = itemView.findViewById(R.id.dishName);
            dishPrice = itemView.findViewById(R.id.dishPrice);
        }
    }
}
