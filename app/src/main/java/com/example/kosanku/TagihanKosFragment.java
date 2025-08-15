package com.example.kosanku;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TagihanKosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment_tagihan_kos_fragment, container, false);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Tombol Bayar
        Button btnKirim = view.findViewById(R.id.bayar_btn);
        btnKirim.setOnClickListener(v -> {
            // Langsung ganti ke LandingLaundryFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.flFragment, new ReportTagihanKosFragment())
                    .addToBackStack(null) // opsional, supaya bisa kembali
                    .commit();

        });

        // Tombol Back
        ImageButton back_btn = view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack(); // kembali ke fragment sebelumnya
            }
        });

        return view;
    }
}
