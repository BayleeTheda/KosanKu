package com.example.kosanku;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ImageView profileIcon;
    private View appBar; // Deklarasi untuk App Bar
    private BottomNavigationView bottomNav; // Deklarasi untuk Bottom Nav

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi semua view
        bottomNav = findViewById(R.id.bottomNav);
        appBar = findViewById(R.id.app_bar); // Inisialisasi App Bar
        ImageView notif_btn = findViewById(R.id.notification_icon);
        profileIcon = findViewById(R.id.profile_icon);

        // Set fragment awal jika activity baru dibuat
        if (savedInstanceState == null) {
            setCurrentFragment(new HomeFragment(), false);
            bottomNav.setSelectedItemId(R.id.home_btn);
        }

        // Listener untuk Bottom Navigation
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home_btn) {
                setCurrentFragment(new HomeFragment(), false);
            } else if (itemId == R.id.qr_btn) {
                setCurrentFragment(new QrFragment(), false);
            } else if (itemId == R.id.history_btn) {
                setCurrentFragment(new HistoryFragment(), false);
            }
            return true;
        });

        // Listener untuk tombol notifikasi
        notif_btn.setOnClickListener(v -> {
            setCurrentFragment(new NotificationFragment(), true);
        });

        // Listener untuk tombol profile
        profileIcon.setOnClickListener(v -> {
            setCurrentFragment(new ProfileFragment(), true);
        });

        // Listener untuk menangani tombol 'kembali'
        handleOnBackPressed();
    }

    private void setCurrentFragment(Fragment fragment, boolean addToBackStack) {
        // Cek jenis fragment untuk menampilkan/menyembunyikan navigasi
        if (fragment instanceof ProfileFragment) {
            showNavigation(false); // Sembunyikan jika ProfileFragment
        } else {
            showNavigation(true); // Tampilkan untuk fragment lain
        }

        // Lakukan transaksi fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragment, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

    // Method baru untuk mengatur visibilitas navigasi
    private void showNavigation(boolean show) {
        if (show) {
            appBar.setVisibility(View.VISIBLE);
            bottomNav.setVisibility(View.VISIBLE);
        } else {
            appBar.setVisibility(View.GONE);
            bottomNav.setVisibility(View.GONE);
        }
    }

    // Method untuk memastikan navigasi muncul kembali saat tombol back ditekan
    private void handleOnBackPressed() {
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.flFragment);
            if (!(currentFragment instanceof ProfileFragment)) {
                // Jika fragment yang tampil BUKAN ProfileFragment, pastikan navigasi terlihat
                showNavigation(true);

                // Update juga item yang aktif di bottom nav
                if (currentFragment instanceof HomeFragment) {
                    bottomNav.getMenu().findItem(R.id.home_btn).setChecked(true);
                } else if (currentFragment instanceof QrFragment) {
                    bottomNav.getMenu().findItem(R.id.qr_btn).setChecked(true);
                } else if (currentFragment instanceof HistoryFragment) {
                    bottomNav.getMenu().findItem(R.id.history_btn).setChecked(true);
                }
            }
        });
    }
}