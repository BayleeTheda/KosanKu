package com.example.kosanku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    // Deklarasi view
    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi view dari layout
        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Set OnClickListener untuk tombol login
        btnLogin.setOnClickListener(v -> {
            // Panggil method untuk validasi dan login
            validateAndLogin();
        });
    }

    private void validateAndLogin() {
        // Ambil input dari user dan hapus spasi di awal/akhir
        String email = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Hapus error sebelumnya (jika ada)
        tilUsername.setError(null);
        tilPassword.setError(null);

        // Proses validasi
        if (email.isEmpty()) {
            tilUsername.setError("Email tidak boleh kosong");
            return; // Hentikan proses jika email kosong
        }

        if (!email.endsWith("@gmail.com")) {
            tilUsername.setError("Email harus menggunakan @gmail.com");
            return; // Hentikan proses jika format email salah
        }

        if (password.isEmpty()) {
            tilPassword.setError("Password tidak boleh kosong");
            return; // Hentikan proses jika password kosong
        }

        if (password.length() < 8) {
            tilPassword.setError("Password minimal 8 karakter");
            return; // Hentikan proses jika password kurang dari 8 karakter
        }

        // Jika semua validasi berhasil
        // Pindah ke MainActivity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Tutup LoginActivity agar tidak bisa kembali
    }
}