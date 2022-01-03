package com.example.rasayanm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rasayanm.ActivityOfferDetail;
import com.example.rasayanm.Model.QuickOffer;
import com.example.rasayanm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QuickOfferAdapter extends RecyclerView.Adapter<QuickOfferAdapter.ViewHolder> {

    List<QuickOffer> offerList;
    Context context;

    public QuickOfferAdapter(List<QuickOffer> offerList, Context context) {
        this.offerList = offerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.quick_offer_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitle.setText("Title :- "+offerList.get(position).getTitle());
        holder.tvStock.setText("Stock :- "+offerList.get(position).getStock());
        holder.tvDiscription.setText("Discription :- "+offerList.get(position).getDiscription());
        holder.tvLocality.setText("Locality :- "+offerList.get(position).getLocality());
        holder.tvCountryName.setText("Country Name :- "+offerList.get(position).getCountryName());
        holder.tvPrice.setText("Price :- "+offerList.get(position).getPrice());
        holder.tvId.setText("Time :-"+offerList.get(position).getId());
        holder.tvStockUnit.setText(""+offerList.get(position).getStockUnit());
        holder.tvGradeUnit.setText(""+offerList.get(position).getGradeUnit());
        holder.tvPriceUnit.setText(""+offerList.get(position).getPriceUnit());


        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss");
        Date dateObj = null;
        try {
            dateObj = curFormater.parse(offerList.get(position).getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("dd MM , yyyy");

        String newDateStr = postFormater.format(dateObj);

        Log.d("TAGG", "onBindViewHolder: "+offerList.get(position).getTitle()+ " || "+newDateStr);

        Glide.with(context).load(offerList.get(position).getImgUrl()).placeholder(R.drawable.rasayanm).into(holder.img);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityOfferDetail.class);
                intent.putExtra("Title",offerList.get(position).getTitle());
                intent.putExtra("Stock",offerList.get(position).getStock());
                intent.putExtra("GradeUnit",offerList.get(position).getGradeUnit());
                intent.putExtra("PriceUnit",offerList.get(position).getPriceUnit());
                intent.putExtra("StockUnit",offerList.get(position).getStockUnit());
                intent.putExtra("Discription",offerList.get(position).getDiscription());
                intent.putExtra("Price",offerList.get(position).getPrice());
                intent.putExtra("Locality",offerList.get(position).getLocality());
                intent.putExtra("CountryName",offerList.get(position).getCountryName());
                intent.putExtra("Id",offerList.get(position).getId());
                intent.putExtra("Image",offerList.get(position).getImgUrl());

                intent.putExtra("quick offer",offerList.get(position));
                holder.itemView.getContext().startActivity(intent);



                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public void setOfferList(List<QuickOffer> offerList){
        this.offerList=offerList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvStock,tvDiscription,tvLocality,tvCountryName,tvPrice,tvId;
        TextView tvStockUnit,tvGradeUnit,tvPriceUnit;
        ImageView img;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStockUnit=itemView.findViewById(R.id.tvStockUnit);
            tvGradeUnit=itemView.findViewById(R.id.tvGradeUnit);
            tvPriceUnit=itemView.findViewById(R.id.tvPriceUnit);
            tvId=itemView.findViewById(R.id.tvId);
            img=itemView.findViewById(R.id.imgView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvStock=itemView.findViewById(R.id.tvStock);
            tvDiscription=itemView.findViewById(R.id.tvDiscription);
            tvLocality=itemView.findViewById(R.id.tvLocality);
            tvCountryName=itemView.findViewById(R.id.tvCountryName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            relativeLayout=itemView.findViewById(R.id.relativeLayout);


        }
    }
}
