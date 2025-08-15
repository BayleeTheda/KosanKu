package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReportTagihanCSFragment extends Fragment {

    public ReportTagihanCSFragment() {
        // Required empty public constructor
    }

    // Factory method untuk membuat instance baru
    public static ReportTagihanCSFragment newInstance() {
        return new ReportTagihanCSFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout terlebih dahulu
        View view = inflater.inflate(R.layout.report_tagihan_fragment, container, false);

        // Tombol back
        ImageButton back_btn = view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            // Ganti fragment ke HomeFragment
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new PaymentCSFragment())
                    .addToBackStack(null) // supaya bisa kembali lagi
                    .commit();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Tombol "Refresh" / "Bayar"
        Button btnBayar = view.findViewById(R.id.refreshButton);
        btnBayar.setOnClickListener(v -> showPaymentSuccessDialog());
    }

    /**
     * Menampilkan PaymentSuccessDialogFragment.
     */
    private void showPaymentSuccessDialog() {
        PaymentSuccessDialogFragment dialogFragment = new PaymentSuccessDialogFragment();
        dialogFragment.show(getParentFragmentManager(), "payment_success_dialog");
    }
}
