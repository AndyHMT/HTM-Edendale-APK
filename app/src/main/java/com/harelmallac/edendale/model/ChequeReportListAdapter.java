package com.harelmallac.edendale.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.harelmallac.edendale.R;

import java.util.ArrayList;

public class ChequeReportListAdapter extends ArrayAdapter<ChequeReportClass> {
    private static final String TAG = "ChequeReportAdapter";
    private Context mContext;
    int mResource;
    ArrayList<ChequeReportClass> CRList;


    public ChequeReportListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ChequeReportClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        CRList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cheque_report_layout,null, true);


        TextView customer = view.findViewById(R.id.CNAME);
        TextView amt = view.findViewById(R.id.AMT);
        TextView invoice = view.findViewById(R.id.INV);
        TextView receipt = view.findViewById(R.id.REP);

        customer.setText(CRList.get(position).getName());
        amt.setText(CRList.get(position).getAmount());
        invoice.setText(CRList.get(position).getInvoice());
        receipt.setText(CRList.get(position).getReceipt());

        return view;
    }
}