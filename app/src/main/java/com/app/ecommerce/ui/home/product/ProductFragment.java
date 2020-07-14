package com.app.ecommerce.ui.home.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.ecommerce.R;
import com.app.ecommerce.api.ProductCategoryRequest;
import com.app.ecommerce.api.RegistroRequest;
import com.app.ecommerce.ui.RegisterActivity;
import com.app.ecommerce.utilities.LoadingClass;
import com.app.ecommerce.utilities.SharePreferenceConfig;
import com.app.ecommerce.utilities.StaticVar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRecyclerProducto;
    private  AdapterProduct adapterProduct;
    private  ArrayList<EntityProduct> lisProducts;
    private boolean valorFresh=false;
    private FloatingActionButton fabCar;
    private ProductCategoryRequest productCategoryRequest;

    //consumo
    public static final int MY_DEFAULT_TIMEOUT = 15000;
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue rqProductos;

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
        getCarga();

        fabCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.carFragment);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

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
                swipeRecyclerProducto.setRefreshing(false);
                valorFresh =true;

            }
        });



        getProducts();
    }

    private void getProducts(){
        final ArrayList<EntityProduct> list = new ArrayList<>();
        String id = SharePreferenceConfig.getInstance(getActivity()).getCategoryId();
        String ruta = "https://www.codecix.com/api/ecommerce/filter-product/"+id;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ruta, null,
                new Response.Listener<JSONObject>() {
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

                                adapterProduct = new AdapterProduct(list, new AdapterProduct.MyAdapterListener() {
                                    /*
                                    String name = list.get(recyclerView.getChildAdapterPosition(v)).getName() ;
                                        */
                                    @Override
                                    public void btnClick(View v, int position) {
                                        showAlerAddCar();
                                        String name = list.get(position).getName().toString();
                                        String id = list.get(position).getId().toString();
                                       // list.get(position).setName("holi");
                                        Log.d("TAG", "position "+position
                                                +" id: "+id
                                                +" name: "+name);
                                       // adapterProduct.notifyDataSetChanged();
                                    }

                                });

                                recyclerView.setAdapter(adapterProduct);
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

    private void getInstacias(View vista) {

        rqProductos = Volley.newRequestQueue(getActivity());
        fabCar = vista.findViewById(R.id.fabProducto);
        swipeRecyclerProducto = vista.findViewById(R.id.swipeRecyclerProducto);
        recyclerView = vista.findViewById(R.id.recyclerProducto);
    }

    private void showAlerAddCar(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.alert_add_car,
                (ViewGroup) getActivity().findViewById(R.id.alertAddCar));


        TextView text = (TextView) layout.findViewById(R.id.tvAlert);
        text.setText("Producto añadido");

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM | Gravity.LEFT,20,20);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

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
                //mostrarDialogNuevaNota();

            default:
                return super.onOptionsItemSelected(item) ;
        }
    }
}