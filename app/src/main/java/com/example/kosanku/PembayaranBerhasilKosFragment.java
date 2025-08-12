package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class PembayaranBerhasilKosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment_berhasil_tagihan_kos_fragment, container, false);

        // Tombol X (close) di popup
        ImageButton closeBtn = view.findViewById(R.id.close_btn); // pastikan id di XML adalah close_btn
        closeBtn.setOnClickListener(v -> {
            Fragment homeFragment = new HomeFragment(); // Ganti dengan fragment home kamu
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.flFragment, homeFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }


}
