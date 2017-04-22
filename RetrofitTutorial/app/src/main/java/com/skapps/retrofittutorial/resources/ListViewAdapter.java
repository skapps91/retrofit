package com.skapps.retrofittutorial.resources;


import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.skapps.retrofittutorial.R;
import com.skapps.retrofittutorial.resources.retrofit.Loader;
import com.skapps.retrofittutorial.resources.retrofit.SyncData;

import java.util.ArrayList;
import java.util.HashMap;

import static com.skapps.retrofittutorial.resources.GlobalClass.*;


public class ListViewAdapter extends BaseAdapter
{
    public ArrayList<HashMap<String,String>> list;
    public ArrayList<String> list1;
    Activity activity;
    SyncData sync;
    Loader ldr;


    public ListViewAdapter(Activity activity, ArrayList<HashMap<String,String>> list) {
        super();
        this.activity = activity;
        sync = new SyncData( activity );
        ldr = new Loader();

        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {

        TextView txt, id;
        ImageButton updateBtn, deleteBtn;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        final ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView =  inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txt = (TextView) convertView.findViewById(R.id.txt);
            holder.id = (TextView) convertView.findViewById(R.id.id);
            holder.updateBtn = (ImageButton) convertView.findViewById(R.id.updateBtn);
            holder.deleteBtn = (ImageButton) convertView.findViewById(R.id.deleteBtn);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);




            try {


                    holder.txt.setText(map.get(LIST_TXT).toString());
                    holder.id.setText(map.get(LIST_ID).toString());

                holder.updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditDialog( holder.id.getText().toString(), holder.txt.getText().toString()  );

                        Toast.makeText(activity, "edit", Toast.LENGTH_SHORT).show();

                    }
                });

                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if( networkAvailable() ){

                            sync.deleteData( ldr,  holder.id.getText().toString() );

                        }else{
                            CustomDialogNoInternet( activity );
                        }
                    }
                });


            }
            catch(Exception e){
                System.out.println("Error ListAdapter: "+e.toString());
            }





        return convertView;
    }


    public void EditDialog(final String id, final String txt ){

        final Dialog dialog = new Dialog( activity );

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit);
        dialog.setCancelable(false);

        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.80);
        dialog.getWindow().setLayout(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT); //set below the setContentview


        final EditText NameTxt = (EditText) dialog.findViewById(R.id.NameTxt);
        final EditText AgeTxt = (EditText) dialog.findViewById(R.id.AgeTxt);
        Button updateBtn = (Button) dialog.findViewById(R.id.updateBtn);
        Button Cancelbtn = (Button) dialog.findViewById(R.id.Cancelbtn);

        NameTxt.setText( txt );

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( networkAvailable() ){

                    sync.updateData( ldr, id, NameTxt.getText().toString(), AgeTxt.getText().toString(), dialog );

                }else{
                    CustomDialogNoInternet( activity );
                }

            }
        });

        Cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


}