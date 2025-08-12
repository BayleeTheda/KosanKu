// ReportTagihanLaundry.java
package com.example.kosanku;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportTagihanLaundryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportTagihanMakanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportTagihanMakanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportTagihanLaundry.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.report_tagihan_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Temukan tombol Refresh
        Button btnRefresh = view.findViewById(R.id.refreshButton);

        // Atur OnClickListener untuk tombol Refresh
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ganti fragmen saat ini dengan LandingLaundryFragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                // Menggunakan flFragment sebagai ID kontainer sesuai dengan layout MainActivity
                transaction.replace(R.id.flFragment, new LandingLaundryFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                // Gunakan Handler untuk menunggu 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Setelah 5 detik, ganti LandingLaundryFragment dengan WaitingOrderFragment
                        if (isAdded()) {
                            FragmentTransaction secondTransaction = getParentFragmentManager().beginTransaction();
                            secondTransaction.replace(R.id.flFragment, new WaitingOrderFragment());
                            // Jangan tambahkan ke back stack jika Anda ingin WaitingOrder menjadi halaman akhir
                            secondTransaction.commit();
                        }
                    }
                }, 5000); // 5000 milliseconds = 5 seconds
            }
        });
    }
}
