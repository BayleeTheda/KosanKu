// PaymentSuccessDialogFragment.java
package com.example.kosanku; // Pastikan ini sesuai dengan package Anda

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager; // <-- Tambahkan baris ini
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

public class PaymentSuccessDialogFragment extends DialogFragment {

    public PaymentSuccessDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mengatur gaya dialog agar tidak memiliki title bar default
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.dialog_payment_success, container, false);
        View view = inflater.inflate(R.layout.pembayaran_berhasil_dialog, container, false);

        // Mengatur background transparan untuk dialog
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // Ini penting untuk memastikan latar belakang abu-abu transparan bekerja dengan baik
            // dan bahwa pop-up itu sendiri tidak memiliki background default.
            // Kita akan mengatur warna abu-abu transparan di style.xml
        }

        // Menambahkan listener untuk tombol close
        ImageView ivClose = view.findViewById(R.id.btn_closepopup);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menutup dialog terlebih dahulu
                dismiss();

                // Mengarahkan ke HomeFragment
                // Menggunakan getParentFragmentManager() untuk mengelola fragment dari activity utama
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager != null) {
                    // Membuat instance HomeFragment
                    Fragment homeFragment = new HomeFragment();

                    // Memulai transaksi fragment
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    // Mengganti fragment saat ini dengan HomeFragment
                    transaction.replace(R.id.flFragment, homeFragment);

                    // Menghapus semua fragment dari back stack untuk kembali ke halaman utama
                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    // Melakukan commit pada transaksi
                    transaction.commit();
                }
            }
        });
        return view;
    }

    // Metode ini dipanggil setelah onCreateView dan memastikan dialog ditampilkan dengan benar
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            // Mengatur lebar dan tinggi dialog agar sesuai dengan konten
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}