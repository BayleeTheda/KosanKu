package com.example.kosanku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ReportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.keluhan_fragment, container, false);

        Spinner spinner = view.findViewById(R.id.droplist_keluhan);

        String[] kategoriKeluhan = {"Fasilitas Umum", "Fasilitas Kamar", "Kebersihan", "Gangguan Tetangga", "Pembayaran", "Lainnya"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, kategoriKeluhan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button submit_btn = view.findViewById(R.id.send_btn);
        ImageButton back_btn = view.findViewById(R.id.back_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new HomeFragment());
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new HomeFragment());
            }
        });

        return view;
    }

    public void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}
