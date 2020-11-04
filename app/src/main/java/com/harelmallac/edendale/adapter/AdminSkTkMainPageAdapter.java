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
import com.harelmallac.edendale.model.AdminSkTkMainPageClass;
import com.harelmallac.edendale.model.SearchInvoiceClass;

import java.util.ArrayList;

public class AdminSkTkMainPageAdapter extends ArrayAdapter<AdminSkTkMainPageClass> {
    private static final String TAG = "searchInvoiceListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<AdminSkTkMainPageClass> SkTkList;


    public AdminSkTkMainPageAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AdminSkTkMainPageClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        SkTkList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_stock_take_main_page_list_layout,null, true);

        TextView prodName = view.findViewById(R.id.adSkTkProd);
        TextView QtyStore = view.findViewById(R.id.adSkTkQty);



        prodName.setText(SkTkList.get(position).getProductName());
        QtyStore.setText(SkTkList.get(position).getQtystore());



        return view;
    }
}