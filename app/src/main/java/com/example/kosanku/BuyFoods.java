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
import android.widget.Toast;
import java.util.HashMap;
import android.widget.ImageView;
import java.util.ArrayList;

public class BuyFoods extends Fragment {

    // Mendeklarasikan semua elemen UI yang akan diinteraksikan
    private TextView[] quantityTextViews = new TextView[6];
    private Button[] plusButtons = new Button[6];
    private Button[] minusButtons = new Button[6];
    private TextView textViewTotalPrice;
    private Button buttonPesan;
    private ImageView backButton;

    // Data untuk harga setiap makanan (dalam Rupiah)
    private HashMap<Integer, Double> foodPrices = new HashMap<>();
    // Menyimpan kuantitas setiap makanan
    private int[] foodQuantities = new int[6];

    public BuyFoods() {
        // Konstruktor kosong yang dibutuhkan oleh Android
    }
    private String[] foodNames = {"Rendang", "Tunjang", "Bebek Goreng", "Tumis Kangkung", "Soto", "Jengkol"};



    // Metode utama untuk membuat dan mengembalikan tampilan fragment
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Menggabungkan file layout XML dengan kode Java ini
        View view = inflater.inflate(R.layout.fragment_buy_foods, container, false);

        // Menginisialisasi data harga
        // Perhatikan bahwa index array dimulai dari 0
        foodPrices.put(0, 8000.0); // Harga untuk Rendang
        foodPrices.put(1, 8000.0); // Harga untuk Tunjang
        foodPrices.put(2, 8000.0); // Harga untuk Bebek Goreng
        foodPrices.put(3, 8000.0); // Harga untuk Tumis Kangkung
        foodPrices.put(4, 8000.0); // Harga untuk Soto
        foodPrices.put(5, 8000.0); // Harga untuk Jengkol

        // Menginisialisasi semua elemen UI dari layout
        // Loop ini akan menginisialisasi semua TextView dan Button secara efisien
        for (int i = 0; i < 6; i++) {
            // Mendapatkan ID resource secara dinamis
            int quantityId = getResources().getIdentifier("text_quantity_" + (i + 1), "id", requireContext().getPackageName());
            int plusId = getResources().getIdentifier("button_plus_" + (i + 1), "id", requireContext().getPackageName());
            int minusId = getResources().getIdentifier("button_minus_" + (i + 1), "id", requireContext().getPackageName());

            quantityTextViews[i] = view.findViewById(quantityId);
            plusButtons[i] = view.findViewById(plusId);
            minusButtons[i] = view.findViewById(minusId);

            // Mengatur listener untuk tombol tambah dan kurang
            final int index = i;
            plusButtons[i].setOnClickListener(v -> increaseQuantity(index));
            minusButtons[i].setOnClickListener(v -> decreaseQuantity(index));
        }

        // Menginisialisasi TextView dan Button di bagian bawah layar
        textViewTotalPrice = view.findViewById(R.id.textViewTotalPrice);
        buttonPesan = view.findViewById(R.id.buttonPesan);
        backButton = view.findViewById(R.id.back_arrow); // Pastikan ID ini ada di XML Anda

        // Mengatur listener untuk tombol "Pesan"
        buttonPesan.setOnClickListener(v -> showOrderSummary());

        // Mengatur listener untuk tombol "kembali"
        backButton.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });

        // Memperbarui tampilan harga total awal
        updateTotalPrice();

        return view;
    }

    // Metode untuk menambah kuantitas makanan
    private void increaseQuantity(int index) {
        foodQuantities[index]++;
        quantityTextViews[index].setText(String.valueOf(foodQuantities[index]));
        updateTotalPrice();
    }

    // Metode untuk mengurangi kuantitas makanan
    private void decreaseQuantity(int index) {
        if (foodQuantities[index] > 0) {
            foodQuantities[index]--;
            quantityTextViews[index].setText(String.valueOf(foodQuantities[index]));
            updateTotalPrice();
        }
    }

    // Metode untuk menghitung dan memperbarui total harga
    private void updateTotalPrice() {
        double total = 0.0;
        for (int i = 0; i < foodQuantities.length; i++) {
            if (foodPrices.containsKey(i)) {
                total += foodQuantities[i] * foodPrices.get(i);
            }
        }
        // Menampilkan total harga dengan format mata uang
        textViewTotalPrice.setText(String.format("Rp %,.0f", total));
    }

    // Metode untuk menampilkan ringkasan pesanan saat tombol "Pesan" diklik
    private void showOrderSummary() {
        double total = 0.0;
        ArrayList<String> orderSummary = new ArrayList<>();

        for (int i = 0; i < foodQuantities.length; i++) {
            if (foodQuantities[i] > 0) {
                // Menambahkan item yang dipesan ke list ringkasan
                orderSummary.add(foodNames[i] + " (" + foodQuantities[i] + " pcs)");
                total += foodQuantities[i] * foodPrices.get(i);
            }
        }

        if (total > 0) {
            // Membuat instance PaymentMakananFragment dengan data pesanan
            PaymentMakananFragment paymentFragment = PaymentMakananFragment.newInstance(total, orderSummary);

            // Mengganti fragment saat ini dengan fragment pembayaran
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, paymentFragment) // Pastikan R.id.flFragment adalah container fragment Anda
                    .addToBackStack(null) // Menambahkan ke back stack agar bisa kembali
                    .commit();
        } else {
            Toast.makeText(requireContext(), "Keranjang Anda kosong!", Toast.LENGTH_SHORT).show();
        }
    }
}