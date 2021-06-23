package com.example.dbfirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbfirebase.R;
import com.example.dbfirebase.database.Teman;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Teman> daftarteman;
    private Context context;

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
        String nama = daftarteman.get(position).getNama();
        holder.tvnama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //detail data
            }
        });
        holder.tvnama.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 * untuk latihan Selanjutnya ,fungsi Delete dan Update data
                 */
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
        }
    }

}

