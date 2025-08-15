package com.example.kosanku;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ReportTagihanMakanFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ReportTagihanMakanFragment() {
        // Required empty public constructor
    }

    public static ReportTagihanMakanFragment newInstance(String param1, String param2) {
        ReportTagihanMakanFragment fragment = new ReportTagihanMakanFragment();
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
        View view = inflater.inflate(R.layout.report_tagihan_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnRefresh = view.findViewById(R.id.refreshButton);
        btnRefresh.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

            // Ganti ke LandingMakanFragment (kalau memang untuk makan, bukan laundry)
            fragmentManager.beginTransaction()
                    .replace(R.id.flFragment, new LandingLaundryFragment())
                    .addToBackStack(null)
                    .commit();

            // Setelah 5 detik, ganti ke WaitingOrderFragment
            new Handler().postDelayed(() -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.flFragment, new WaitingOrderFragment())
                        .addToBackStack(null)
                        .commit();
            }, 5000);
        });
    }
}
