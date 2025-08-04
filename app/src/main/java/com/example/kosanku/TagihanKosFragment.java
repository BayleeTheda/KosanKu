package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TagihanKosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_tagihan_kos_fragment, container, false);

        // Tombol back (ImageButton)
        ImageButton backButton = view.findViewById(R.id.back_btn);

        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                // Kembali ke fragment yang ada di backstack sebelumnya
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
