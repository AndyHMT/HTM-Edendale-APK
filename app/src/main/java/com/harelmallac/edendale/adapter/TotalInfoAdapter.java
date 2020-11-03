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
import com.harelmallac.edendale.model.TotalInfoClass;

import java.util.ArrayList;

public class TotalInfoAdapter extends ArrayAdapter<TotalInfoClass> {
    private static final String TAG = "TotalListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<TotalInfoClass> totalList;


    public TotalInfoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TotalInfoClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        totalList = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_info_layout,null, true);


        TextView totalNameView = view.findViewById(R.id.TotalName);
        TextView totalAmountView = view.findViewById(R.id.Totalamount);


        totalNameView.setText(totalList.get(position).getName());
        totalAmountView.setText(totalList.get(position).getNum());


        return view;
    }
}