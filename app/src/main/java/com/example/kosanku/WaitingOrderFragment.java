package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class WaitingOrderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WaitingOrderFragment() {
        // Required empty public constructor
    }

    public static WaitingOrderFragment newInstance(String param1, String param2) {
        WaitingOrderFragment fragment = new WaitingOrderFragment();
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

        // Inflate layout dulu
        View view = inflater.inflate(R.layout.fragment_waiting_order, container, false);

        // Ambil tombol
        Button btnKirim = view.findViewById(R.id.pesanan_btn);

        // Listener tombol
        btnKirim.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.flFragment, new HomeFragment())
                    .addToBackStack(null) // supaya bisa back
                    .commit();
        });

        return view;
    }
}
