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
import com.harelmallac.edendale.model.AdSalesReportClass;
import com.harelmallac.edendale.model.AdSearchOrderClass;

import java.util.ArrayList;

public class AdSearchOrderAdapter extends ArrayAdapter<AdSearchOrderClass> {
    private static final String TAG = "SordAdapter";
    private Context mContext;
    int mResource;
    ArrayList<AdSearchOrderClass> SordList;


    public AdSearchOrderAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AdSearchOrderClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        SordList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_search_order_list_layout, null, true);


        TextView QuantityValue = view.findViewById(R.id.SordCustName);
        TextView NameView = view.findViewById(R.id.SordAddress);

        QuantityValue.setText(SordList.get(position).getSordCustName());
        NameView.setText(SordList.get(position).getSordAddress());

        return view;
    }
}

