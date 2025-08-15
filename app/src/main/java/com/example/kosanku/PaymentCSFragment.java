package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PaymentCSFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment_cs_fragment, container, false);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Tombol Back
        ImageButton back_btn = view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new OrderCSFragment());
            }
        });


        // Tombol Bayar
        Button btnKirim = view.findViewById(R.id.bayar_btn);
        btnKirim.setOnClickListener(v -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.flFragment, new ReportTagihanCSFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    public void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}
