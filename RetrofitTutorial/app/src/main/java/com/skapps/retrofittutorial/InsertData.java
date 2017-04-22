package com.skapps.retrofittutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.skapps.retrofittutorial.resources.retrofit.Loader;
import com.skapps.retrofittutorial.resources.retrofit.SyncData;

import static com.skapps.retrofittutorial.resources.GlobalClass.CustomDialogNoInternet;
import static com.skapps.retrofittutorial.resources.GlobalClass.networkAvailable;

public class InsertData extends AppCompatActivity implements View.OnClickListener {

    EditText nameTxt, ageTxt;
    Button SendBtn;
    SyncData sync;
    Loader ldr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        initUI();


    }

    private void initUI(){

        ageTxt = (EditText) findViewById(R.id.ageTxt);
        nameTxt = (EditText) findViewById(R.id.nameTxt);
        SendBtn = (Button) findViewById(R.id.SendBtn);

        sync = new SyncData( InsertData.this );
        ldr = new Loader();

        SendBtn.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.SendBtn:{

                if( ageTxt.getText().toString().trim().isEmpty() ||  nameTxt.getText().toString().trim().isEmpty()  ){

                    if( ageTxt.getText().toString().trim().isEmpty() ){
                        ageTxt.setError( "Required" );
                    }
                    if( nameTxt.getText().toString().trim().isEmpty() ){
                        nameTxt.setError( "Required" );
                    }
                }else{

                    if( networkAvailable() ){

                        sync.insertData( ldr, nameTxt.getText().toString().trim(), ageTxt.getText().toString().trim() );

                    }else{
                        CustomDialogNoInternet( getApplicationContext() );
                    }


                }

            }
            break;

        }

    }
}
