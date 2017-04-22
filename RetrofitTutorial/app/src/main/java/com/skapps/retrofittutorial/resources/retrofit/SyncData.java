package com.skapps.retrofittutorial.resources.retrofit;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skapps.retrofittutorial.resources.ListViewAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.skapps.retrofittutorial.resources.GlobalClass.LIST_ID;
import static com.skapps.retrofittutorial.resources.GlobalClass.LIST_TXT;
import static com.skapps.retrofittutorial.resources.GlobalClass.RetrofitInitilizer;

public class SyncData {

    static Context ctx;

    public SyncData( Context c ){
        this.ctx = c;
    }

    public void selectData(final Loader ldr, final ListView DataList){

        ldr.showDialog( ctx );

        RetrofitWebService api = RetrofitInitilizer();

        api.selectData(

                "dummy abc", // write anything as it will not be used in server side
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        BufferedReader reader = null;

                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            output = reader.readLine();

                            Log.d("Prof",output+"--- output" );

                            JSONObject json  = new JSONObject(output);

                            Log.d("Prof",json.toString()+"---");


                            String status = json.getString("code");

                            if( status.equalsIgnoreCase("1") ){

                                ArrayList<HashMap<String,String >> Arr = new ArrayList<HashMap<String, String>>();

                                JSONArray jArr = json.getJSONArray( "values" );
                                JSONObject c = null;
                                for( int i=0; i<jArr.length(); i++ ){

                                    c = jArr.getJSONObject( i );

                                    HashMap temp = new HashMap();

                                    temp.put(LIST_TXT, c.getString( "name" ) );
                                    temp.put(LIST_ID, c.getString( "id" ) );

                                    Arr.add( temp );


                                }

                                ListViewAdapter adp = new ListViewAdapter( (Activity) ctx, Arr );
                                DataList.setAdapter( adp );


                            }else if( status.equalsIgnoreCase("0") ){
                                Toast.makeText( ctx, "Data Not Found",Toast.LENGTH_SHORT ).show();
                            }

                            ldr.HideLoader();




                        } catch (IOException e) {

                            ldr.HideLoader();

                            Log.d("Prof","Excep "+e.toString());
                            Toast.makeText(ctx, "I/O Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        } catch (JSONException e) {
                            ldr.HideLoader();

                            Log.d("Prof","Excep JSOn "+e.toString());
                            Toast.makeText(ctx, "Json Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        ldr.HideLoader();

                        Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }
        );


    }

    public void insertData(final Loader ldr, final String name, String age ){

        ldr.showDialog( ctx );

        RetrofitWebService api = RetrofitInitilizer();

        api.insert(

                name,
                age,
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        BufferedReader reader = null;

                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            output = reader.readLine();

                            Log.d("Prof",output+"--- output" );

                            JSONObject json  = new JSONObject(output);

                            Log.d("Prof",json.toString()+"---");


                            String status = json.getString("code");

                            if( status.equalsIgnoreCase("1") ){
                                Toast.makeText( ctx, "Data Inserted",Toast.LENGTH_SHORT ).show();

                            }else if( status.equalsIgnoreCase("0") ){
                                Toast.makeText( ctx, "Data Not Inserted",Toast.LENGTH_SHORT ).show();
                            }

                            ldr.HideLoader();




                        } catch (IOException e) {

                            ldr.HideLoader();

                            Log.d("Prof","Excep "+e.toString());
                            Toast.makeText(ctx, "I/O Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        } catch (JSONException e) {
                            ldr.HideLoader();

                            Log.d("Prof","Excep JSOn "+e.toString());
                            Toast.makeText(ctx, "Json Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        ldr.HideLoader();

                        Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }
        );


    }

    public void updateData(final Loader ldr, final String id, final String name, String age, final Dialog d ){

        ldr.showDialog( ctx );

        RetrofitWebService api = RetrofitInitilizer();

        api.update(

                id,
                name,
                age,
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        BufferedReader reader = null;

                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            output = reader.readLine();

                            Log.d("Prof",output+"--- output" );

                            JSONObject json  = new JSONObject(output);

                            Log.d("Prof",json.toString()+"---");


                            String status = json.getString("code");

                            if( status.equalsIgnoreCase("1") ){
                                d.dismiss();
                                Toast.makeText( ctx, "Data Updated",Toast.LENGTH_SHORT ).show();

                            }else if( status.equalsIgnoreCase("0") ){
                                Toast.makeText( ctx, "Data Not Updated",Toast.LENGTH_SHORT ).show();
                            }

                            ldr.HideLoader();




                        } catch (IOException e) {

                            ldr.HideLoader();

                            Log.d("Prof","Excep "+e.toString());
                            Toast.makeText(ctx, "I/O Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        } catch (JSONException e) {
                            ldr.HideLoader();

                            Log.d("Prof","Excep JSOn "+e.toString());
                            Toast.makeText(ctx, "Json Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        ldr.HideLoader();

                        Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }
        );


    }

    public void deleteData(final Loader ldr, final String id ){

        ldr.showDialog( ctx );

        RetrofitWebService api = RetrofitInitilizer();

        api.delete(

                id,
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        BufferedReader reader = null;

                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            output = reader.readLine();

                            Log.d("Prof",output+"--- output" );

                            JSONObject json  = new JSONObject(output);

                            Log.d("Prof",json.toString()+"---");


                            String status = json.getString("code");

                            ldr.HideLoader();

                            if( status.equalsIgnoreCase("1") ){

                                Toast.makeText( ctx, "Data Deleted",Toast.LENGTH_SHORT ).show();

                            }else if( status.equalsIgnoreCase("0") ){
                                Toast.makeText( ctx, "Data Not Deleted",Toast.LENGTH_SHORT ).show();
                            }

                        } catch (IOException e) {

                            ldr.HideLoader();

                            Log.d("Prof","Excep "+e.toString());
                            Toast.makeText(ctx, "I/O Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        } catch (JSONException e) {
                            ldr.HideLoader();

                            Log.d("Prof","Excep JSOn "+e.toString());
                            Toast.makeText(ctx, "Json Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        ldr.HideLoader();

                        Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }
        );


    }


    public void getData( String id ){

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id ); // Params will be required.. you can add multiple values as per needed

        RetrofitWebService api = RetrofitInitilizer();

        api.get_data(

                params,
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {


                        BufferedReader reader = null;

                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            output = reader.readLine();

                            Log.d("Prof",output+"--- output" );

                            JSONObject json  = new JSONObject(output);

                            Log.d("Prof",json.toString()+"---");


                            Toast.makeText( ctx , "Values\n"+json.toString(), Toast.LENGTH_SHORT).show();
/*
                            ldr.HideLoader();
*/

                            /*HistoryTxt.setText( json.getString("History") );

                            Picasso.with(ctx).load( json.getString("ImgUrl") ).into(BannerImg);
*/

                        } catch (IOException e) {

/*
                            ldr.HideLoader();
*/

                            Log.d("Prof","Excep "+e.toString());
                            Toast.makeText(ctx, "I/O Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        } catch (JSONException e) {
/*
                            ldr.HideLoader();
*/

                            Log.d("Prof","Excep JSOn "+e.toString());
                            Toast.makeText(ctx, "Json Exception\n"+e.toString(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        /*ldr.HideLoader();*/

                        Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }
        );


    }



}
