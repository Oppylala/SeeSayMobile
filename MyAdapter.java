package com.example.user.emergencyapps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 10/14/2020.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context, context1;
    ArrayList<reportcase> users;

    public MyAdapter(Context c, ArrayList<reportcase> u) {
        context = c;
        users = u;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.reports,parent,false));

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.CrimeType.setText(users.get(position).getCrimeType());
        holder.ReportEmail.setText(users.get(position).getEmail());
        holder.ReportStation.setText(users.get(position).getStation());
        holder.ReportDetails.setText(users.get(position).getReport());
        Picasso.get().load(users.get(position).getImageid()).into(holder.profilePic);
    }

    @Override
    public int getItemCount() {
        return users.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView CrimeType, ReportEmail, ReportDetails, ReportStation;
        ImageView profilePic;

        public MyViewHolder(View itemView) {
            super(itemView);
            CrimeType = itemView.findViewById(R.id.crimeType);
            ReportEmail = itemView.findViewById(R.id.reportmail);
            ReportDetails = itemView.findViewById(R.id.reports);
            ReportStation = itemView.findViewById(R.id.rstation);
            profilePic = itemView.findViewById(R.id.rimage);

        }
    }
}
