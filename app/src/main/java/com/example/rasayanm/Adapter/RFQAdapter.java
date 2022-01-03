package com.example.rasayanm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rasayanm.ActivityRFQDetail;
import com.example.rasayanm.Model.QuickOffer;
import com.example.rasayanm.Model.RFQ;
import com.example.rasayanm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RFQAdapter extends RecyclerView.Adapter<RFQAdapter.ViewHolder> {

    List<RFQ> rfqList;
    Context context;

    public RFQAdapter(List<RFQ> rfqList, Context context) {
        this.rfqList = rfqList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rfq_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvlongitude.setText("Longitude :- "+rfqList.get(position).getLongitude());
        holder.tvlatitude.setText("Latitude :- "+rfqList.get(position).getLatitude());
        holder.tvProductName.setText("Product Name :- "+rfqList.get(position).getProductName());
        holder.tvQuantity.setText("Quantity :- "+rfqList.get(position).getQuantity());
        holder.tvGrdeUnit.setText("Grade Unit :- "+rfqList.get(position).getGradeUnit());
        holder.tvQuantityUnit.setText(" "+rfqList.get(position).getQuantityUnit());
        holder.tvRfqId.setText("Time :- "+rfqList.get(position).getRfqId());
        holder.tvDiscription.setText("Discription :- "+rfqList.get(position).getDiscription());




//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ActivityRFQDetail.class);
//
//                intent.putExtra("ProductName",rfqList.get(position).getProductName());
//                intent.putExtra("Lattitude",rfqList.get(position).getLatitude());
//                intent.putExtra("GradeUnit",rfqList.get(position).getGradeUnit());
//                intent.putExtra("Longitude",rfqList.get(position).getLongitude());
//                intent.putExtra("Quantity",rfqList.get(position).getQuantity());
//                intent.putExtra("QuantityUnit",rfqList.get(position).getQuantityUnit());
//                intent.putExtra("Id",rfqList.get(position).getRfqId());
//                intent.putExtra("uploadBy",rfqList.get(position).getUploadBy());
//
//                context.startActivity(intent);
//
//                SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss");
//                Date dateObj = null;
//                try {
//                    dateObj = curFormater.parse(rfqList.get(position).getRfqId());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                SimpleDateFormat postFormater = new SimpleDateFormat("dd MM , yyyy");
//
//                String newDateStr = postFormater.format(dateObj);
//
//                Log.d("TAGG", "onBindViewHolder: "+rfqList.get(position).getProductName()+ " || "+newDateStr);
//            }
//        });

    }
    public void setRFQList(List<RFQ> rfqList){
        this.rfqList=rfqList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return rfqList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvProductName,tvQuantity,tvlatitude,tvlongitude;
        TextView tvQuantityUnit,tvGrdeUnit,tvRfqId;
        LinearLayout linearLayout;
        TextView tvDiscription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvQuantityUnit=itemView.findViewById(R.id.tvQuantityUnit);
            tvGrdeUnit=itemView.findViewById(R.id.tvGradeUnit);
            tvlatitude=itemView.findViewById(R.id.tvlatitude);
            tvlongitude=itemView.findViewById(R.id.tvlongitude);
            tvProductName=itemView.findViewById(R.id.tvProductName);
            tvQuantity=itemView.findViewById(R.id.tvQuantity);
            linearLayout=itemView.findViewById(R.id.linearLayout);
            tvRfqId=itemView.findViewById(R.id.tvRfqId);
            tvDiscription=itemView.findViewById(R.id.tvDiscription);
        }
    }
}
