package com.example.pk2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pk2.model.Mensaje_Texto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdaptadorChat extends RecyclerView.Adapter
{
    private static final int M_VISTA_TIPO_ENVIADO = 1;
    private static final int M_VISTA_TIPO_RECIBIDO = 2;

    private Context c;
    private List<Mensaje_Texto> m_messageList;
    static final String PATH_USERS = "users/";
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    public AdaptadorChat(Context context, List<Mensaje_Texto> messageList) {
        c = context;
        m_messageList = messageList;

    }
    @Override
    public int getItemCount() {
        return m_messageList.size();
    }
    //Determina que tipo de "ViewType" es dependiendo del usuario que envio el mensaje.
    @Override
    public int getItemViewType(int position)
    {
        mAuth = FirebaseAuth.getInstance();
        Mensaje_Texto message =  m_messageList.get(position);
        FirebaseUser currUser = mAuth.getCurrentUser();

        if (message.getDueno().getId().equals(currUser.getUid())) {
            // If the current user is the sender of the message
            return M_VISTA_TIPO_ENVIADO;
        } else {
            // If some other user sent the message
            return M_VISTA_TIPO_RECIBIDO;
        }
    }
    void addMessage(Mensaje_Texto message)
    {
        m_messageList.add(0, message);
        notifyDataSetChanged();
    }
    void setUpdatedList(List<Mensaje_Texto> newList)
    {
        this.m_messageList.clear();
        this.m_messageList = newList;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == M_VISTA_TIPO_ENVIADO) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_chat_s_text_message, parent, false);
            return new EnviadorMensajeHolder(view);
        } else if (viewType == M_VISTA_TIPO_RECIBIDO) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_chat_r_text_message, parent, false);
            return new RecibidorMensajeHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mensaje_Texto message = m_messageList.get(position);

        switch (holder.getItemViewType()) {
            case M_VISTA_TIPO_ENVIADO:
                ((EnviadorMensajeHolder) holder).bind(message);
                break;
            case M_VISTA_TIPO_RECIBIDO:
                ((RecibidorMensajeHolder) holder).bind(message);
        }
    }

    private class RecibidorMensajeHolder extends RecyclerView.ViewHolder
    {
        TextView messageText, timeText, nameText;
        ImageView profileImage;
        RecibidorMensajeHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_r);
            timeText = (TextView) itemView.findViewById(R.id.text_message_hour_r);
            nameText = (TextView) itemView.findViewById(R.id.text_uname_r);
            profileImage = (ImageView) itemView.findViewById(R.id.image_profile_r);
        }
        void bind(Mensaje_Texto message) {
            messageText.setText(message.getContenidoMensaje());

            //WRONG.
            final long Hours= TimeUnit.MILLISECONDS.toHours(message.getCreatedAt());
            final long Minutes = TimeUnit.MILLISECONDS.toMinutes(message.getCreatedAt());
            final long Seconds = TimeUnit.MILLISECONDS.toSeconds(message.getCreatedAt());
            timeText.setText(String.format("%d : %d : %d", Hours, Minutes, Seconds));
            nameText.setText(message.getDueno().getNombre());
        }
    }
    private class EnviadorMensajeHolder extends RecyclerView.ViewHolder
    {
        TextView messageText, timeText;
        EnviadorMensajeHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message);
            timeText = (TextView) itemView.findViewById(R.id.text_time);
        }
        void bind(Mensaje_Texto message) {
            messageText.setText(message.getContenidoMensaje());

            //Transformar la hora guardada (En Milisegundos ) a una hora formateada.
            final long Hours= TimeUnit.MILLISECONDS.toHours(message.getCreatedAt());
            final long Minutes = TimeUnit.MILLISECONDS.toMinutes(message.getCreatedAt());
            final long Seconds = TimeUnit.MILLISECONDS.toSeconds(message.getCreatedAt());
            timeText.setText(String.format("%d : %d : %d", Hours, Minutes, Seconds));
        }
    }

}
