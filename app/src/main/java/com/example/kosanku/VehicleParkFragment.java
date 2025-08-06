package com.example.kosanku;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehicleParkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleParkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String[] bedroom = {
        "A301", "A302", "A303", "A304", "A305","A306", "A307", "A308", "A309", "A310",
            "B301", "B302", "B303", "B304", "B305","B306", "B307", "B308", "B309", "B310",
    };

    private String[] time = {
      "07:00 - 11:00", "14:00 - 17:00", "19:00 - 22:00"
    };

    private DatePickerDialog datePickerDialog;
    Button date_fieldbar;

    public VehicleParkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehicleParkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VehicleParkFragment newInstance(String param1, String param2) {
        VehicleParkFragment fragment = new VehicleParkFragment();
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
        View view = inflater.inflate(R.layout.fragment_vehicle_park, container, false);

        Spinner bedroom_field = view.findViewById(R.id.bedroom_fieldbar);
        bedroom_field.setOnItemSelectedListener(bedroom_field.getOnItemSelectedListener());

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this.getActivity(), android.R.layout.simple_spinner_item,bedroom
        );
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedroom_field.setAdapter(ad);

        initDatePicker();
        date_fieldbar = view.findViewById(R.id.date_fieldbar);
        date_fieldbar.setText(getTodaysDate());

        date_fieldbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(view);
            }
        });

        Spinner time_field = view.findViewById(R.id.time_fieldbar);
        time_field.setOnItemSelectedListener(time_field.getOnItemSelectedListener());

        ArrayAdapter<String> at = new ArrayAdapter<>(
          this.getActivity(), android.R.layout.simple_spinner_item, time
        );
        at.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_field.setAdapter(at);

        ImageButton back_btn = view.findViewById(R.id.back_btn);
        MaterialButton confirm_btn = view.findViewById(R.id.confirm_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new HomeFragment());
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new HomeFragment());
            }
        });

        return view;
    }

    private void setCurrentFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                date_fieldbar.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this.getContext(), style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year){
        return day+"/"+month+"/"+year;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}