package com.skapps.retrofittutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.skapps.retrofittutorial.resources.retrofit.Loader;
import com.skapps.retrofittutorial.resources.retrofit.SyncData;

import static com.skapps.retrofittutorial.resources.GlobalClass.CustomDialogNoInternet;
import static com.skapps.retrofittutorial.resources.GlobalClass.networkAvailable;

public class ViewActivity extends AppCompatActivity {

    ListView DataList;
    SyncData sync;
    Loader ldr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        sync = new SyncData( ViewActivity.this );
        ldr = new Loader();
        DataList = (ListView)findViewById(R.id.DataList);


        if( networkAvailable() ){

            sync.selectData( ldr, DataList );

        }else{
            CustomDialogNoInternet( getApplicationContext() );
        }



    }
}
