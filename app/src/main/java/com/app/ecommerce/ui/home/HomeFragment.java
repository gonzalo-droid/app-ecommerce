package com.app.ecommerce.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.ecommerce.R;
import com.app.ecommerce.ui.home.product.AdapterProduct;
import com.app.ecommerce.ui.home.product.EntityProduct;
import com.app.ecommerce.utilities.LoadingClass;
import com.app.ecommerce.utilities.StaticVar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewProducto;
    private SwipeRefreshLayout swipeRecyclerProducto;

    private  ArrayList<EntityProduct> lisProducts;
    private boolean valorFresh = false;
    private FloatingActionButton fab;
    private AdapterProduct adapterProduct;

    //consumo
    public static final int MY_DEFAULT_TIMEOUT = 15000;
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue rqCategoias;
    private RequestQueue rqProductos;

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
        getCarga();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.carFragment);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

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
        });



        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

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
        });



        swipeRecyclerProducto.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto

                getProducts();
                valorFresh =true;
            }
        });



        getCategories();
        getProducts();





    }

    private void getProducts(){
        final ArrayList<EntityProduct> list = new ArrayList<>();
        String ruta = "https://www.codecix.com/api/ecommerce/todos-productos";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ruta, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Tag-Productos",response.toString());
                EntityProduct entityProduct = null;
                try {
                    JSONArray jsonArray = response.optJSONArray("data");
                    for(int i =0;i < jsonArray.length();i++){
                        entityProduct = new EntityProduct();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        entityProduct.setId(jsonObject.optString("id"));
                        entityProduct.setName(jsonObject.optString("name"));
                        if(jsonObject.optString("description_short") == null){
                            entityProduct.setDescription_short("");
                        }else{
                            entityProduct.setDescription_short(jsonObject.optString("description_short"));
                        }

                        if(jsonObject.optString("price_previous") == null){
                            entityProduct.setPrice_previous("");
                        }else{
                            entityProduct.setPrice_previous(jsonObject.optString("price_previous"));
                        }

                        if(jsonObject.optString("discount") == null){
                            entityProduct.setDiscount("");
                        }else{
                            entityProduct.setDiscount(jsonObject.optString("discount"));
                        }

                        entityProduct.setPrice_current(jsonObject.optString("price_current"));
                        entityProduct.setCategory_id(jsonObject.optString("category_id"));
                        entityProduct.setMiniPhoto(StaticVar.urlMiniPhoto+""+jsonObject.optString("image"));
                        list.add(entityProduct);
                    }

                    if(list.size() == 0){
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(new RecyclerView.Adapter() {

                            @NonNull
                            @Override
                            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                return null;
                            }

                            @Override
                            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                            }

                            @Override
                            public int getItemCount() {
                                return 0;
                            }
                        });
                    }else{

                        if(valorFresh){
                            valorFresh=false;
                            swipeRecyclerProducto.setRefreshing(false);
                        }

                        adapterProduct = new AdapterProduct(list);
                        adapterProduct.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Navigation.findNavController(v).navigate(R.id.productFragment);
                            }
                        });

                        recyclerViewProducto.setAdapter(adapterProduct);
                    }


                }catch (JSONException e){
                    Log.d("tab-error-producto :",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tab-error-producto :",error.toString());
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rqProductos.add(jsonObjectRequest);
    }

    private void getCategories(){
        final ArrayList<EntityCategory> list = new ArrayList<>();
        String ruta = "https://www.codecix.com/api/ecommerce/todos-categorias";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ruta, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Tag-Categorias",response.toString());
                EntityCategory entityCategory;
                try {
                    JSONArray jsonArray = response.optJSONArray("data");
                    for(int i =0;i < jsonArray.length();i++){
                        entityCategory = new EntityCategory();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        entityCategory.setId(jsonObject.optString("id"));
                        entityCategory.setName(jsonObject.optString("name"));
                        entityCategory.setSlug(jsonObject.optString("slug"));
                        list.add(entityCategory);
                    }

                    AdapterCategory adapterCategory = new AdapterCategory(list);
                    adapterCategory.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Navigation.findNavController(v).navigate(R.id.productFragment);
                        }
                    });

                    recyclerView.setAdapter(adapterCategory);
                }catch (JSONException e){
                    Log.d("tab-error-categoria :",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tab-error-categoria :",error.toString());
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rqCategoias.add(jsonObjectRequest);
    }

    private void getInstance(View root){

        rqCategoias = Volley.newRequestQueue(getActivity());
        rqProductos = Volley.newRequestQueue(getActivity());
        fab = root.findViewById(R.id.fab);
        recyclerView = root.findViewById(R.id.recyclerCategoria);

        swipeRecyclerProducto = root.findViewById(R.id.swipeRecyclerProducto);
        recyclerViewProducto = root.findViewById(R.id.recyclerProducto);
    }

    public void getCarga(){
        final LoadingClass loadingClass = new LoadingClass(getActivity());
        loadingClass.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingClass.dismissDialog();
            }
        },2500);
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