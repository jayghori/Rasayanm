package com.example.rasayanm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ViewUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rasayanm.Model.User;
import com.example.rasayanm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText("  "+userList.get(position).getName());
        holder.tvEmail.setText("  "+userList.get(position).getEmail());
        holder.tvPhoneNumber.setText("  "+userList.get(position).getPhoneNumber());
        holder.tvAddress.setText(" "+userList.get(position).getAddress());
        holder.tvGstNumber.setText(" "+userList.get(position).getGSTNumber());
        holder.tvPanNumber.setText(" "+userList.get(position).getPANNumber());


        Glide.with(context).load(userList.get(position).getImgUrl()).placeholder(R.drawable.rasayanm).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvEmail,tvPhoneNumber,tvAddress,tvGstNumber,tvPanNumber;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvEmail=itemView.findViewById(R.id.tvEmail);
            tvPhoneNumber=itemView.findViewById(R.id.tvPhoneNumber);
            tvAddress=itemView.findViewById(R.id.tvAddress);
            tvGstNumber=itemView.findViewById(R.id.tvGstNumber);
            tvPanNumber=itemView.findViewById(R.id.tvPanNumber);
            img=itemView.findViewById(R.id.img);


        }
    }
}
