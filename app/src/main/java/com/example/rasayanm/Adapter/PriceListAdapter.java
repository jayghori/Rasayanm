package com.example.rasayanm.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.rasayanm.Model.PriceList;
import com.example.rasayanm.PriceListDetail;
import com.example.rasayanm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PriceListAdapter extends RecyclerView.Adapter<PriceListAdapter.ViewHolder> {

    Context context;
    List<PriceList> priceList;

    public PriceListAdapter(Context context, List<PriceList> priceListList) {
        this.context = context;
        this.priceList = priceListList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pricelist_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitle.setText("Title :- " + priceList.get(position).getTitle());
        holder.tvStock.setText("Stock :- " + priceList.get(position).getStock());
        holder.tvDiscription.setText("Discription :- " + priceList.get(position).getDiscription());
        holder.tvLocality.setText("Locality :- " + priceList.get(position).getLocality());
        holder.tvCountryName.setText("Country Name :- " + priceList.get(position).getCountryName());
        holder.tvPrice.setText("Price :- " + priceList.get(position).getPrice());
        holder.tvId.setText("Time :-" + priceList.get(position).getId());
        holder.tvStockUnit.setText("" + priceList.get(position).getStockUnit());
        holder.tvGradeUnit.setText("" + priceList.get(position).getGradeUnit());
        holder.tvPriceUnit.setText("" + priceList.get(position).getPriceUnit());

        SimpleDateFormat curFormater = new SimpleDateFormat("MM-YYYY-dd/hh:mm:ss");
        Date dateObj = null;
        try {
            dateObj = curFormater.parse(priceList.get(position).getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("dd MM , yyyy");

        String newDateStr = postFormater.format(dateObj);

        Log.d("TAGG", "onBindViewHolder: " + priceList.get(position).getTitle() + " || " + newDateStr);

        Glide.with(context).load(priceList.get(position).getImgUrl()).placeholder(R.drawable.rasayanm).into(holder.img);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PriceListDetail.class);
                intent.putExtra("Title", priceList.get(position).getTitle());
                intent.putExtra("Stock", priceList.get(position).getStock());
                intent.putExtra("GradeUnit", priceList.get(position).getGradeUnit());
                intent.putExtra("PriceUnit", priceList.get(position).getPriceUnit());
                intent.putExtra("StockUnit", priceList.get(position).getStockUnit());
                intent.putExtra("Discription", priceList.get(position).getDiscription());
                intent.putExtra("Price", priceList.get(position).getPrice());
                intent.putExtra("Locality", priceList.get(position).getLocality());
                intent.putExtra("CountryName", priceList.get(position).getCountryName());
                intent.putExtra("Id", priceList.get(position).getId());
                intent.putExtra("Image", priceList.get(position).getImgUrl());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    public void setPriceList(List<PriceList> priceList) {
        this.priceList = priceList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvStock, tvDiscription, tvLocality, tvCountryName, tvPrice, tvId;
        TextView tvStockUnit, tvGradeUnit, tvPriceUnit;
        ImageView img;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStockUnit = itemView.findViewById(R.id.tvStockUnit);
            tvGradeUnit = itemView.findViewById(R.id.tvGradeUnit);
            tvPriceUnit = itemView.findViewById(R.id.tvPriceUnit);
            tvId = itemView.findViewById(R.id.tvId);
            img = itemView.findViewById(R.id.imgView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvStock = itemView.findViewById(R.id.tvStock);
            tvDiscription = itemView.findViewById(R.id.tvDiscription);
            tvLocality = itemView.findViewById(R.id.tvLocality);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

        }
    }
}

