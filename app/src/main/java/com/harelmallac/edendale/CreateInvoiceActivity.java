package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class CreateInvoiceActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView LV;
    ArrayAdapter<String> ListViewArrayAdapter;
    ArrayAdapter<String> FilteredListViewArrayAdapter;
    ArrayList<String> customerslist = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);

        LV = findViewById(R.id.cusListView);

        //String[] months = new DateFormatSymbols().getMonths();
        //ArrayAdapter<String> cusListViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, months);
        //ArrayAdapter<String> cusListViewAdapter = new ArrayAdapter<>(this, R.layout.customer_list_view_layout, months);
        //LV.setAdapter(cusListViewAdapter);
//        LV.setOnItemClickListener(this);

        //======================================================================================================




        DataBaseHelper db = new DataBaseHelper(this);
        Cursor cursor = db.getCustomer();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve customer data",Toast.LENGTH_SHORT).show();
        }
        else{
            //int i = 0;
            while(cursor.moveToNext()){

                customerslist.add(cursor.getString(1));
                //customerslist2.add(cursor.getString(1));
            }

            ListViewArrayAdapter = new ArrayAdapter<>(this,
                    R.layout.customer_list_view_layout, customerslist);
            LV.setAdapter(ListViewArrayAdapter);
        }
        //===================================================================================
        LV.setOnItemClickListener(this);


        //============================================================================
        //FILTER
        //============================================================================
        EditText listFilter = findViewById(R.id.listFilter);
        listFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
//                CreateInvoiceActivity.this.ListViewArrayAdapter.getFilter().filter(s);
                ArrayList<String> customerslist2 = new ArrayList<String>();
                    String x = cs.toString();
//                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
                for(String str: customerslist){
                    if ( str.toLowerCase().indexOf(x.toLowerCase()) != -1 ) {

                        customerslist2.add(str);


                    } else {

                        System.out.println("not found");

                    }
                }
                FilteredListViewArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.customer_list_view_layout, customerslist2);
                LV.setAdapter(FilteredListViewArrayAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //============================================================================
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String customer = parent.getItemAtPosition(position).toString();
        //Toast.makeText(getApplicationContext(),customer,Toast.LENGTH_SHORT).show();

        String cusName = null;
        Intent intent = new Intent(CreateInvoiceActivity.this, CreateInvoiceInfoPageActivity.class);
        intent.putExtra("cusName", customer);
        startActivity(intent);
    }
}