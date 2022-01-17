package com.example.sqlite8020180306crud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.ViewHolderSaya> {

    private Context context;
    private ArrayList pegawai_id, pegawai_nama, pegawai_email, pegawai_alamat;

    BukuAdapter(
            Context context,
            ArrayList pegawai_id,
            ArrayList pegawai_nama,
            ArrayList pegawai_email,
            ArrayList pegawai_alamat
    ){
        this.context = context;
        this.pegawai_id = pegawai_id;
        this.pegawai_nama = pegawai_nama;
        this.pegawai_email = pegawai_email;
        this.pegawai_alamat = pegawai_alamat;
    }

    @NonNull
    @Override
    public ViewHolderSaya onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterKita = LayoutInflater.from(context);
        View viewKita = inflaterKita.inflate(R.layout.row_saya, parent, false);
        return new ViewHolderSaya(viewKita);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSaya holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_id_pegawai.setText(String.valueOf(pegawai_id.get(position)));
        holder.txt_nama_pegawai.setText(String.valueOf(pegawai_nama.get(position)));
        holder.txt_email_pegawai.setText(String.valueOf(pegawai_email.get(position)));
        holder.txt_alamat_pegawai.setText(String.valueOf(pegawai_alamat.get(position)));
        holder.layoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKita=new Intent(context, UbahActivity.class);
                intentKita.putExtra("id", String.valueOf(pegawai_id.get(position)));
                intentKita.putExtra("nama", String.valueOf(pegawai_nama.get(position)));
                intentKita.putExtra("email", String.valueOf(pegawai_email.get(position)));
                intentKita.putExtra("alamat", String.valueOf(pegawai_alamat.get(position)));

                context.startActivity(intentKita);
            }
        });
    }

    @Override
    public int getItemCount() {

        return pegawai_id.size();
    }

    public class ViewHolderSaya extends RecyclerView.ViewHolder {

        TextView txt_id_pegawai, txt_nama_pegawai, txt_email_pegawai, txt_alamat_pegawai;
        LinearLayout layoutUtama;

        public ViewHolderSaya(@NonNull View itemView) {
            super(itemView);

            txt_id_pegawai      = itemView.findViewById(R.id.txt_pegawai_id);
            txt_nama_pegawai    = itemView.findViewById(R.id.txt_pegawai_nama);
            txt_email_pegawai   = itemView.findViewById(R.id.txt_pegawai_email);
            txt_alamat_pegawai  = itemView.findViewById(R.id.txt_pegawai_alamat);
            layoutUtama         = itemView.findViewById(R.id.layout_utama);

        }
    }
}
