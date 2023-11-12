package com.example.restohelper.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.restohelper.R;
import com.example.restohelper.activities.CartActivity;
import com.example.restohelper.activities.SettingsActivity;
import com.example.restohelper.adapters.CategoryAdapter;
import com.example.restohelper.adapters.DishAdapter;
import com.example.restohelper.models.CategoryModel;
import com.example.restohelper.models.DishModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ImageButton openCartButton, openSettingsButton;

    ConstraintLayout constraintLayout;
    ProgressDialog progressDialog;

    RecyclerView categoriesRecyclerView, dishesRecyclerView;

    CategoryAdapter categoryAdapter;
    DishAdapter dishAdapter;
    List<CategoryModel> categories;
    List<DishModel> dishes;

    //FireStore
    FirebaseFirestore db;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progressDialog = new ProgressDialog(getActivity());

        categoriesRecyclerView = root.findViewById(R.id.home_category_recycler_view);
        dishesRecyclerView = root.findViewById(R.id.home_dish_recycler_view);

        db = FirebaseFirestore.getInstance();

        constraintLayout = root.findViewById(R.id.home_constraint_layout);
        constraintLayout.setVisibility(View.GONE);

        progressDialog.setTitle("Добро пожаловать в RestoHelper!");
        progressDialog.setMessage("Пожалуйста, ожидайте...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        openCartButton = root.findViewById(R.id.home_open_cart_activity_image_button);
        openSettingsButton = root.findViewById(R.id.settings_activity);

        openCartButton.setOnClickListener(v -> startActivity(new Intent(getContext(), CartActivity.class)));

        openSettingsButton.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingsActivity.class)));

        // Categories
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(), categories);
        categoriesRecyclerView.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            CategoryModel category = document.toObject(CategoryModel.class);
                            categories.add(category);
                            categoryAdapter.notifyDataSetChanged();
                            constraintLayout.setVisibility(View.VISIBLE);
                            progressDialog.dismiss();
                        }
                    }
                    else {

                        Toast.makeText(getActivity(), " " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Dishes
        dishesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        dishes = new ArrayList<>();
        dishAdapter = new DishAdapter(getActivity(), dishes);
        dishesRecyclerView.setAdapter(dishAdapter);

        db.collection("Dishes")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            DishModel dish = document.toObject(DishModel.class);
                            dishes.add(dish);
                            dishAdapter.notifyDataSetChanged();
                        }
                    }
                    else {

                        Toast.makeText(getActivity(),
                                " " + task.getException(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        return root;
    }
}