package com.example.admin.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.ViewHolder> {
    ArrayList<String> array;
    public ArrayAdapter(ArrayList<String> randomStringList) {
        array = randomStringList;
    }

    @Override
    public ArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.array_list_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArrayAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(array.get(position));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);

        }
    }
}
