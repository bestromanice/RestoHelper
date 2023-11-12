package com.example.restohelper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.restohelper.models.DishModel;
import com.example.restohelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class DishPageActivity extends AppCompatActivity {

    ImageView dishImage;
    TextView dishName, dishPrice, dishDescription, dishQuantity;
    Button plusQuantityButton, minusQuantityButton, addToCartButton;

    Toolbar toolbar;

    int totalQuantity = 1;
    int totalPrice = 0;

    DishModel dishModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_page);

        toolbar = findViewById(R.id.dish_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof DishModel) {

            dishModel = (DishModel) obj;
        }

        dishImage = findViewById(R.id.dish_page_image_view);
        dishName = findViewById(R.id.dish_page_name_text_view);
        dishPrice = findViewById(R.id.dish_page_price_text_view);
        dishDescription = findViewById(R.id.dish_page_description_text_view);
        dishQuantity = findViewById(R.id.dish_page_quantity_text_view);

        plusQuantityButton = findViewById(R.id.dish_page_plus_button);
        minusQuantityButton = findViewById(R.id.dish_page_minus_button);

        addToCartButton = findViewById(R.id.dish_page_add_to_cart_button);

        if (dishModel != null) {

            Glide.with(getApplicationContext()).load(dishModel.getImg_url()).into(dishImage);
            dishName.setText(dishModel.getName());
            dishPrice.setText(String.valueOf(dishModel.getPrice()));
            dishDescription.setText(dishModel.getDescription());

            totalPrice = dishModel.getPrice() * totalQuantity;
        }

        addToCartButton.setOnClickListener(v -> addToCart());

        plusQuantityButton.setOnClickListener(v -> {

            if (totalQuantity < 10) {
                totalQuantity += 1;
                dishQuantity.setText(String.valueOf(totalQuantity));

                if (dishModel != null) {
                    totalPrice = dishModel.getPrice() * totalQuantity;
                }
            }
        });

        minusQuantityButton.setOnClickListener(v -> {

            if (totalQuantity > 1) {
                totalQuantity -= 1;
                dishQuantity.setText(String.valueOf(totalQuantity));

                if (dishModel != null) {
                    totalPrice = dishModel.getPrice() * totalQuantity;
                }
            }
        });

    }

    private void addToCart() {

        String saveCurrentTime, saveCurrentDate;
        Calendar calendarForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy");
        saveCurrentDate = currentDate.format(calendarForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calendarForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("dishImage", dishModel.getImg_url());
        cartMap.put("dishName", dishName.getText().toString());
        cartMap.put("dishPrice", dishPrice.getText().toString());
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("totalQuantity", dishQuantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(DishPageActivity.this,
                                "Добавлено к заказу",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}