package com.harelmallac.edendale;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.adapter.RemittancePreviewAdapter;
import com.harelmallac.edendale.model.RemittancePreviewClass;

import java.util.ArrayList;

public class RemittancePreviewActivity extends AppCompatActivity {

    ListView LVRprevList;
    ArrayList<RemittancePreviewClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remittance_preview);

        LVRprevList = findViewById(R.id.LVRprevList);

        list.add(new RemittancePreviewClass("x Rs 2000", "3", "6000.00"));
        list.add(new RemittancePreviewClass("x Rs 1000", "8", "8000.00"));

        RemittancePreviewAdapter adapter = new RemittancePreviewAdapter(this, R.layout.remittance_preview_list_layout, list);
        LVRprevList.setAdapter(adapter);
    }
}