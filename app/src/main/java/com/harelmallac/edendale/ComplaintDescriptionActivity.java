package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ComplaintsModel;
import com.harelmallac.edendale.model.IdentityModel;
import com.harelmallac.edendale.model.SalesmanComplaintsModel;

import java.sql.Date;
import java.time.LocalDate;

public class ComplaintDescriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DataBaseHelper db;
    String cusName = null;
    String cusAddress = null;
    String cusPhone = null;
    String cusEmail = null;

    String proSpinner = null;
    String proName = null;
    String proDescription = null;
    String purDate = null;
    String placeOfPur = null;
    Boolean dairy = false;
    Boolean dry = false;
    Boolean liquid = false;
    Boolean frozen = false;
    Boolean productQuality = false;
    Boolean taste = false;
    Boolean deposit = false;
    Boolean packaging = false;
    Boolean solubitlity = false;
    Boolean expiry = false;
    Boolean prodOther = false;
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
                    if (comSpinner.equals("Dairy"))
                    {
                        dairy= true;
                        frozen = false;
                        liquid = false;
                        dry = false;
                    }

                if (comSpinner.equals("Frozen"))
                {
                    dairy= false;
                    frozen = true;
                    liquid = false;
                    dry = false;
                }

                if (comSpinner.equals("Liquid"))
                {
                    dairy= false;
                    frozen = false;
                    liquid = true;
                    dry = false;
                }

                if (comSpinner.equals("Dry"))
                {
                    dairy= false;
                    frozen = false;
                    liquid = false;
                    dry = true;
                }

                if(proSpinner.equals("Product Quality"))
                {
                    productQuality = true;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                }

                if(proSpinner.equals("Taste"))
                {
                    productQuality = false;
                    taste = true;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                }

                if(proSpinner.equals("Deposit"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = true;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                }

                if(proSpinner.equals("Packaging"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = true;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                }

                if(proSpinner.equals("Solubility"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = true;
                    expiry = false;
                    prodOther = false;
                }

                if(proSpinner.equals("Expiry"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = true;
                    prodOther = false;
                }

                if(proSpinner.equals("Other"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = true;
                }
                Log.e("Product Name",proName);

                String productId = db.getProductId(proName);
                IdentityModel product = new IdentityModel(productId);



                SalesmanComplaintsModel complaints = new SalesmanComplaintsModel(comDescrip,cusAddress,cusName,dairy,deposit,dry,cusEmail,expiry,comResp,frozen,liquid,comOther,packaging,cusPhone,product,proDescription,productQuality,purDate,placeOfPur,solubitlity,taste,purDate);
                db.addComplaint(complaints);

                Log.e("Error Message",cusName);

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
        if(z.equals("Other"))
        {
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