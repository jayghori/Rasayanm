package com.example.rasayanm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rasayanm.Model.Chat;
import com.example.rasayanm.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Chat> chatList;

    int SENDER = 1;
    int RECEIVER = 2;

    public ChatAdapter(Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER) {
            return new SenderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sent, parent, false));
        } else {
            return new ReceiverHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receive, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == SENDER) {
            SenderHolder holder = (SenderHolder) viewHolder;
            holder.tvMsg.setText(chatList.get(position).getMsg());
        } else if (getItemViewType(position) == RECEIVER) {
            ReceiverHolder holder = (ReceiverHolder) viewHolder;
            holder.tvMsg.setText(chatList.get(position).getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getPerson().get(0).equals(FirebaseAuth.getInstance().getUid())) {
            return SENDER;
        } else {
            return RECEIVER;
        }
    }

    class SenderHolder extends RecyclerView.ViewHolder {

        TextView tvMsg;

        public SenderHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.message);
        }
    }

    class ReceiverHolder extends RecyclerView.ViewHolder {

        TextView tvMsg;

        public ReceiverHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.message);
        }
    }
}
