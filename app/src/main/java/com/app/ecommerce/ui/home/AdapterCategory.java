package com.app.ecommerce.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ecommerce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> implements View.OnClickListener {


    private ArrayList<EntityCategory> listCategory;
    private View.OnClickListener listener;

    public AdapterCategory(ArrayList<EntityCategory> listCategory) {
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_category,null,false);
        /*
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);*/

        view.setOnClickListener(this);

        return new AdapterCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.ViewHolder holder, int position) {

        holder.entityCategory= listCategory.get(position);
        holder.tvName.setText(holder.entityCategory.getName());



    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView tvName;
        public EntityCategory entityCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvName=itemView.findViewById(R.id.tvNameCategory);

        }
    }

}
