package com.harelmallac.edendale.model;

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

import java.util.ArrayList;
import java.util.List;

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        String name = getItem(position).getName();
//        String discount = getItem(position).getDiscount();
//        String quantity = getItem(position).getQuantity();
//
//        ItemClass IC = new ItemClass(name, discount,quantity);
//
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        convertView = inflater.inflate(mResource, parent, false);
//
//        TextView NameView = (TextView) convertView.findViewById(R.id.itemName);
//        EditText DiscountValue = (EditText) convertView.findViewById(R.id.DiscountValue);
//        EditText QuantityValue = (EditText) convertView.findViewById(R.id.QuantityValue);
//
//        NameView.setText(name);
//        DiscountValue.setText(discount);
//        QuantityValue.setText(quantity);
//
//        return convertView;
//    }

public class ItemListAdapter extends ArrayAdapter<ItemClass> {
    private static final String TAG = "ItemListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<ItemClass> ItemList;


    public ItemListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        ItemList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_invoice_items_list_view_layout,null, true);


        TextView NameView = view.findViewById(R.id.itemName);
        TextView TotalView = view.findViewById(R.id.itemTotal);
        EditText DiscountValue = view.findViewById(R.id.DiscountValue);
        EditText QuantityValue = view.findViewById(R.id.QuantityValue);

        NameView.setText(ItemList.get(position).getName());
        TotalView.setText(ItemList.get(position).getTotal());
        DiscountValue.setText(ItemList.get(position).getDiscount());
        QuantityValue.setText(ItemList.get(position).getQuantity());

        return view;
    }
}
