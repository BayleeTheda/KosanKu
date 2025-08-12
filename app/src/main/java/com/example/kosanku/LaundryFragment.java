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
import androidx.fragment.app.FragmentManager;

public class LaundryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.laundry_fragment, container, false);

        Button btnKirim = view.findViewById(R.id.pickup_btn);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Ganti ke LandingLaundryFragment
                fragmentManager.beginTransaction()
                        .replace(android.R.id.content, new LandingLaundryFragment())
                        .commit();

                // Delay 5 detik, lalu ganti ke PaymentLaundryFragment
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fragmentManager.beginTransaction()
                                .replace(android.R.id.content, new LaundryFragment())
                                .commit();
                    }
                }, 5000);
            }
        });





        return view;
    }

    public void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}
