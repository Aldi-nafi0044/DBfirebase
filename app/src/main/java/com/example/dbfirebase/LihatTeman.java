package com.example.dbfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbfirebase.adapter.Adapter;
import com.example.dbfirebase.database.Teman;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatTeman extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Teman> dataTeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_teman);

        recyclerView = (RecyclerView) findViewById(R.id.rv_utama);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Teman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                dataTeman = new ArrayList<>();
                for(DataSnapshot daftarDS: snapshot.getChildren()){
                    Teman tm = daftarDS.getValue(Teman.class);
                    tm.setKode(daftarDS.getKey());

                    dataTeman.add(tm);
                }adapter = new Adapter(dataTeman, LihatTeman.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                System.out.println(error.getDetails()+""+error.getMessage());
            }
        });
    }
}