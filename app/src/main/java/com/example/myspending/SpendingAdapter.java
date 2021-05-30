package com.example.myspending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpendingAdapter extends RecyclerView.Adapter<SpendingAdapter.ViewHolder> {
    ArrayList<Spending> spendings = new ArrayList<Spending>();
    OnSpendingListener listener;

    @NonNull
    @Override
    public SpendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.spending_list, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpendingAdapter.ViewHolder holder, int position) {
        String name = spendings.get(position).name;
        String nominal = "Rp. " + spendings.get(position).nominal;
        String date = spendings.get(position).date;

        holder.tv_name.setText(name);
        holder.tv_nominal.setText(nominal);
        holder.tv_date.setText(date);

    }

    @Override
    public int getItemCount() {
        return spendings.size();
    }

    public void setData(ArrayList<Spending> spendings, OnSpendingListener listener){
        this.spendings = spendings;
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name, tv_nominal, tv_date;
        OnSpendingListener listener;

        public ViewHolder(@NonNull View itemView, OnSpendingListener listener) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_nominal = itemView.findViewById(R.id.tv_nominal);
            tv_date = itemView.findViewById(R.id.tv_date);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.OnSpendingClick(getAdapterPosition());
        }
    }
    public interface OnSpendingListener{
        void OnSpendingClick(int position);
    }

}
