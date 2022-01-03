package com.example.rasayanm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rasayanm.ActivityOfferDetail;
import com.example.rasayanm.Model.NoticeBoard;
import com.example.rasayanm.NoticeBoardDetail;
import com.example.rasayanm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.ViewHolder> {

    Context context;
    List<NoticeBoard> noticeBoardList;

    public NoticeBoardAdapter(Context context, List<NoticeBoard> noticeBoardList) {
        this.context = context;
        this.noticeBoardList = noticeBoardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_board_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitile.setText("Title :- " + noticeBoardList.get(position).getTitle());
        holder.tvDiscription.setText("Stock :- " + noticeBoardList.get(position).getDiscription());
        holder.tvLink.setText("Discription :- " + noticeBoardList.get(position).getLink());
        holder.tvSpinCategory.setText("Categery :- " + noticeBoardList.get(position).getCategeryUnit());

        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss");
        Date dateObj = null;
        try {
            dateObj = curFormater.parse(noticeBoardList.get(position).getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("dd MM , yyyy");

        String newDateStr = postFormater.format(dateObj);
        Log.d("TAGG", "onBindViewHolder: " + noticeBoardList.get(position).getTitle() + " || " + newDateStr);

        Glide.with(context).load(noticeBoardList.get(position).getImgUrl()).placeholder(R.drawable.rasayanm).into(holder.img);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticeBoardDetail.class);
                intent.putExtra("Title", noticeBoardList.get(position).getTitle());
                intent.putExtra("Discription", noticeBoardList.get(position).getDiscription());
                intent.putExtra("Link", noticeBoardList.get(position).getLink());
                intent.putExtra("SpinUnit", noticeBoardList.get(position).getCategeryUnit());
                intent.putExtra("Image", noticeBoardList.get(position).getImgUrl());
                intent.putExtra("Id", noticeBoardList.get(position).getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeBoardList.size();
    }

    public void setNoticeBoardList(List<NoticeBoard> noticeBoardList) {
        this.noticeBoardList = noticeBoardList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitile, tvDiscription, tvLink, tvSpinCategory;
        ImageView img;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitile = itemView.findViewById(R.id.tvTitle);
            tvDiscription = itemView.findViewById(R.id.tvDiscription);
            tvLink = itemView.findViewById(R.id.tvLink);
            img = itemView.findViewById(R.id.imgView);
            tvSpinCategory = itemView.findViewById(R.id.tvSpinCategory);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

        }
    }
}
