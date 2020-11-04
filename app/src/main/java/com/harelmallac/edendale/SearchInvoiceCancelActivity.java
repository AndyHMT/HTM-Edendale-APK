package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.harelmallac.edendale.adapter.SearchInvoiceCancelAdapter;
import com.harelmallac.edendale.model.SearchInvoiceCancelClass;

import java.util.ArrayList;

public class SearchInvoiceCancelActivity extends AppCompatActivity {

    ListView LVschInCan;
    ArrayList<SearchInvoiceCancelClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice_cancel);

        LVschInCan = findViewById(R.id.LVschInCan);

        list.add(new SearchInvoiceCancelClass("Sam", R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview));
        list.add(new SearchInvoiceCancelClass("Jane", R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview));
        list.add(new SearchInvoiceCancelClass("John", R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview));
        list.add(new SearchInvoiceCancelClass("Tim", R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview));
        list.add(new SearchInvoiceCancelClass("Tom", R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview));
        list.add(new SearchInvoiceCancelClass("Jerry", R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview));


        SearchInvoiceCancelAdapter adapter = new SearchInvoiceCancelAdapter(this, R.layout.search_invoice_cancel_layout, list);
        LVschInCan.setAdapter(adapter);
    }

    public void previewClick(Context context, String txt){
        Intent intent = new Intent(context, SearchInvoicePreviewPageActivity.class);
        intent.putExtra("name",txt);
        context.startActivity(intent);

    }
}