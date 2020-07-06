package com.app.ecommerce.ui.home.product;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.ecommerce.R;
import com.app.ecommerce.ui.home.AdapterCategory;
import com.app.ecommerce.ui.home.EntityCategory;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FullDialogProduct extends DialogFragment {
    public static final String TAG = "FullScreenDialog";
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextInputLayout tilBuscar;
    private SwipeRefreshLayout swipeRecyclerProducto;
    private  AdapterProduct adapterProduct;
    private  ArrayList<EntityProduct> lisProducts;
    private boolean valorFresh=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(FullDialogProduct.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View vista = getActivity().getLayoutInflater().inflate(R.layout.full_dialog_product, container, false);
        getInstacias(vista);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        /*
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });*/

        lisProducts = new ArrayList<>();
        lisProducts.add(new EntityProduct("Arroz con pato","pata criollo y arroz japones","23.4","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Cerveza","toretto","45.90","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Ramen","sopa de anime","59.23","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Filete con pure","File al horno","11.11","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        // asociamos el adaptador al recyclerview
        adapterProduct = new AdapterProduct(lisProducts);

        swipeRecyclerProducto.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                swipeRecyclerProducto.setRefreshing(false);
                valorFresh =true;
                /*
                if(tilBuscar.getEditText().getText().length() >= 3){
                    //getCategories();

                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("Ingrese mínimo 3 caracteres")
                            .setNegativeButton( "Reintentar",null)
                            .create()
                            .show();
                }*/
            }
        });


        adapterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullDialogProduct dialogProduct = new FullDialogProduct();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                dialogProduct.show(ft,FullDialogProduct.TAG);

            }
        });

        recyclerView.setAdapter(adapterProduct);

        return  vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullDialogProduct.this.dismiss();
            }
        });

        toolbar.setTitle("Productos");
    }

    private void getInstacias(View vista) {
        toolbar = vista.findViewById(R.id.toolbarProduct);
        swipeRecyclerProducto = vista.findViewById(R.id.swipeRecyclerProducto);
        recyclerView = vista.findViewById(R.id.recyclerProducto);
        tilBuscar = vista.findViewById(R.id.tilBuscarProducto);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
