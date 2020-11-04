package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.CreateInvoiceLinesActivity;
import com.harelmallac.edendale.R;
import com.harelmallac.edendale.model.ProductLinesClass;
import com.harelmallac.edendale.model.SearchInvPrevPgClass;

import java.util.ArrayList;

public class SearchInvPrevPgAdapter  extends ArrayAdapter<SearchInvPrevPgClass> {
    private static final String TAG = "PrevPgAdapter";
    private Context mContext;
    int mResource;
    ArrayList<SearchInvPrevPgClass> PrevPgList;


    public SearchInvPrevPgAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SearchInvPrevPgClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        PrevPgList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_invoice_preview_page_layout,null, true);


        TextView PrevPgName = view.findViewById(R.id.PrevPgName);
        TextView PrevPgQty = view.findViewById(R.id.PrevPgQty);
        TextView PrevPgPrice = view.findViewById(R.id.PrevPgPrice);
        TextView PrevPgTotal = view.findViewById(R.id.PrevPgTotal);



        PrevPgName.setText(PrevPgList.get(position).getPrevPgName());
        PrevPgQty.setText(PrevPgList.get(position).getPrevPgQty());
        PrevPgPrice.setText(PrevPgList.get(position).getPrevPgPrice());
        PrevPgTotal.setText(PrevPgList.get(position).getPrevPgTotal());


        return view;
    }
}