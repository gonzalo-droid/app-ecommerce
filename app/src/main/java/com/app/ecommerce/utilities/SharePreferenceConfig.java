package com.app.ecommerce.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.List;

public class SharePreferenceConfig {
    private SharedPreferences sharedPreferences;
    private static SharePreferenceConfig minst ;
    private Context context;
    private static String SHARD_PROFILE = "myapp";
    private static String KEY_CATEGORY = "categoriaId";
    private static List<String> KEY_LIST = Collections.singletonList("id");

    private SharePreferenceConfig(Context context){this.context=context;}

    public static synchronized SharePreferenceConfig getInstance(Context context){
        if(minst == null){
           minst = new SharePreferenceConfig(context);
        }
        return  minst;
    }

    public void setCategoryId(String id){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARD_PROFILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CATEGORY,id);
        editor.apply();
    }

    public String getCategoryId(){
        SharedPreferences sharePreferenceConfig =context.getSharedPreferences(SHARD_PROFILE,Context.MODE_PRIVATE);
        return sharePreferenceConfig.getString(KEY_CATEGORY,"0");
    }


}
