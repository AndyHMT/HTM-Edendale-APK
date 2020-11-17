package com.harelmallac.edendale;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.adapter.RemittanceAdapter;
import com.harelmallac.edendale.model.RemittanceClass;

import java.util.ArrayList;

public class RemittanceActivity extends AppCompatActivity {

    ListView LVrmtList;
    ArrayList<RemittanceClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remittance);


        LVrmtList = findViewById(R.id.LVrmtList);

        if(list.size() == 0)
        {
            list.add(new RemittanceClass("x Rs 2000", ""));
            list.add(new RemittanceClass("x Rs 1000", ""));
            list.add(new RemittanceClass("x Rs 500", ""));
            list.add(new RemittanceClass("x Rs 200", ""));
            list.add(new RemittanceClass("x Rs 100", ""));
            list.add(new RemittanceClass("x Rs 50", ""));
            list.add(new RemittanceClass("x Rs 25", ""));
            list.add(new RemittanceClass("x Rs 20", ""));
            list.add(new RemittanceClass("x Rs 10", ""));
            list.add(new RemittanceClass("x Rs 5", ""));
            list.add(new RemittanceClass("x Rs 1", ""));
            list.add(new RemittanceClass("x Rs 0.50", ""));
            list.add(new RemittanceClass("x Rs 0.20", ""));
            list.add(new RemittanceClass("x Rs 0.05", ""));

            final RemittanceAdapter adapter = new RemittanceAdapter(this, list);
            LVrmtList.setAdapter(adapter);

            Button validate = findViewById(R.id.button4);
            validate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> aPrice = new ArrayList<>();
                    ArrayList<String> aQty = new ArrayList<>();

                    for(int i = 0; i < list.size(); i++){
                        String price = list.get(i).getRmtNum();
                        String qty = list.get(i).getRmtQty();
                        if (qty != "") {
                            Log.e("selected values ", "Id : "+qty+"Product: "+price+"");
                            aPrice.add(price);
                            aQty.add(qty);
                        }
                    }
                    Intent intent = new Intent(RemittanceActivity.this, RemittancePreviewActivity.class);
                    intent.putExtra("price", aPrice);
                    intent.putExtra("amount", aQty);
                    startActivity(intent);
                }
            });
        }





    }
}