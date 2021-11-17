package com.example.pk2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pk2.model.Motel;

import java.util.List;

public class adaptadorList extends RecyclerView.Adapter<adaptadorList.ViewHolder>{
    private List<Motel> datos;
    private LayoutInflater inflater;
    private Context context;
    final adaptadorList.OnItemClickListener listener;

    public  interface  OnItemClickListener{
        void onItemClick(Motel elementos);
    }



    public adaptadorList(List<Motel> lista, Context context, adaptadorList.OnItemClickListener listener ){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.datos = lista;
        this.listener = listener;

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @NonNull
    @Override
    public adaptadorList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cards, null);
        return new adaptadorList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptadorList.ViewHolder holder, int position) {
        holder.bindData(datos.get(position));
    }

    public void setItems(List<Motel> items)
    {
        datos = items;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView nombre, direccion;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.imagenMotelCard);
            nombre = itemView.findViewById(R.id.nombreMotelCard);
            direccion = itemView.findViewById(R.id.direccionMotelCard);
        }

        void bindData(final Motel lista){
            String urlImage = lista.getImagen();
            Glide.with(context)
                    .load(urlImage)
                    .into(iconImage);
            nombre.setText(lista.getNombre());
            direccion.setText(lista.getDireccion());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(lista);
                }
            });
        }


    }
}
