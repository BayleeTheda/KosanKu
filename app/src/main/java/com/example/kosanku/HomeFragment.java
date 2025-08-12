package com.example.kosanku;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BottomNavigationView bottomNav;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //WARNING
        // Inisialisasi semua view
        bottomNav = view.findViewById(R.id.bottomNav);
        ImageView profileIcon;
//            appBar = findViewById(R.id.app_bar);
        ImageView notif_btn = view.findViewById(R.id.notification_icon);
        profileIcon = view.findViewById(R.id.profile_icon);

        // Bottom Navigation
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home_btn) {
                setFragment(new HomeFragment());
            } else if (itemId == R.id.history_btn) {
                setFragment(new HistoryFragment());
            }
            return true;
        });

        FloatingActionButton qr_btn = view.findViewById(R.id.qr_btn);
        qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new QrFragment());
            }
        });

        // Notifikasi
        notif_btn.setOnClickListener(v -> setFragment(new NotificationFragment()));

        // Profil
        profileIcon.setOnClickListener(v -> setFragment(new ProfileFragment()));


        ImageSlider banner = view.findViewById(R.id.banner);

        ArrayList<SlideModel> slideBanner = new ArrayList<>();

        slideBanner.add(new SlideModel(R.drawable.banner_1, ScaleTypes.FIT));
        slideBanner.add(new SlideModel(R.drawable.banner_1, ScaleTypes.FIT));
        slideBanner.add(new SlideModel(R.drawable.banner_1, ScaleTypes.FIT));

        banner.setImageList(slideBanner, ScaleTypes.CENTER_INSIDE);

        LinearLayout vehicle_btn = view.findViewById(R.id.vehicle_btn);
        vehicle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new VehicleParkFragment());
            }
        });

        LinearLayout laundry_btn = view.findViewById(R.id.laundry_btn);
        laundry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new LaundryFragment());
            }
        });

        LinearLayout cleaning_btn = view.findViewById(R.id.cleaning_btn);
        cleaning_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new OrderCSFragment());
            }
        });

        LinearLayout bill_btn = view.findViewById(R.id.bill_btn);
        bill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new TagihanKosFragment());
            }
        });


        LinearLayout food_btn = view.findViewById(R.id.food_btn);
        food_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new BuyFoods());
            }
        });

        LinearLayout report_btn = view.findViewById(R.id.report_btn);
        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ReportFragment());
            }
        });

        return view;
    }

    public void setFragment(Fragment fragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}