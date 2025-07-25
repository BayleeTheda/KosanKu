// ReportTagihanLaundry.java
package com.example.kosanku;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // Import Button

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportTagihanLaundryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportTagihanLaundryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportTagihanLaundryFragment() {
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
    public static ReportTagihanLaundryFragment newInstance(String param1, String param2) {
        ReportTagihanLaundryFragment fragment = new ReportTagihanLaundryFragment();
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
        return inflater.inflate(R.layout.fragment_report_tagihan_laundry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Temukan tombol Refresh
        Button btnRefresh = view.findViewById(R.id.btnRefresh);

        // Atur OnClickListener untuk tombol Refresh
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilkan pop-up kustom
                showPaymentSuccessDialog();
            }
        });
    }

    private void showPaymentSuccessDialog() {
        PaymentSuccessDialogFragment dialogFragment = new PaymentSuccessDialogFragment();
        // Gunakan getChildFragmentManager() jika Anda ingin dialog tetap dalam siklus hidup Fragment ini
        // Gunakan getSupportFragmentManager() jika Anda ingin dialog mengambang di atas Activity
        dialogFragment.show(getParentFragmentManager(), "payment_success_dialog");
    }
}