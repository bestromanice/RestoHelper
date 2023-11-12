package com.example.restohelper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import com.example.restohelper.R;
import com.example.restohelper.adapters.CartAdapter;
import com.example.restohelper.models.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CartActivity extends AppCompatActivity {

    Handler handler;

    TextView overAllAmount;
    Toolbar toolbar;
    RecyclerView cartRecyclerView;
    List<CartModel> cartModelList;
    CartAdapter cartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        overAllAmount = findViewById(R.id.cart_total_price_text_view);
        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartModelList);
        cartRecyclerView.setAdapter(cartAdapter);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String documentId = document.getId();

                                CartModel cartModel = document.toObject(CartModel.class);

                                cartModel.setDocumentId(documentId);

                                cartModelList.add(cartModel);
                                cartAdapter.notifyDataSetChanged();
                            }

                            calculateTotalAmount(cartModelList);
                        }
                    }
                });

        handler = new Handler(Looper.getMainLooper());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(() -> {
                    calculateTotalAmount(cartModelList);
                    cartAdapter.notifyDataSetChanged();
                });
            };
        }, 0, 1000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateTotalAmount(cartModelList);
    }

    public void calculateTotalAmount(List<CartModel> cartModelList) {

        int totalAmount = 0;

        for (CartModel cartModel: cartModelList) {
            totalAmount += cartModel.getTotalPrice();
        }

        overAllAmount.setText(String.format("Общая стоимость заказа: %d₽", totalAmount));
    }
}