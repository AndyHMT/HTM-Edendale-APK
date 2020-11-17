package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.MenuActivity;
import com.harelmallac.edendale.R;
import com.harelmallac.edendale.SearchInvoiceActivity;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.SearchInvoiceClass;

import java.util.ArrayList;

public class SearchInvoiceAdapter extends BaseAdapter {
    private Context context;
    private static ArrayList<SearchInvoiceClass> sList;
    DataBaseHelper db = new DataBaseHelper(context);

    LayoutInflater mInflater;
    public SearchInvoiceAdapter(Context context, ArrayList<SearchInvoiceClass> sList){
        this.context = context;
        this.sList  = sList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return sList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder holder;
        convertView=null;
        if (convertView == null) {
            holder = new ViewHolder();
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.search_invoice_list_layout, null);
            holder.invoice = convertView.findViewById(R.id.srchInvName);
            holder.invoice.setTag(position);
            holder.invoice.setText(sList.get(position).getsInvName());

            //Print
            holder.print = convertView.findViewById(R.id.srchInvprint);
            holder.print.setTag(position);
            holder.print.setImageResource(sList.get(position).getsInvprint());

            //Preview
            holder.preview = convertView.findViewById(R.id.srchInvView);
            holder.preview.setTag(position);
            holder.preview.setImageResource(sList.get(position).getsInvView());

            //Cancel
            holder.cancel = convertView.findViewById(R.id.srchInvCancel);
            holder.cancel.setTag(position);
            holder.cancel.setImageResource(sList.get(position).getsInvCancel());



            holder.preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SearchInvoiceActivity x = new SearchInvoiceActivity();
                    x.previewClick(context, sList.get(position).getsInvName());
                }
            });


            holder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SearchInvoiceActivity x = new SearchInvoiceActivity();
                    x.cancelClick(context, position);
                }
            });

            holder.print.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SearchInvoiceActivity x = new SearchInvoiceActivity();
                    x.printSelected(context, sList.get(position).getsInvName());
                }
            });



            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        TextView invoice;
        ImageView print, preview, cancel;
    }
}
