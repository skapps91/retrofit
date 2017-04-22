package com.skapps.retrofittutorial.resources;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.skapps.retrofittutorial.R;
import com.skapps.retrofittutorial.resources.retrofit.RetrofitWebService;

import java.io.IOException;

import retrofit.RestAdapter;



public class GlobalClass {

    public static String DOMAIN = "http://abc.com"; // The Live Domain
    public static String DOMAIN_CHK = "http://192.168.8.100:80/projects/retrofit/"; // dummy Domain its linked with XAMPP Server.


    /**
     * Variables defined for List Items START
     */
    public static String LIST_TXT = "TXT";
    public static String LIST_ID = "id";

    /**
     * Variables defined for List Items ENDS
     */


    public static RetrofitWebService RetrofitInitilizer(){ //It Initiates the retrofit function. we will use this when we want to send/receive data

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(DOMAIN_CHK) //Setting the Root URL
                .build(); //Finally building the adapter

        RetrofitWebService api = adapter.create(RetrofitWebService.class);

        return api;
    }

    public static boolean networkAvailable() { // This function is used to check internet off or on. We will check it via pinging www.google.com, We can put the IP address of google or its name, Similarly you can out your own web link if you need.

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8"); // Google IP
            //Process ipProcess = runtime.exec("/system/bin/ping -c 1 google.com");
            int exitValue = ipProcess.waitFor();

            Log.d("IP ADDR", ipProcess.toString());
            Log.d("IP ADDR", String.valueOf(exitValue));
            return (exitValue == 0 || exitValue == 1); // In return if you get 1 or 0 it means the net connection is fine. It varies if you use Cellular net or Wifi. Other than these 2 signals your net is not available or else check the internet permission in your manifest.

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;


    }



    public static void CustomDialogNoInternet(Context ctx){ // Use this function if net is not available. AlertDialog.
			 /*
			  * Custom Dialog box starts
			  */

        AlertDialog alert = new AlertDialog.Builder( ctx ).create();
        alert.setTitle("Sorry");
        alert.setMessage("Please Check Your Internet Connection");
        alert.setIcon(R.drawable.ic_alert);
        alert.show();
			/*
			  * Custom dialog box ends
			  */

    }


}
