package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QrFragment extends Fragment {

    private BottomNavigationView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qr_fragment, container, false);

        //WARNING
        // Inisialisasi semua view
        bottomNav = view.findViewById(R.id.bottomNav);
        ImageView profileIcon;
//            appBar = findViewById(R.id.app_bar);
        ImageView notif_btn = view.findViewById(R.id.notification_icon);
        profileIcon = view.findViewById(R.id.profile_icon);

        // Bottom Navigation
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home_btn) {
                setFragment(new HomeFragment());
            } else if (itemId == R.id.history_btn) {
                setFragment(new HistoryFragment());
            }
            return true;
        });

        FloatingActionButton qr_btn = view.findViewById(R.id.qr_btn);
        qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new QrFragment());
            }
        });

        // Notifikasi
        notif_btn.setOnClickListener(v -> setFragment(new NotificationFragment()));

        // Profil
        profileIcon.setOnClickListener(v -> setFragment(new ProfileFragment()));

        return view;
    }

    public void setFragment(Fragment fragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
