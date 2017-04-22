package com.skapps.retrofittutorial.resources.retrofit;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.skapps.retrofittutorial.R;


public class Loader {

    com.wang.avi.AVLoadingIndicatorView ldr;

    Dialog dialog;

    public void showDialog( Context activity ){ // Show progressDialog when Heavy processing is going on like Syncing is on
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_custom_loader);

        ldr = ( com.wang.avi.AVLoadingIndicatorView )dialog.findViewById(R.id.avloadingIndicatorView);

        ldr.setVisibility(View.VISIBLE);


        dialog.show();

    }

    public void HideLoader(){ // Hide dialog when work is done.

        ldr.setVisibility(View.GONE);

        dialog.dismiss();

    }

}
