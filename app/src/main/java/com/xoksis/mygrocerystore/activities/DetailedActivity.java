package com.xoksis.mygrocerystore.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xoksis.mygrocerystore.databinding.ActivityDetailedBinding;
import com.xoksis.mygrocerystore.models.ViewAllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding detailedBinding;

    ViewAllModel viewAllModel = null;

    int totalQuantity = 1, totalPrice = 0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailedBinding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(detailedBinding.getRoot());

        setSupportActionBar(detailedBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");

        if (object instanceof ViewAllModel) {

            viewAllModel = (ViewAllModel) object;

        }


        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedBinding.detailedImg);
            detailedBinding.detailedRating.setText(viewAllModel.getRating());
            detailedBinding.detailedDec.setText(viewAllModel.getDescription());
            detailedBinding.detailedPrice.setText("Price :$" + viewAllModel.getPrice() + "/kg");

            totalPrice = viewAllModel.getPrice() * totalQuantity;

            if (viewAllModel.getType().equals("egg")) {
                detailedBinding.detailedPrice.setText("Price :" + viewAllModel.getPrice() + "/dozen");
                totalPrice = viewAllModel.getPrice() * totalQuantity;

            }
            if (viewAllModel.getType().equals("milk")) {
                detailedBinding.detailedPrice.setText("Price :" + viewAllModel.getPrice() + "/liter");
                totalPrice = viewAllModel.getPrice() * totalQuantity;

            }

        }

        detailedBinding.addItem.setOnClickListener(v -> {

            if (totalQuantity < 10) {

                totalQuantity++;
                detailedBinding.quantity.setText(String.valueOf(totalQuantity));
                totalPrice = viewAllModel.getPrice() * totalQuantity;

            }

        });

        detailedBinding.removeItem.setOnClickListener(v -> {

            if (totalQuantity > 1) {

                totalQuantity--;
                detailedBinding.quantity.setText(String.valueOf(totalQuantity));
                totalPrice = viewAllModel.getPrice() * totalQuantity;

            }

        });

        detailedBinding.addToCart.setOnClickListener(v -> {

            String saveCurrentDate, saveCurrentTime;
            Calendar calForDate = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
            saveCurrentDate = currentDate.format(calForDate.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calForDate.getTime());

            final HashMap<String, Object> cartMap = new HashMap<>();

            cartMap.put("productName", viewAllModel.getName());
            cartMap.put("productPrice", viewAllModel.getPrice());
            cartMap.put("currentDate", saveCurrentDate);
            cartMap.put("currentTime", saveCurrentTime);
            cartMap.put("totalQuantity", detailedBinding.quantity.getText().toString());
            cartMap.put("totalPrice", totalPrice);

            firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            Toast.makeText(DetailedActivity.this, "Added To A Cart", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    });
        });

    }
}