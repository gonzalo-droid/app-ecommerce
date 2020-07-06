package com.app.ecommerce.ui.home.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ecommerce.R;
import com.app.ecommerce.ui.home.AdapterCategory;
import com.app.ecommerce.ui.home.EntityCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> implements View.OnClickListener {


    private ArrayList<EntityProduct> list;
    private View.OnClickListener listener;

    public AdapterProduct(ArrayList<EntityProduct> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_product,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        view.setOnClickListener(this);

        return new AdapterProduct.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {

        holder.entityProduct= list.get(position);
        holder.tvTitle.setText(holder.entityProduct.getName());
        holder.tvDescription.setText(holder.entityProduct.getDescription_short());
        holder.tvPrice.setText("S/"+holder.entityProduct.getPrice());
        Picasso.get()
                .load(holder.entityProduct.getMiniPhoto())
                .resize(400,400)
                .centerCrop()//img centrada
                .into(holder.ivPhoto);


    }

    @Override
    public int getItemCount() {
        return list.size();
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
        public final TextView tvTitle,tvDescription,tvPrice;
        public final ImageView ivPhoto;
        private  final Button tbnAdd;
        public EntityProduct entityProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tbnAdd=itemView.findViewById(R.id.btnAdd);
            ivPhoto=itemView.findViewById(R.id.ivProductoList);

        }
    }

}
