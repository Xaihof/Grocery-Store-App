package com.xoksis.mygrocerystore.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.xoksis.mygrocerystore.R;
import com.xoksis.mygrocerystore.databinding.ActivityMainBinding;
import com.xoksis.mygrocerystore.fragments.CategoryFragment;
import com.xoksis.mygrocerystore.fragments.HomeFragment;
import com.xoksis.mygrocerystore.fragments.MyCartsFragment;
import com.xoksis.mygrocerystore.fragments.MyOrdersFragment;
import com.xoksis.mygrocerystore.fragments.NewProductsFragment;
import com.xoksis.mygrocerystore.fragments.OffersFragment;
import com.xoksis.mygrocerystore.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    FirebaseAuth auth;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        auth = FirebaseAuth.getInstance();

        loadFragments(new HomeFragment());

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);


        // Setup toolbar.
        setSupportActionBar(toolbar);

        // action bar toggle reference variable;
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        // setting actionBarToggle on drawerlistener on drawerLayout.
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // synchronizing open and closed state for actionBarDrawerToggle.
        actionBarDrawerToggle.syncState();

        // setting itemSelectedClickListener on NavigationView.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.nav_Home) {

                    loadFragments(new HomeFragment());

                } else if (id == R.id.nav_Profile) {

                    loadFragments(new ProfileFragment());

                } else if (id == R.id.nav_Category) {

                    loadFragments(new CategoryFragment());

                } else if (id == R.id.nav_Offers) {

                    loadFragments(new OffersFragment());

                } else if (id == R.id.nav_NewProducts) {

                    loadFragments(new NewProductsFragment());

                } else if (id == R.id.nav_MyOrders) {

                    loadFragments(new MyOrdersFragment());

                } else {

                    loadFragments(new MyCartsFragment());

                }

                // Closing drawer after any item pressed from drawer.
                drawerLayout.closeDrawer(GravityCompat.START);

                //setting it true
                return true;
            }
        });


    }

    // userDefined fuction.
    private void loadFragments(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment, null)
                .commit();
    }
}