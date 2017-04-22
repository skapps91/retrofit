package com.skapps.retrofittutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.skapps.retrofittutorial.resources.retrofit.SyncData;

public class MainActivity extends AppCompatActivity {

    Button insertBtn, ViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBtn = (Button) findViewById(R.id.insertBtn);
        ViewBtn = (Button) findViewById(R.id.ViewBtn);

        SyncData sync = new SyncData( MainActivity.this );

        sync.getData( "6" );

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity( new Intent( MainActivity.this, InsertData.class ));

            }
        });

        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity( new Intent( MainActivity.this, ViewActivity.class ));

            }
        });



    }
}
