package com.example.dbfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbfirebase.database.Teman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahTeman extends AppCompatActivity {

    private EditText ednama, edtelpon;
    private Button btnsimpan;
    private DatabaseReference database;
    String nm, tlp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_teman);

        ednama = findViewById(R.id.editnama);
        edtelpon = findViewById(R.id.edittelpon);
        btnsimpan = findViewById(R.id.buttonsimpan);

        database = FirebaseDatabase.getInstance().getReference();

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!ednama.getText().toString().isEmpty())&&(!edtelpon.getText().toString().isEmpty())){
                   nm = ednama.getText().toString();
                   tlp = edtelpon.getText().toString();

                   submitData(new Teman(nm,tlp));

                    Intent i = new Intent(TambahTeman.this,MainActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(TambahTeman.this,"data tersimpan",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void submitData(Teman tmn){
        database.child("Teman").push().setValue(tmn).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                ednama.setText("");
                edtelpon.setText("");

                Toast.makeText(TambahTeman.this,"data sukses disimpan",Toast.LENGTH_LONG).show();
            }
        });
    }
}