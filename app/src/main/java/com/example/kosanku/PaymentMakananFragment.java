package com.example.kosanku;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;


import java.util.ArrayList;

public class PaymentMakananFragment extends Fragment {

    // Kunci untuk Bundle (untuk menerima data)
    public static final String ARG_TOTAL_PRICE = "total_price";
    public static final String ARG_ORDER_SUMMARY = "order_summary";

    private TextView tvTotalHarga;
    private ImageButton backBtn;
    private Button bayarButton;

    public PaymentMakananFragment() {
        // Konstruktor kosong
    }

    // Metode factory untuk membuat instance fragment dengan data
    public static PaymentMakananFragment newInstance(double totalPrice, ArrayList<String> orderSummary) {
        PaymentMakananFragment fragment = new PaymentMakananFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_TOTAL_PRICE, totalPrice);
        args.putStringArrayList(ARG_ORDER_SUMMARY, orderSummary);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Menggabungkan layout XML
        View view = inflater.inflate(R.layout.payment_makanan_fragment, container, false);

        // Inisialisasi view
        tvTotalHarga = view.findViewById(R.id.total_harga_makanan); // <-- Anda perlu menambahkan ID ini di XML
        backBtn = view.findViewById(R.id.back_btn);
        bayarButton = view.findViewById(R.id.BayarButtonMakanan);

        // Menangani tombol kembali
        backBtn.setOnClickListener(v -> {
            if (getParentFragmentManager() != null) {
                getParentFragmentManager().popBackStack();
            }
        });

        bayarButton.setOnClickListener(v -> {
            // Logika untuk beralih ke LandingLaundryFragment
            if (getParentFragmentManager() != null) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, new ReportTagihanMakanFragment()) // Ganti fragment
                        .addToBackStack(null) // Tambahkan ke back stack
                        .commit();
            }
        });

        // Mengambil data dari Bundle
        if (getArguments() != null) {
            double totalHarga = getArguments().getDouble(ARG_TOTAL_PRICE, 0.0);
            ArrayList<String> orderSummary = getArguments().getStringArrayList(ARG_ORDER_SUMMARY);

            // Menampilkan total harga
            tvTotalHarga.setText(String.format("Rp %,.0f", totalHarga));

            // TODO: Di sini Anda perlu memproses dan menampilkan orderSummary di layout
            // Untuk layout XML Anda, ini akan menjadi bagian yang rumit karena layout
            // detail itemnya statis (Rendang, Tunjang, dll).
            // Idealnya, Anda menggunakan RecyclerView di sini untuk menampilkan daftar item secara dinamis.
        }

        return view;
    }
}