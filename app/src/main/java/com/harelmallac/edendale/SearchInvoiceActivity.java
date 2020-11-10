package com.harelmallac.edendale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.adapter.SearchInvoiceAdapter;
import com.harelmallac.edendale.adapter.SearchInvoiceApater;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.SearchInvoiceClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchInvoiceActivity extends AppCompatActivity {

    private ListView LVsInv;
    private ArrayList<SearchInvoiceClass> list = new ArrayList<>();
    private TextView tvDate;
    DataBaseHelper db = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice);
        db.getAllCurrentDayInvoices();
        tvDate = findViewById(R.id.textView10);
        tvDate.setText(currentDate());

        LVsInv = findViewById(R.id.LVsearchInv);

        //#Varun - Load all current invoices
        getCurrentInvoices();

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



    //#Varun - Get current date
    public String currentDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    //#Varun - Get All invoices
    public void getCurrentInvoices() {
        Cursor res = db.getAllCurrentDayInvoices();
        if(res.getCount() < 0){
            Log.e("Error","Not able to retrieve product data");
        }
        else {
            while (res.moveToNext()) {
                String invoiceNum = res.getString(3);
                String customerName = res.getString(9);
                String statusPost = res.getString(21);
                list.add(new SearchInvoiceClass(customerName+System.lineSeparator()+invoiceNum+System.lineSeparator()+statusPost, R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview, R.drawable.ic_baseline_cancel));
            }
        }
    }

    public void cancelClick(final Context context, final int position) {
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
                if (list.size() != 0) {
                    Toast.makeText(context, list.get(position).getsInvName(), Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    LVsInv.invalidateViews();
                } else {
                    Toast.makeText(context, "list size 0", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
//                list.remove(1);
//                LVsInv.invalidateViews();
            }
        });
    }


}