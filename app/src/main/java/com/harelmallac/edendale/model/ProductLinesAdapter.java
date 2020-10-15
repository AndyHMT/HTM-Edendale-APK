package com.harelmallac.edendale.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.CreateInvoiceLinesActivity;
import com.harelmallac.edendale.R;

import java.util.ArrayList;
import java.util.List;

public class ProductLinesAdapter extends ArrayAdapter<ProductLinesClass> {
    private static final String TAG = "ProductLineListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<ProductLinesClass> ProductLineList;


    public ProductLinesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ProductLinesClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        ProductLineList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_lines_layout,null, true);


        TextView ProNameView = view.findViewById(R.id.ProductName);
        TextView ProQtyView = view.findViewById(R.id.ProductQty);
        TextView ProPriceView = view.findViewById(R.id.ProductPrice);
        TextView ProTotalView = view.findViewById(R.id.ProductTotal);
        Button zx = view.findViewById(R.id.ProductCancel);

        ProNameView.setText(ProductLineList.get(position).getName());
        ProQtyView.setText(ProductLineList.get(position).getQty());
        ProPriceView.setText(ProductLineList.get(position).getPrice());
        ProTotalView.setText(ProductLineList.get(position).getTotal());

        zx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CreateInvoiceLinesActivity.delete(position);
                CreateInvoiceLinesActivity x = new CreateInvoiceLinesActivity();

                x.delete(position);
            }
        });

        return view;
    }
}