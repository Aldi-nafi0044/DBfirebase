package com.example.dbfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbfirebase.database.Teman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TemanEdit extends AppCompatActivity {
    TextView tv_Key;
    EditText ed_nama, ed_telpon;
    Button btnEdit;
    DatabaseReference databaseReference;
    String kode, nama, telpon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);
        tv_Key = findViewById(R.id.tv_key);
        ed_nama = findViewById(R.id.ednama);
        ed_telpon = findViewById(R.id.edtelpon);
        btnEdit = findViewById(R.id.btEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("kunci1");
        nama = bundle.getString("kunci2");
        telpon = bundle.getString("kunci3");

        tv_Key.setText(kode);
        ed_nama.setText(nama);
        ed_telpon.setText(telpon);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTeman(new Teman(ed_nama.getText().toString(),ed_telpon.getText().toString()));
            }
        });
    }
    public void editTeman(Teman teman){
        databaseReference.child("Teman")
                .child(kode)
                .setValue(teman)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TemanEdit.this,"Data berhasil di update",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}