package com.example.mahmoud.sanaye;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.MyViewHolder> {

    List<EmployeeModel> models;
    Context context;
    private LayoutInflater mInflater;

    public AdapterItems(List<EmployeeModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Name.setText(models.get(position).getName());
        holder.job.setText(models.get(position).getJob());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        TextView job;

        public MyViewHolder(View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.rv_user_name);
            job = itemView.findViewById(R.id.rv_user_job);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmployeeModel model= models.get(getAdapterPosition());
                    Intent intent = new Intent(context,EmployeeDetails.class);
                    intent.putExtra("model",model);
                    context.startActivity(intent);
                }
            });
        }

    }
}
