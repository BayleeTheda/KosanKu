package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class TagihanKosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_tagihan_kos_fragment, container, false);

        AppCompatButton bayarButton = view.findViewById(R.id.backButton);

        ImageButton backBtn = view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        bayarButton.setOnClickListener(v -> {
            // Kirim tipe pembayaran "kos"
            Fragment reportTagihanFragment = ReportTagihanFragment.newInstance("kos");
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.flFragment, reportTagihanFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }
}
