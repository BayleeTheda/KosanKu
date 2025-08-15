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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

        // Sembunyikan Topbar & Bottom Nav
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showNavigation(false);
        }

        // Set background container fragment agar tidak putih
        if (getActivity() != null) {
            View containerView = getActivity().findViewById(R.id.flFragment);
            if (containerView != null) {
                containerView.setBackgroundColor(0xFFFBF4F1);
            }
        }

        // Inisialisasi view
        spinnerNomorKamar = view.findViewById(R.id.spinner_nomor_kamar);
        spinnerJam = view.findViewById(R.id.spinner_jam);
        spinnerArrowKamar = view.findViewById(R.id.spinner_arrow_kamar);
        spinnerArrowJam = view.findViewById(R.id.spinner_arrow_jam);
        dateField = view.findViewById(R.id.date_fieldbar);
        ImageButton backButton = view.findViewById(R.id.back_btn);

        // Setup spinner data + font
        setupSpinner(spinnerNomorKamar, spinnerArrowKamar,
                new String[]{"A01", "A02", "A03", "B01", "B02", "B03"});
        setupSpinner(spinnerJam, spinnerArrowJam,
                new String[]{"07:00 - 11:00", "14:00 - 17:00", "19:00 - 22:00"});

        // Inisialisasi date picker
        initDatePicker();

        // Klik date picker
        dateField.setOnClickListener(v -> datePickerDialog.show());

        // Tombol kembali
        backButton.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // Ambil tombol pembayaran
        Button btnKirim = view.findViewById(R.id.pembayaran);

        // Listener tombol
        btnKirim.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.flFragment, new PaymentCSFragment())
                    .addToBackStack(null) // supaya bisa kembali
                    .commit();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Tampilkan kembali navigasi
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showNavigation(true);
        }
    }

    // Setup spinner dengan font custom + animasi panah
    private void setupSpinner(Spinner spinner, ImageView arrow, String[] data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                data
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view;
                text.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.cabin));
                text.setTextColor(0xFF000000); // hitam
                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView text = (TextView) view;
                text.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.cabin));
                text.setTextColor(0xFF000000); // hitam
                return view;
            }
        };
        spinner.setAdapter(adapter);

        spinner.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                arrow.animate().rotation(180).setDuration(200).start();
                spinner.postDelayed(() -> arrow.animate().rotation(0).setDuration(200).start(), 1500);
            }
            return false;
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrow.animate().rotation(0).setDuration(200).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                arrow.animate().rotation(0).setDuration(200).start();
            }
        });
    }

    // Setup date picker
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) -> {
            month += 1;
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
