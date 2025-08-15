package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class paymentlaundryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public paymentlaundryFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_paymentlaundry, container, false);

        Button btnKirim = view.findViewById(R.id.bayar_btn);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.flFragment, new ReportTagihanLaundryFragment())
                        .addToBackStack(null)
                        .commit();
            }

        });

        ImageButton back_btn = view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v -> {
            // Ganti fragment ke HomeFragment
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, new LaundryFragment())
                    .addToBackStack(null) // supaya bisa kembali lagi
                    .commit();
        });

        return view;
    }
}