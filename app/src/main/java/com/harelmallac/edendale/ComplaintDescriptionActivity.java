package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ComplaintDescriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_description);
        Spinner ComSpinner = findViewById(R.id.spinnerComplaint);
        Toast.makeText(getApplicationContext(),ComSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        ComSpinner.setOnItemSelectedListener(this);

        Button butNext = findViewById(R.id.button);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cusName = null;
                String cusAddress = null;
                String cusPhone = null;
                String cusEmail = null;

                String proSpinner = null;
                String proName = null;
                String proDescription = null;
                String purDate = null;
                String placeOfPur = null;

                if( getIntent().getExtras() != null)
                {
                    cusName = getIntent().getStringExtra("cusName");
                    cusAddress = getIntent().getStringExtra("cusAddress");
                    cusPhone = getIntent().getStringExtra("cusPhone");
                    cusEmail = getIntent().getStringExtra("cusEmail");
                    proSpinner = getIntent().getStringExtra("proSpinner");
                    proName = getIntent().getStringExtra("proName");
                    proDescription = getIntent().getStringExtra("proDescription");
                    purDate = getIntent().getStringExtra("purDate");
                    placeOfPur = getIntent().getStringExtra("placeOfPur");
                }

                Spinner ComSpinner = findViewById(R.id.spinnerComplaint);
                EditText other =findViewById(R.id.otherSpecification);
                EditText comDescription =findViewById(R.id.complaintDescription);
                EditText comResponse = findViewById(R.id.complaintResponse);

                String comSpinner = ComSpinner.getSelectedItem().toString();
                String comOther = other.getText().toString();
                String comDescrip = comDescription.getText().toString();
                String comResp = comResponse.getText().toString();

                if (comDescrip.isEmpty()==false && comResp.isEmpty()==false ){
                    if(comSpinner.equals("Other")){
                        if (comOther.isEmpty()==false){
                            Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Please fill in all of the fields",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        //==============================
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill in all of the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView customerView2 = findViewById(R.id.customerView2);
        customerView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ComplaintDescriptionActivity.this, ComplaintFormActivity.class);
                startActivity(intent2);
            }
        });

        TextView productView = findViewById(R.id.productView);
        productView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ComplaintDescriptionActivity.this, ComplaintProductActivity.class);
                startActivity(intent3);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String z = parent.getSelectedItem().toString();
        //Toast.makeText(this,z,Toast.LENGTH_SHORT).show();
        EditText specification = findViewById(R.id.otherSpecification);
        if(z.equals("Other")){
            //Toast.makeText(this,parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            specification.setEnabled(true);
            specification.setHintTextColor(getResources().getColor(R.color.red));
        }
        else
        {
            specification.setText("");
            specification.setEnabled(false);
            specification.setHintTextColor(getResources().getColor(R.color.darkGray));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}