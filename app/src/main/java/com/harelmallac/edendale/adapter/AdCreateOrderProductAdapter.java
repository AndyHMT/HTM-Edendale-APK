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
import com.harelmallac.edendale.model.AdCreateOrderLinesClass;
import com.harelmallac.edendale.model.AdCreateOrderProductClass;

import java.util.ArrayList;

public class AdCreateOrderProductAdapter extends ArrayAdapter<AdCreateOrderProductClass> {
    private static final String TAG = "AdCreateOderLinesProductAdapter";
    private Context mContext;
    int mResource;
    ArrayList<AdCreateOrderProductClass> AdCordPList;


    public AdCreateOrderProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AdCreateOrderProductClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        AdCordPList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_create_order_lines_product_layout,null, true);


        TextView NameView = view.findViewById(R.id.CordProdname);
        TextView Quantitystore = view.findViewById(R.id.CordQtyStore);
        EditText QtyView = view.findViewById(R.id.CordproQty);


        NameView.setText(AdCordPList.get(position).getCordProdct());
        Quantitystore.setText(AdCordPList.get(position).getCordQtyStore());
        QtyView.setText(AdCordPList.get(position).getCordproQty());


        return view;
    }
}
