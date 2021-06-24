package com.example.dbfirebase.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbfirebase.MainActivity;
import com.example.dbfirebase.R;
import com.example.dbfirebase.TemanEdit;
import com.example.dbfirebase.database.Teman;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Teman> daftarteman;
    private Context context;
    private DatabaseReference databaseReference;

    public Adapter(ArrayList<Teman> daftarteman, Context context) {
        this.daftarteman = daftarteman;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teman, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String kode, nama, telpon;
        nama = daftarteman.get(position).getNama();
        kode = daftarteman.get(position).getKode();
        telpon = daftarteman.get(position).getTelpon();

        holder.tvnama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //detail data
            }
        });
        holder.tvnama.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
                popupMenu.inflate(R.menu.menuteman);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Bundle bandel = new Bundle();
                                bandel.putString("kunci1",kode);
                                bandel.putString("kunci2",nama);
                                bandel.putString("kunci3",telpon);

                                Intent intent = new Intent(view.getContext(), TemanEdit.class);
                                intent.putExtras(bandel);
                                view.getContext().startActivity(intent);
                                break;
                            case R.id.hapus:
                                AlertDialog.Builder alertdb = new AlertDialog.Builder(view.getContext());
                                alertdb.setTitle("Yakin nih "+nama+" mau di hapus?");
                                alertdb.setMessage("Tekan YA untuk menghapus");
                                alertdb.setCancelable(false);
                                alertdb.setPositiveButton("ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DeleteData(kode);
                                        Toast.makeText(view.getContext(),"Data "+nama+" telah dihapus", Toast.LENGTH_LONG).show();
                                        Intent inten = new Intent(view.getContext(), MainActivity.class);
                                        view.getContext().startActivity(inten);
                                    }
                                });
                                alertdb.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog adlg = alertdb.create();
                                adlg.show();
                                break;

                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
        holder.tvnama.setText(nama);
    }

    @Override
    public int getItemCount() {
        return daftarteman.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvnama;
        ViewHolder(View v) {
            super(v);
            tvnama = (TextView) v.findViewById(R.id.tv_nama);

            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
    }
    public void DeleteData(String kode){
        if(databaseReference != null){
            databaseReference.child("Teman").child(kode).removeValue();
        }
    }
}

