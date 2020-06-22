package com.example.testbarang;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testbarang2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import static android.text.TextUtils.isEmpty;

public class UpdateData extends AppCompatActivity {
    private EditText kodeBaru, namaBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekKode, cekNama;
    private Task reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getSupportActionBar().setTitle("Update Data");
        kodeBaru = findViewById(R.id.new_kode);
        namaBaru = findViewById(R.id.new_nama);
        update = findViewById(R.id.update);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekKode = kodeBaru.getText().toString();
                cekNama = namaBaru.getText().toString();

                if (isEmpty(cekKode) || isEmpty(cekNama)) {
                    Toast.makeText(update.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    data_barang setBarang = new data_barang();
                    setBarang.setKode(kodeBaru.getText().toString());
                    setBarang.setNama(namaBaru.getText().toString());
                    updateData(setBarang);


                }
            }
        });
    }

    private void getData() {
        final String getKode = getIntent().getExtras().getString("dataKode");
        final String getNama = getIntent().getExtras().getString("dataNama");
        kodeBaru.setText(getKode);
        namaBaru.setText(getNama);
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void updateData(data_barang barang){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Barang").push().setValue(Brg).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid){
                kodeBaru.setText("");
                namaBaru.setText("");
                Toast.makeText(updateData.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void onDeleteData(dataBarang dataBarang, int position){
        String userID = auth.getUid();
        if(reference != null){
            reference.addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(dataBarang.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}