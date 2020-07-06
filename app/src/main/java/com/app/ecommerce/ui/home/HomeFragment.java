package com.app.ecommerce.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.ecommerce.R;
import com.app.ecommerce.ui.home.product.FullDialogProduct;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextInputLayout tilBuscar;
    private AdapterCategory adapterCategory ;

    private  ArrayList<EntityCategory> listCategory;
    private boolean valorFresh = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getInstance(root);

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

        listCategory = new ArrayList<>();
        listCategory.add(new EntityCategory("Postres","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        listCategory.add(new EntityCategory("Bebidas","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        listCategory.add(new EntityCategory("Carnes","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        listCategory.add(new EntityCategory("Pescados","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        // asociamos el adaptador al recyclerview
        adapterCategory = new AdapterCategory(listCategory);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                swipeRefreshLayout.setRefreshing(false);
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


        adapterCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullDialogProduct dialogProduct = new FullDialogProduct();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                dialogProduct.show(ft,FullDialogProduct.TAG);

            }
        });

        recyclerView.setAdapter(adapterCategory);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });


        return root;
    }



    private void getInstance(View root){
        tilBuscar = root.findViewById(R.id.tilBuscar);
        recyclerView = root.findViewById(R.id.recyclerCategoria);
        swipeRefreshLayout = root.findViewById(R.id.swipeRecyclerCategoria);
    }
}