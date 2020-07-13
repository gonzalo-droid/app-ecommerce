package com.app.ecommerce.api;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductCategoryRequest extends JsonObjectRequest {

    private Map<String,String> parametros;
    public ProductCategoryRequest(String category_id,String ruta,Response.Listener<JSONObject> listener){
        super(Request.Method.GET, ruta,null, listener,  null);
        parametros = new HashMap<>();
        parametros.put("category_id", category_id);
    }

    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }

}
