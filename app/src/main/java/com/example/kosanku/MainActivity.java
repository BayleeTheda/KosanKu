package com.example.kosanku;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        Fragment homeFragment = new HomeFragment();
        Fragment qrFragment = new QrFragment();
        Fragment historyFragment = new HistoryFragment();

        setCurrentFragment(homeFragment);
        bottomNavigationView.setSelectedItemId(R.id.home_btn);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home_btn){
                setCurrentFragment(homeFragment);
                return true;
            }else if(item.getItemId() == R.id.qr_btn){
                setCurrentFragment(qrFragment);
                return true;
            }else if(item.getItemId() == R.id.history_btn){
                setCurrentFragment(historyFragment);
                return true;
            }
            return true;
        });

        ImageView notif_btn = findViewById(R.id.notification_icon);



        notif_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentFragment(new NotificationFragment());
            }
        });
    }

    private void setCurrentFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}