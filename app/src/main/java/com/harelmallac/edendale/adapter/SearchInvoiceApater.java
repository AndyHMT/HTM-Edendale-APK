package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.CreateInvoiceLinesActivity;
import com.harelmallac.edendale.MenuActivity;
import com.harelmallac.edendale.R;
import com.harelmallac.edendale.SearchInvoiceActivity;
import com.harelmallac.edendale.model.SearchInvoiceClass;
import com.harelmallac.edendale.model.TotalInfoClass;

import java.util.ArrayList;

public class SearchInvoiceApater extends ArrayAdapter<SearchInvoiceClass> {
    private static final String TAG = "searchInvoiceListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<SearchInvoiceClass> srchInvList;


    public SearchInvoiceApater(@NonNull Context context, int resource, @NonNull ArrayList<SearchInvoiceClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        srchInvList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_invoice_list_layout,null, true);

        TextView sInvNameView = view.findViewById(R.id.srchInvName);
        ImageView sInvPrint = view.findViewById(R.id.srchInvprint);
        ImageView sInvView = view.findViewById(R.id.srchInvView);
        ImageView sInvCancel = view.findViewById(R.id.srchInvCancel);



        sInvNameView.setText(srchInvList.get(position).getsInvName());
        sInvPrint.setImageResource(srchInvList.get(position).getsInvprint());
        sInvView.setImageResource(srchInvList.get(position).getsInvView());
        sInvCancel.setImageResource(srchInvList.get(position).getsInvCancel());


        return view;
    }
}