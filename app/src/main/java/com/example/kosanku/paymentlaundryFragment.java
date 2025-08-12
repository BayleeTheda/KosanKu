package com.example.kosanku;

import android.os.Bundle;

import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager; // Import FragmentManager
import androidx.fragment.app.FragmentTransaction; // Import FragmentTransaction
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // Import Button

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link paymentlaundryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class paymentlaundryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public paymentlaundryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment paymentlaundry.
     */
    // TODO: Rename and change types and number of parameters
    public static paymentlaundryFragment newInstance(String param1, String param2) {
        paymentlaundryFragment fragment = new paymentlaundryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paymentlaundry, container, false);

        // Temukan tombol "Bayar" berdasarkan ID-nya
        Button btnPay = view.findViewById(R.id.BayarButton);

        // Atur OnClickListener untuk tombol "Bayar"
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat instance baru dari fragmen ReportTagihanLaundry
                ReportTagihanLaundryFragment reportTagihanLaundryFragment = new ReportTagihanLaundryFragment();

                // Dapatkan FragmentManager
                // Gunakan getParentFragmentManager() untuk komunikasi antar fragmen dalam satu Activity.
                FragmentManager fragmentManager = getFragmentManager();

                // Mulai Transaksi Fragmen
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Ganti fragmen saat ini dengan fragmen ReportTagihanLaundry
                // R.id.fragment_container harus menjadi ID dari FrameLayout atau container
                // di layout Activity Anda tempat Anda menampilkan fragmen-fragmen Anda.
                fragmentTransaction.replace(R.id.flFragment, reportTagihanLaundryFragment).commit();

                // Tambahkan transaksi ke back stack (opsional, tapi bagus untuk navigasi)
//                fragmentTransaction.addToBackStack(null);

                // Commit transaksi
//                fragmentTransaction.commit();
            }
        });

        return view;
    }
}