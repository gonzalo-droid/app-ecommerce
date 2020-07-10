package com.app.ecommerce;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {

    private static final String ruta= "https://www.codecix.com/api/ecommerce/registro";
    private Map<String,String> parametros;
    public RegistroRequest(String name, String email, String password, String password_confirmation,
                           Response.Listener<String> listener){
        super(Request.Method.POST, ruta, listener,  null);
        parametros = new HashMap<>();
        parametros.put("name", name);
        parametros.put("email", email);
        parametros.put("password", password);
        parametros.put("password_confirmation", password_confirmation);
    }

    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }

}
