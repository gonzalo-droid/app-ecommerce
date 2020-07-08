package com.app.ecommerce.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.app.ecommerce.R;

public class LoadingClass {

    private Activity activity;
    private AlertDialog alertDialogM;

    public LoadingClass(){

    }
    public LoadingClass(Activity myactivity){
        activity = myactivity;

    }
    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.simple_loading,null));
        builder.setCancelable(true);

        alertDialogM = builder.create();
        alertDialogM.show();
    }

    public  void dismissDialog(){
        alertDialogM.dismiss();
    }
}
