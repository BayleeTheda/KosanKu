// TagihanLaundryFragment.java
package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReportTagihanLaundryFragment extends Fragment {

    public ReportTagihanLaundryFragment() {
        // Required empty public constructor
    }

    // Metode factory standar untuk membuat instance baru dari fragmen
    public static ReportTagihanLaundryFragment newInstance() {
        return new ReportTagihanLaundryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout untuk fragmen ini
        // Pastikan nama file XML-nya sesuai dengan yang Anda berikan
        return inflater.inflate(R.layout.fragment_report_tagihan_laundry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Cari tombol "Bayar" berdasarkan ID
        Button btnBayar = view.findViewById(R.id.BayarButton);

        // Atur OnClickListener untuk tombol "Bayar"
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil metode untuk menampilkan dialog pop-up
                showPaymentSuccessDialog();
            }
        });
    }

    /**
     * Metode pembantu untuk menampilkan PaymentSuccessDialogFragment.
     * Ini membuat kode onClick lebih rapi.
     */
    private void showPaymentSuccessDialog() {
        // Buat instance dari dialog fragment
        PaymentSuccessDialogFragment dialogFragment = new PaymentSuccessDialogFragment();

        // Tampilkan dialog menggunakan getParentFragmentManager()
        dialogFragment.show(getParentFragmentManager(), "payment_success_dialog");
    }
}
