package com.example.kosanku;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast; // Opsional: untuk notifikasi singkat

import java.util.ArrayList; // Untuk contoh data RecyclerView

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryTransaction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryTransaction extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Deklarasi View components
    private TextView tvHistoryTitle;
    private EditText etSearch;
    private Spinner spinnerAll;
    private RecyclerView rvTransactions;

    // Untuk RecyclerView (contoh data dan adapter)
    private TransactionAdapter transactionAdapter;
    private ArrayList<Transaction> transactionList; // Model data Anda

    public HistoryTransaction() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryTransaction.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryTransaction newInstance(String param1, String param2) {
        HistoryTransaction fragment = new HistoryTransaction();
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
        View view = inflater.inflate(R.layout.fragment_history_transaction, container, false);

        // Inisialisasi View components dari layout
        tvHistoryTitle = view.findViewById(R.id.tv_history_title);
        etSearch = view.findViewById(R.id.et_search);
        spinnerAll = view.findViewById(R.id.spinner_all);
//        rvTransactions = view.findViewById(R.id.rv_transactions);

        // --- Konfigurasi Spinner ---
        // Membuat ArrayAdapter menggunakan array dari strings.xml
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.filter_options, // Nama array yang Anda definisikan di strings.xml
                android.R.layout.simple_spinner_item // Layout untuk tampilan spinner saat tidak dropdown
        );

        // Mengatur dropdown layout (tampilan saat spinner dibuka)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Menetapkan Adapter ke Spinner
        spinnerAll.setAdapter(spinnerAdapter);

        // Menangani pilihan item pada Spinner
        spinnerAll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                // TODO: Tambahkan logika filter untuk RecyclerView di sini
                Toast.makeText(getContext(), "Pilihan Filter: " + selectedOption, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Biarkan kosong atau tambahkan logika jika tidak ada yang dipilih
            }
        });

        // --- Konfigurasi RecyclerView (contoh sederhana) ---
        // Anda perlu membuat kelas model data 'Transaction' dan kelas Adapter 'TransactionAdapter'
        transactionList = new ArrayList<>();
        // TODO: Ganti dengan data transaksi asli Anda
        transactionList.add(new Transaction("25/05/2025", "Tagihan Kost", "Rp 3.000.000", "Lunas"));
        transactionList.add(new Transaction("25/05/2025", "Tagihan Laundry", "Rp 3.000.000", "Lunas"));
        transactionList.add(new Transaction("25/05/2025", "Tagihan Makan", "Rp 3.000.000", "Lunas"));
        transactionList.add(new Transaction("25/05/2025", "Tagihan Cleaning Service", "Rp 3.000.000", "Lunas"));
        // Tambahkan lebih banyak data sesuai kebutuhan

        rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionAdapter = new TransactionAdapter(transactionList); // Anda perlu membuat konstruktor ini
        rvTransactions.setAdapter(transactionAdapter);

        // Return the inflated view
        return view;
    }

    // --- Anda perlu membuat kelas model data ini (misalnya, di file Transaction.java) ---
    // Contoh sederhana:
    public static class Transaction {
        private String date;
        private String name;
        private String amount;
        private String status;

        public Transaction(String date, String name, String amount, String status) {
            this.date = date;
            this.name = name;
            this.amount = amount;
            this.status = status;
        }

        public String getDate() { return date; }
        public String getName() { return name; }
        public String getAmount() { return amount; }
        public String getStatus() { return status; }
    }


    // --- Anda perlu membuat kelas adapter ini (misalnya, di file TransactionAdapter.java) ---
    // Contoh sederhana:
    public static class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

        private ArrayList<Transaction> transactions;

        public TransactionAdapter(ArrayList<Transaction> transactions) {
            this.transactions = transactions;
        }

        @NonNull
        @Override
        public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
            return new TransactionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
            Transaction transaction = transactions.get(position);
            holder.tvDate.setText(transaction.getDate());
            holder.tvName.setText(transaction.getName());
            holder.tvAmount.setText(transaction.getAmount());
            holder.tvStatus.setText(transaction.getStatus());

            // Ubah warna latar belakang status jika perlu (misalnya, berdasarkan status "Lunas" atau "Belum Lunas")
            // if (transaction.getStatus().equals("Lunas")) {
            //     holder.tvStatus.setBackgroundResource(R.drawable.bg_status_lunas);
            // } else {
            //     holder.tvStatus.setBackgroundResource(R.drawable.bg_status_belum_lunas); // Anda perlu membuat drawable ini
            // }
        }

        @Override
        public int getItemCount() {
            return transactions.size();
        }

        public static class TransactionViewHolder extends RecyclerView.ViewHolder {
            TextView tvDate, tvName, tvAmount, tvStatus;

            public TransactionViewHolder(@NonNull View itemView) {
                super(itemView);
                tvDate = itemView.findViewById(R.id.tv_transaction_date);
                tvName = itemView.findViewById(R.id.tv_transaction_name);
                tvAmount = itemView.findViewById(R.id.tv_transaction_amount);
                tvStatus = itemView.findViewById(R.id.tv_transaction_status);
            }
        }
    }
}