package com.example.nepo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Database.KegiatanHandler;

public class OpenRecAdapter extends RecyclerView.Adapter<OpenRecAdapter.ViewHolder> {
    private List<KegiatanHandler> kegiatanHandlerList;
    private Context context;
    private RecyclerView recyclerView;
    private int id;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemJudul;
        TextView itemDeskripsi;
        TextView itemTgl_Mulai;
        TextView itemTgl_Akhir;

        public ViewHolder(@NonNull View itemView){
            super (itemView);
            itemJudul = itemView.findViewById(R.id.judul_kegiatan);
            itemDeskripsi = itemView.findViewById(R.id.deskripsi);
            itemTgl_Mulai = itemView.findViewById(R.id.value_tgl_mulai);
            itemTgl_Akhir = itemView.findViewById(R.id.value_tgl_akhir);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(context);
                    dialogPesan.setMessage("Pilihan Operasi yang Akan Dilakukan");
                    dialogPesan.setCancelable(true);

//                    id = id

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialogPesan.show();

                    return false;
                }
            });

//            private void deleteData(){

//            }
        }
    }
    public OpenRecAdapter(List<KegiatanHandler> kegiatanHandlerList, Context context, RecyclerView recyclerView){
        this.kegiatanHandlerList = kegiatanHandlerList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    public OpenRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_card_kegiatan,parent,false);
        OpenRecAdapter.ViewHolder viewHolder = new OpenRecAdapter.ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull OpenRecAdapter.ViewHolder holder, int position) {
        KegiatanHandler kegiatanHandler = kegiatanHandlerList.get(position);
        holder.itemJudul.setText(String.valueOf(kegiatanHandler.getJudul()));
        holder.itemDeskripsi.setText(String.valueOf(kegiatanHandler.getDeskripsi()));
        holder.itemTgl_Mulai.setText(String.valueOf(kegiatanHandler.getTanggal_Mulai()));
        holder.itemTgl_Akhir.setText(String.valueOf(kegiatanHandler.getTanggal_Akhir()));
    }

    public long getItemId(int position) {
        return position;
    }

    public int getItemCount() {
        return kegiatanHandlerList.size();
    }
}
