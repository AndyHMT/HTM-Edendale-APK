package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.harelmallac.edendale.adapter.SearchInvPrevPgAdapter;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.SearchInvPrevPgClass;

import java.util.ArrayList;

public class SearchInvoicePreviewPageActivity extends AppCompatActivity {

    ListView LVPrev;
    ArrayList<SearchInvPrevPgClass> list = new ArrayList<>();
    DataBaseHelper db = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice_preview_page);

        LVPrev = findViewById(R.id.LVPrev);

        String name = getIntent().getStringExtra("name");
        String[] splitText = name.split("\\r?\\n");

        String invoiceNumber = splitText[1];

        //#Varun - populate selected invoice
        populateInvoice(invoiceNumber);

        SearchInvPrevPgAdapter adapter = new SearchInvPrevPgAdapter(this, R.layout.search_invoice_preview_page_layout, list);
        LVPrev.setAdapter(adapter);
    }

    //#Varun - populate view selected invoice
    public void populateInvoice(String invoiceNum) {
        Cursor res = db.getSearchInvoiceDetails(invoiceNum);

        if(res.getCount() < 0){
            Log.e("Error","Product VAT Rate Not found.");
        }
        else {
            while (res.moveToNext()){
                String pname = res.getString(10);
                String qty = res.getString(7);
                String grossPrice = res.getString(3);
                Double total = Double.parseDouble(qty) * Double.parseDouble(grossPrice);
                list.add(new SearchInvPrevPgClass(pname,qty, grossPrice,total+""));
            }
        }
    }
}