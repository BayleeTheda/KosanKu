package com.example.kosanku;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.flFragment);
//    private RelativeLayout appBar;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set fragment awal
        setCurrentFragment(new HomeFragment(), false);
//        if (savedInstanceState == null) {
//            setCurrentFragment(new HomeFragment(), false);
//        }

        if(currentFragment instanceof HomeFragment){

            // Inisialisasi semua view
            bottomNav = findViewById(R.id.bottomNav);
            ImageView profileIcon;
//            appBar = findViewById(R.id.app_bar);
            ImageView notif_btn = findViewById(R.id.notification_icon);
            profileIcon = findViewById(R.id.profile_icon);

            // Bottom Navigation
            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.home_btn) {
                    setCurrentFragment(new HomeFragment(), false);
                } else if (itemId == R.id.history_btn) {
                    setCurrentFragment(new HistoryFragment(), false);
                }
                return true;
            });

            FloatingActionButton qr_btn = findViewById(R.id.qr_btn);
            qr_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCurrentFragment(new QrFragment(), false);
                }
            });

            // Notifikasi
            notif_btn.setOnClickListener(v -> setCurrentFragment(new NotificationFragment(), true));

            // Profil
            profileIcon.setOnClickListener(v -> setCurrentFragment(new ProfileFragment(), true));

            // Pantau perubahan backstack
//            handleOnBackPressed();
        }
    }

    public void setCurrentFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment instanceof ProfileFragment) {
            showNavigation(false);
        } else {
            showNavigation(true);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragment, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

    public void showNavigation(boolean show) {
//        appBar.setVisibility(show ? View.VISIBLE : View.GONE);
//        bottomNav.setVisibility(show ? View.VISIBLE : View.GONE);
    }

//    private void handleOnBackPressed() {
//        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
//
//            // Jika bukan ProfileFragment, tampilkan navigasi
//            if (!(currentFragment instanceof ProfileFragment)) {
//                BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
//                showNavigation(true);
//
//                // Update selected item di BottomNav
//                if (currentFragment instanceof HomeFragment) {
//                    bottomNav.getMenu().findItem(R.id.home_btn).setChecked(true);
//                } else if (currentFragment instanceof QrFragment) {
//                    bottomNav.getMenu().findItem(R.id.qr_btn).setChecked(true);
//                } else if (currentFragment instanceof HistoryFragment) {
//                    bottomNav.getMenu().findItem(R.id.history_btn).setChecked(true);
//                }
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        // Jika backstack masih ada, popBackStack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed(); // Keluar aplikasi
        }
    }
}
