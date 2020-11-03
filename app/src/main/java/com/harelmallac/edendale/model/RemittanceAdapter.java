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

public class RemittanceAdapter extends ArrayAdapter<RemittanceClass> {
    private static final String TAG = "RemittanceAdapter";
    private Context mContext;
    int mResource;
    ArrayList<RemittanceClass> RemittanceList;


    public RemittanceAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RemittanceClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        RemittanceList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remittance_list_layout,null, true);


        TextView NameView = view.findViewById(R.id.rmtNum);
        EditText QuantityValue = view.findViewById(R.id.rmtQty);

        NameView.setText(RemittanceList.get(position).getRmtNum());
        QuantityValue.setText(RemittanceList.get(position).getRmtQty());

        return view;
    }
}
