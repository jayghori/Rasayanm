package com.example.rasayanm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rasayanm.Model.User;
import com.example.rasayanm.R;

import java.util.List;

public class UserRfqAdapter extends RecyclerView.Adapter<UserRfqAdapter.ViewHolder> {

    Context context;
    List<User> users;

    public UserRfqAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rfq_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvUserName.setText(""+users.get(position).getName());
        holder.tvUserPhoneNumber.setText(""+users.get(position).getPhoneNumber());
        holder.tvUserEmail.setText(""+users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName, tvUserEmail, tvUserPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserEmail = itemView.findViewById(R.id.tvUserEmail);
            tvUserPhoneNumber = itemView.findViewById(R.id.tvUserPhoneNumber);
        }
    }
}
