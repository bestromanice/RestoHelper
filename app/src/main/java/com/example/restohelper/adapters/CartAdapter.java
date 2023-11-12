package com.example.restohelper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.restohelper.R;
import com.example.restohelper.models.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartModel> cartModelList;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View cartItems = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.CartViewHolder(cartItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Glide.with(context).load(cartModelList.get(position).getDishImage()).into(holder.image);
        holder.name.setText(cartModelList.get(position).getDishName());
        holder.price.setText(cartModelList.get(position).getDishPrice());
        holder.totalQuantity.setText(cartModelList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));

        holder.date.setText(cartModelList.get(position).getCurrentDate());
        holder.time.setText(cartModelList.get(position).getCurrentTime());

        holder.deleteItem.setOnClickListener(v -> firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User")
                .document(cartModelList.get(position).getDocumentId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            cartModelList.remove(cartModelList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Позиция удалена", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Ошибка: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public static final class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price, totalQuantity, totalPrice, date, time;
        Button deleteItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.cart_dish_image_view);
            name = itemView.findViewById(R.id.cart_ordered_dish_name_text_view);
            price = itemView.findViewById(R.id.cart_ordered_dish_price_text_view);
            totalQuantity = itemView.findViewById(R.id.cart_ordered_dish_quantity_text_view);
            totalPrice = itemView.findViewById(R.id.cart_ordered_dish_total_price_text_view);
            date = itemView.findViewById(R.id.cart_ordered_date_text_view);
            time = itemView.findViewById(R.id.cart_ordered_time_text_view);

            deleteItem = itemView.findViewById(R.id.cart_remove_button);
        }
    }
}