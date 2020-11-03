package com.harelmallac.edendale.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.R;

import java.util.ArrayList;

public class CurrentStockReportAdapter extends ArrayAdapter<CurrentStockReportClass> {
    private static final String TAG = "CurrentStockAdapter";
    private Context mContext;
    int mResource;
    ArrayList<CurrentStockReportClass> CSRList;


    public CurrentStockReportAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CurrentStockReportClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        CSRList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_sales_list_layout,null, true);


        TextView QuantityValue = view.findViewById(R.id.DSQty);
        TextView NameView = view.findViewById(R.id.DSProduct);

        QuantityValue.setText(CSRList.get(position).getCSRQty());
        NameView.setText(CSRList.get(position).getCSRProduct());

        return view;
    }
}