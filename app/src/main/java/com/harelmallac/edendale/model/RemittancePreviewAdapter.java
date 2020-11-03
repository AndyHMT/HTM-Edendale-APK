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

public class RemittancePreviewAdapter extends ArrayAdapter<RemittancePreviewClass> {
    private static final String TAG = "RemittancePrevAdapter";
    private Context mContext;
    int mResource;
    ArrayList<RemittancePreviewClass> RemittancePrevList;


    public RemittancePreviewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RemittancePreviewClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        RemittancePrevList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remittance_preview_list_layout,null, true);


        TextView NameView = view.findViewById(R.id.RprevNum);
        TextView QuantityView = view.findViewById(R.id.RprevQty);
        TextView AmtView = view.findViewById(R.id.RprevAmt);

        NameView.setText(RemittancePrevList.get(position).getRprevNum());
        QuantityView.setText(RemittancePrevList.get(position).getRprevQty());
        AmtView.setText(RemittancePrevList.get(position).getRprevAmt());

        return view;
    }
}
