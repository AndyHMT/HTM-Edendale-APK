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
import com.harelmallac.edendale.model.DailySalesListClass;

import java.util.ArrayList;

public class DailySalesListAdapter extends ArrayAdapter<DailySalesListClass> {
    private static final String TAG = "DailySalesAdapter";
    private Context mContext;
    int mResource;
    ArrayList<DailySalesListClass> DSList;


    public DailySalesListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DailySalesListClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        DSList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_sales_list_layout,null, true);


        TextView QuantityValue = view.findViewById(R.id.DSQty);
        TextView NameView = view.findViewById(R.id.DSProduct);

        QuantityValue.setText(DSList.get(position).getDSQty());
        NameView.setText(DSList.get(position).getDSProduct());

        return view;
    }
}
