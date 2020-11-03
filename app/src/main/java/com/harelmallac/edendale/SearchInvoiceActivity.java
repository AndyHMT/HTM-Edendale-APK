package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.harelmallac.edendale.adapter.SearchInvoiceAdapter;
import com.harelmallac.edendale.adapter.SearchInvoiceApater;
import com.harelmallac.edendale.model.SearchInvoiceClass;

import java.util.ArrayList;

public class SearchInvoiceActivity extends AppCompatActivity {

    private ListView LVsInv;
    private ArrayList<SearchInvoiceClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice);

        LVsInv = findViewById(R.id.LVsearchInv);
        list.add(new SearchInvoiceClass("James", R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview, R.drawable.ic_baseline_cancel));
        list.add(new SearchInvoiceClass("Sarah",R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview, R.drawable.ic_baseline_cancel));
        list.add(new SearchInvoiceClass("Page",R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview, R.drawable.ic_baseline_cancel));
        list.add(new SearchInvoiceClass("Abel",R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview, R.drawable.ic_baseline_cancel));
        //list.add(new SearchInvoiceClass("James"));
        SearchInvoiceAdapter adapter = new SearchInvoiceAdapter(this, list);
        LVsInv.setAdapter(adapter);
    }


    public void previewClick(Context context, String txt){
        Intent intent = new Intent(context, SearchInvoicePreviewPageActivity.class);
        intent.putExtra("name",txt);
        context.startActivity(intent);

    }

}