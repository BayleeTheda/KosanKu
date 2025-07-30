package com.example.kosanku;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class OrderCSFragment extends Fragment {

    private Spinner spinnerNomorKamar, spinnerJam;
    private ImageView spinnerArrowKamar, spinnerArrowJam;
    private TextView dateField;
    private DatePickerDialog datePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cleaning_service_fragment, container, false);

        // Inisialisasi view
        spinnerNomorKamar = view.findViewById(R.id.spinner_nomor_kamar);
        spinnerJam = view.findViewById(R.id.spinner_jam);
        spinnerArrowKamar = view.findViewById(R.id.spinner_arrow_kamar);
        spinnerArrowJam = view.findViewById(R.id.spinner_arrow_jam);
        dateField = view.findViewById(R.id.date_fieldbar);

        // Setup spinner data dan animasi
        setupSpinner(spinnerNomorKamar, spinnerArrowKamar, new String[]{"A01", "A02", "A03", "B01", "B02", "B03"});
        setupSpinner(spinnerJam, spinnerArrowJam, new String[]{"07:00 - 11:00", "14:00 - 17:00", "19:00 - 22:00"});

        // Inisialisasi date picker
        initDatePicker();

        // Klik untuk menampilkan date picker
        dateField.setOnClickListener(v -> datePickerDialog.show());

        return view;
    }

    // Fungsi untuk setup spinner + panah
    private void setupSpinner(Spinner spinner, ImageView arrow, String[] data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                data
        );
        spinner.setAdapter(adapter);

        spinner.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Rotasi saat dropdown dibuka
                arrow.animate().rotation(180).setDuration(200).start();

                // Reset panah jika tidak memilih apa-apa dalam 1.5 detik
                spinner.postDelayed(() -> {
                    arrow.animate().rotation(0).setDuration(200).start();
                }, 1500);
            }
            return false;
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Reset rotasi saat memilih
                arrow.animate().rotation(0).setDuration(200).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Reset rotasi juga saat tidak memilih
                arrow.animate().rotation(0).setDuration(200).start();
            }
        });
    }


    // Setup date picker
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) -> {
            month += 1; // Bulan dimulai dari 0
            dateField.setText(makeDateString(day, month, year));
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + month + "/" + year;
    }
}
