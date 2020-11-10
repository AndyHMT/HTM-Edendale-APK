package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.R;
import com.harelmallac.edendale.model.AdCreateOrderLinesClass;
import com.harelmallac.edendale.model.DailySalesListClass;

import java.util.ArrayList;

public class AdCreateOrderLinesAdapter extends ArrayAdapter<AdCreateOrderLinesClass> {
    private static final String TAG = "AdCreateOderLinesAdapter";
    private Context mContext;
    int mResource;
    ArrayList<AdCreateOrderLinesClass> AdCordList;


    public AdCreateOrderLinesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AdCreateOrderLinesClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        AdCordList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_create_order_lines_layout,null, true);


        TextView NameView = view.findViewById(R.id.CordProductName);
        TextView QuantityValue = view.findViewById(R.id.CordQty);
        TextView PriceView = view.findViewById(R.id.CordPrice);
        TextView TotalView = view.findViewById(R.id.CordTotal);

        NameView.setText(AdCordList.get(position).getCOrdProdName());
        QuantityValue.setText(AdCordList.get(position).getCordQty());
        PriceView.setText(AdCordList.get(position).getCordPrice());
        TotalView.setText(AdCordList.get(position).getCordTotal());

        return view;
    }
}
