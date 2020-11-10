package com.harelmallac.edendale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.harelmallac.edendale.adapter.SearchInvoiceAdapter;
import com.harelmallac.edendale.adapter.SearchInvoiceApater;
import com.harelmallac.edendale.model.SearchInvoiceClass;

import java.util.ArrayList;

public class SearchInvoiceActivity extends AppCompatActivity {

    private static ListView LVsInv;
    private static ArrayList<SearchInvoiceClass> list = new ArrayList<>();

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

        Button cancelPage = findViewById(R.id.button5);
        cancelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchInvoiceActivity.this, SearchInvoiceCancelActivity.class);
                startActivity(intent);
            }
        });



    }


    public void previewClick(Context context, String txt){
        Intent intent = new Intent(context, SearchInvoicePreviewPageActivity.class);
        intent.putExtra("name",txt);
        context.startActivity(intent);

    }


    public void cancelClick(final Context context, final int position){
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure, you want to delete this record?")
                .setPositiveButton("Yes", null)
                .setNegativeButton("Cancel", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size() != 0) {
                    Toast.makeText(context, list.get(position).getsInvName(), Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    LVsInv.invalidateViews();
                }
                else{
                    Toast.makeText(context, "list size 0", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
//                list.remove(1);
//                LVsInv.invalidateViews();
            }
        });
    }


}