package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.R;
import com.harelmallac.edendale.SearchInvoiceActivity;
import com.harelmallac.edendale.SearchInvoiceCancelActivity;
import com.harelmallac.edendale.model.SearchInvoiceCancelClass;


import java.util.ArrayList;

public class SearchInvoiceCancelAdapter extends ArrayAdapter<SearchInvoiceCancelClass> {
    private static final String TAG = "searchInvoiceCancelListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<SearchInvoiceCancelClass> srchInCanList;


    public SearchInvoiceCancelAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SearchInvoiceCancelClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        srchInCanList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_invoice_cancel_layout,null, true);

        TextView sInCanNameView = view.findViewById(R.id.srchInCanName);
        ImageView sInCanPrint = view.findViewById(R.id.srchInCanPrint);
        ImageView sInCanView = view.findViewById(R.id.srchInCanView);



        sInCanNameView.setText(srchInCanList.get(position).getsInCanName());
        sInCanPrint.setImageResource(srchInCanList.get(position).getsInCanvprint());
        sInCanView.setImageResource(srchInCanList.get(position).getsInCanView());


        sInCanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchInvoiceCancelActivity x = new SearchInvoiceCancelActivity();
                x.previewClick(mContext, srchInCanList.get(position).getsInCanName());
            }
        });



        return view;
    }
}