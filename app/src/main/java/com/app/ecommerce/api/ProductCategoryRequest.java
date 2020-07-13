package com.app.ecommerce.api;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductCategoryRequest extends StringRequest {

    private static String ruta= "https://www.codecix.com/api/ecommerce/categoria/productos";
    private Map<String, String> parametros;

    public ProductCategoryRequest(String category_id, Response.Listener<String> listener) {
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("category_id", category_id);
    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }

}
