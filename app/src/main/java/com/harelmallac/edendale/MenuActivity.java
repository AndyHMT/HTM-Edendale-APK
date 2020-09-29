package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.harelmallac.edendale.database.ApiRequest;
import com.harelmallac.edendale.database.DataBaseHelper;

public class MenuActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnSync = findViewById(R.id.btnSync);

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Synchronize();
            }
        });

    }

    public void Synchronize()
    {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.getProducts(this);
        apiRequest.getAllCustomers(this,"SR00010");
        apiRequest.getAllAdresses(this,"SR00010");
        apiRequest.getAllPrices(this,"SR00010","MV07");
        apiRequest.getVat(this);
    }

}