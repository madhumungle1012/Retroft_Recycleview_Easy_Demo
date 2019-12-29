package com.example.retroft_recycleview_easy_demo.Model.CodingFlowExamples;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retroft_recycleview_easy_demo.Model.AndroidVersions.Android_getter_setter;
import com.example.retroft_recycleview_easy_demo.Model.AndroidVersions.DataAdapter;
import com.example.retroft_recycleview_easy_demo.R;

import java.util.ArrayList;

public class CommentDataAdapter extends RecyclerView.Adapter<CommentDataAdapter.ViewHolder> {
    private ArrayList<Comment> android;
    // private ArrayList<Android_getter_setter> androidGettersetter;
    public CommentDataAdapter(ArrayList<Comment> android) {
        this.android = android;
    }

    @NonNull
    @Override
    public CommentDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new CommentDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentDataAdapter.ViewHolder holder, int i) {
        holder.tv_name.setText(android.get(i).getName());
        holder.tv_version.setText(android.get(i).getEmail());
        holder.tv_api_level.setText(android.get(i).getBody());
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