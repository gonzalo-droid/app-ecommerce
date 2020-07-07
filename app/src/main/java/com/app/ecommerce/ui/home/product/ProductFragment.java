package com.app.ecommerce.ui.home.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.app.ecommerce.MenuActivity;
import com.app.ecommerce.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRecyclerProducto;
    private  AdapterProduct adapterProduct;
    private  ArrayList<EntityProduct> lisProducts;
    private boolean valorFresh=false;
    private FloatingActionButton fabCar;

    public ProductFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // definir menu de opciones propia del fragment
        setHasOptionsMenu(true);

        getActivity().setTitle("Producto");
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInstacias(view);

        fabCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.carFragment);
            }
        });

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


            }
        });

        recyclerView.setAdapter(adapterProduct);
    }

    private void getInstacias(View vista) {

        fabCar = vista.findViewById(R.id.fabProducto);
        swipeRecyclerProducto = vista.findViewById(R.id.swipeRecyclerProducto);
        recyclerView = vista.findViewById(R.id.recyclerProducto);
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
                //mostrarDialogNuevaNota();

            default:
                return super.onOptionsItemSelected(item) ;
        }
    }
}