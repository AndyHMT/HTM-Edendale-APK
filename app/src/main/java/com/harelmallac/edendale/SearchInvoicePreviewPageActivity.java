package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.harelmallac.edendale.adapter.SearchInvPrevPgAdapter;
import com.harelmallac.edendale.model.SearchInvPrevPgClass;

import java.util.ArrayList;

public class SearchInvoicePreviewPageActivity extends AppCompatActivity {

    ListView LVPrev;
    ArrayList<SearchInvPrevPgClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice_preview_page);

        LVPrev = findViewById(R.id.LVPrev);

        String name = getIntent().getStringExtra("name");

        list.add(new SearchInvPrevPgClass(name,"1", "12","123"));
        list.add(new SearchInvPrevPgClass("product1","1", "12","123"));
        list.add(new SearchInvPrevPgClass("product1","1", "12","123"));
        list.add(new SearchInvPrevPgClass("product1","1", "12","123"));
        list.add(new SearchInvPrevPgClass("product1","1", "12","123"));

        SearchInvPrevPgAdapter adapter = new SearchInvPrevPgAdapter(this, R.layout.search_invoice_preview_page_layout, list);
        LVPrev.setAdapter(adapter);
    }
}