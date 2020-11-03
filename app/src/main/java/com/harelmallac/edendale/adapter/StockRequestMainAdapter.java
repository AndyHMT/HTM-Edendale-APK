package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.R;
import com.harelmallac.edendale.model.StockRequestMainClass;
import com.harelmallac.edendale.model.StockRequestProductClass;

import java.util.ArrayList;

public class StockRequestMainAdapter extends ArrayAdapter<StockRequestMainClass> {
    private static final String TAG = "StockRequestProductAdapter";
    private Context mContext;
    int mResource;
    ArrayList<StockRequestMainClass> stockmainProdList;


    public StockRequestMainAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StockRequestMainClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        stockmainProdList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_request_main_list_layout,null, true);


        TextView totalNameView = view.findViewById(R.id.stkMainProdName);
        TextView totalAmountView = view.findViewById(R.id.stkMainProdQty);


        totalNameView.setText(stockmainProdList.get(position).getStkprodName());
        totalAmountView.setText(stockmainProdList.get(position).getStkprodQty());


        return view;
    }

}