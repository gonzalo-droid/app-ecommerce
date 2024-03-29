package com.app.ecommerce.ui.home.product;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ecommerce.R;
import com.app.ecommerce.ui.home.AdapterCategory;
import com.app.ecommerce.ui.home.EntityCategory;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> implements View.OnClickListener ,
        Filterable {


    private ArrayList<EntityProduct> list;
    private ArrayList<EntityProduct> listAll;
    private View.OnClickListener listener;
    public MyAdapterListener onClickListener;

    public interface MyAdapterListener {

        void btnClick(View v, int position);
    }

    public AdapterProduct(ArrayList<EntityProduct> list,MyAdapterListener myAdapterListener) {
        this.list = list;
        this.listAll = new ArrayList<>(list);
        this.onClickListener= myAdapterListener;
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
        holder.tvPrice.setText("S/"+holder.entityProduct.getPrice_current());
        Picasso.get()
                .load(holder.entityProduct.getMiniPhoto())
                .resize(400,400)
                .centerCrop()//img centrada
                .into(holder.ivPhoto);

        holder.onClickListener = this.onClickListener;


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

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<EntityProduct> filterList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filterList.addAll(listAll);
            }else {
                for(EntityProduct product:listAll){
                    if(product.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterList.add(product);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values=filterList;
            return filterResults;
        }

        // run on a ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends EntityProduct>) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        public final View view;
        public final TextView tvTitle,tvDescription,tvPrice;
        public final ImageView ivPhoto;
        public  Button btn;
        public EntityProduct entityProduct;
        public MyAdapterListener onClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            ivPhoto=itemView.findViewById(R.id.ivProductoList);
            btn=itemView.findViewById(R.id.btnAdd);

            btn.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickListener.btnClick(v, getAdapterPosition());
        }
    }


}
