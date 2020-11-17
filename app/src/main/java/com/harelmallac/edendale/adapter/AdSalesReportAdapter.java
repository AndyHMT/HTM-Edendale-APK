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
import com.harelmallac.edendale.model.AdSalesReportClass;

import java.util.ArrayList;

public class AdSalesReportAdapter extends ArrayAdapter<AdSalesReportClass> {
    private static final String TAG = "ChequeReportAdapter";
    private Context mContext;
    int mResource;
    ArrayList<AdSalesReportClass> SRepList;


    public AdSalesReportAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AdSalesReportClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        SRepList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_sales_list_layout, null, true);


        TextView QuantityValue = view.findViewById(R.id.CNAME);
        TextView NameView = view.findViewById(R.id.AMT);

        QuantityValue.setText(SRepList.get(position).getSRepQty());
        NameView.setText(SRepList.get(position).getSRepProduct());

        return view;
    }
}
