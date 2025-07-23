package com.example.kosanku;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class LaundryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.laundry_fragment, container, false);

        Button submit_btn = view.findViewById(R.id.pickup_btn);

        submit_btn.setOnClickListener(v -> {
            // Munculin fragment pending dulu
            setFragment(new LandingLaundryFragment());

            // Tunggu 5 detik, baru pindah ke fragment tujuan
            new Handler().postDelayed(() -> {
                setFragment(new HomeFragment()); // Ganti ke fragment lain
            }, 5000); // 5000ms = 5 detik
        });


        return view;
    }

    public void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}
