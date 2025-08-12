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

public class ReportTagihanFragment extends Fragment {

    private static final String ARG_PAYMENT_TYPE = "payment_type";

    public static ReportTagihanFragment newInstance(String paymentType) {
        ReportTagihanFragment fragment = new ReportTagihanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PAYMENT_TYPE, paymentType);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.report_tagihan_fragment, container, false);

        // Tombol back
        ImageButton backBtn = view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // Ambil tipe pembayaran dari argument
        String paymentType = "";
        if (getArguments() != null) {
            paymentType = getArguments().getString(ARG_PAYMENT_TYPE, "");
        }

        String finalPaymentType = paymentType;

        // Tombol Refresh → pindah ke fragment sesuai tipe pembayaran
        AppCompatButton refreshButton = view.findViewById(R.id.backButton);
        refreshButton.setOnClickListener(v -> {
            Fragment tujuan;

            if ("kos".equalsIgnoreCase(finalPaymentType)) {
                tujuan = new PembayaranBerhasilKosFragment();
            } else if ("cs".equalsIgnoreCase(finalPaymentType)) {
                tujuan = new PembayaranBerhasilCSFragment();
            } else {
                tujuan = new PembayaranBerhasilCSFragment(); // default
            }

            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.flFragment, tujuan);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }
}
