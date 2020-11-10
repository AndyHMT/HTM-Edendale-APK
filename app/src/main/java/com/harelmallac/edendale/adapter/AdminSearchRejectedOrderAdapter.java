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
import com.harelmallac.edendale.SearchInvoiceCancelActivity;
import com.harelmallac.edendale.model.AdminSearchRejectedOrderClass;
import com.harelmallac.edendale.model.SearchInvoiceCancelClass;

import java.util.ArrayList;

public class AdminSearchRejectedOrderAdapter extends ArrayAdapter<AdminSearchRejectedOrderClass> {
    private static final String TAG = "searchInvoiceCancelListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<AdminSearchRejectedOrderClass> schRejList;


    public AdminSearchRejectedOrderAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AdminSearchRejectedOrderClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        schRejList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_rejected_order_layout,null, true);

        TextView sRejCustomer = view.findViewById(R.id.schRejCustomer);
        TextView sRejDate = view.findViewById(R.id.schRejDate);
        ImageView sRejView = view.findViewById(R.id.schRejView);



        sRejCustomer.setText(schRejList.get(position).getAdSchRejCustomer());
        sRejDate.setText(schRejList.get(position).getAdSchRejDate());
        sRejView.setImageResource(schRejList.get(position).getAdSchRejView());





        return view;
    }
}
