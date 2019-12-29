package com.example.retroft_recycleview_easy_demo.Model.AndroidVersions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retroft_recycleview_easy_demo.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Android_getter_setter> android;
    // private ArrayList<Android_getter_setter> androidGettersetter;
    public DataAdapter(ArrayList<Android_getter_setter> android) {
        this.android = android;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tv_name.setText(android.get(i).getName());
        holder.tv_version.setText(android.get(i).getVer());
        holder.tv_api_level.setText(android.get(i).getApi());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_name = (TextView)itemView.findViewById(R.id.tv_n);
            tv_version = (TextView)itemView.findViewById(R.id.tv_v);
            tv_api_level = (TextView)itemView.findViewById(R.id.tv_api);

        }
    }
}