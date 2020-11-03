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
import com.harelmallac.edendale.model.StockRequestProductClass;
import com.harelmallac.edendale.model.TotalInfoClass;

import java.util.ArrayList;

public class StockRequestProductAdapter extends ArrayAdapter<StockRequestProductClass> {
    private static final String TAG = "StockRequestProductAdapter";
    private Context mContext;
    int mResource;
    ArrayList<StockRequestProductClass> stockProdList;


    public StockRequestProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StockRequestProductClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        stockProdList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_product_list_layout,null, true);


        TextView totalNameView = view.findViewById(R.id.stkProdName);
        EditText totalAmountView = view.findViewById(R.id.stkProdQty);


        totalNameView.setText(stockProdList.get(position).getStockProductName());
        totalAmountView.setText(stockProdList.get(position).getStockProductQty());


        return view;
    }

}
