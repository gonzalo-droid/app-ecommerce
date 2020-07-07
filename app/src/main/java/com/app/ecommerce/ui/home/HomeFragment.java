package com.app.ecommerce.ui.home;

import android.content.ContentQueryMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.ecommerce.R;
import com.app.ecommerce.ui.home.product.AdapterProduct;
import com.app.ecommerce.ui.home.product.EntityProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private AdapterCategory adapterCategory ;
    private RecyclerView recyclerViewProducto;
    private SwipeRefreshLayout swipeRecyclerProducto;

    private  ArrayList<EntityProduct> lisProducts;
    private  ArrayList<EntityCategory> listCategory;
    private boolean valorFresh = false;
    private FloatingActionButton fab;
    private AdapterProduct adapterProduct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getInstance(view);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.carFragment);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
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



        adapterCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.productFragment);
                /*
                FullDialogProduct dialogProduct = new FullDialogProduct();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                dialogProduct.show(ft,FullDialogProduct.TAG);*/

            }
        });

        recyclerView.setAdapter(adapterCategory);



        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
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
        lisProducts.add(new EntityProduct("Cerveza","toretto","45.90","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Arroz con pato","pata criollo y arroz japones","23.4","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Cerveza","toretto","45.90","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Cerveza","toretto","45.90","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Ramen","sopa de anime","59.23","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Filete con pure","File al horno","11.11","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        lisProducts.add(new EntityProduct("Cerveza","toretto","45.90","https://loremflickr.com/cache/resized/65535_49788248126_d26e39154b_h_1000_800_nofilter.jpg"));
        // asociamos el adaptador al recyclerview
        adapterProduct = new AdapterProduct(lisProducts);

        swipeRecyclerProducto.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                swipeRecyclerProducto.setRefreshing(false);
                valorFresh =true;
            }
        });


        adapterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        recyclerViewProducto.setAdapter(adapterProduct);


    }

    private void getInstance(View root){
        fab = root.findViewById(R.id.fab);
        recyclerView = root.findViewById(R.id.recyclerCategoria);

        swipeRecyclerProducto = root.findViewById(R.id.swipeRecyclerProducto);
        recyclerViewProducto = root.findViewById(R.id.recyclerProducto);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:


                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapterProduct.getFilter().filter(newText);
                        return false;
                    }


                });

            default:

                return super.onOptionsItemSelected(item) ;
        }
    }
}