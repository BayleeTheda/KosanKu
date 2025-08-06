package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class PaymentCSFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment_cs_fragment, container, false);

        // Tombol Back
        ImageButton backBtn = view.findViewById(R.id.back_btn);
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });
        }

        // Tombol Refresh → ke ReportTagihan
        AppCompatButton refreshBtn = view.findViewById(R.id.backButton);
        if (refreshBtn != null) {
            refreshBtn.setOnClickListener(v -> {
                if (getActivity() != null) {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.flFragment, new ReportTagihanFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        return view;
    }
}
