package com.xoksis.mygrocerystore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.xoksis.mygrocerystore.adapters.MyCartAdapter;
import com.xoksis.mygrocerystore.databinding.FragmentMyCartsBinding;
import com.xoksis.mygrocerystore.models.MyCartModel;

import java.util.ArrayList;
import java.util.List;

public class MyCartsFragment extends Fragment {

    FragmentMyCartsBinding myCartsBinding;

    FirebaseFirestore db;
    FirebaseAuth auth;

    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;

    public MyCartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myCartsBinding = FragmentMyCartsBinding.inflate(inflater, container, false);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        myCartsBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(),cartModelList);
        myCartsBinding.recyclerview.setAdapter(cartAdapter);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);
                                cartModelList.add(cartModel);
                                cartAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                });



        return myCartsBinding.getRoot();
    }
}