package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.harelmallac.edendale.CreateInvoiceLinesActivity;
import com.harelmallac.edendale.R;
import com.harelmallac.edendale.StockRequestListActivity;
import com.harelmallac.edendale.model.StockRequestProductClass;

import java.util.ArrayList;

public class StockRequestProductAdapter extends BaseAdapter {
    private Context context;
    private static ArrayList<StockRequestProductClass> stkList;

    LayoutInflater mInflater;
    public StockRequestProductAdapter(Context context, ArrayList<StockRequestProductClass> stkList){
        this.context = context;
        this.stkList  = stkList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return stkList.size();
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
            convertView = mInflater.inflate(R.layout.stock_request_view_lines_layout, null);

            //Quantity
            holder.quantity = (TextView) convertView
                    .findViewById(R.id.pQty);
            holder.quantity.setTag(position);
            holder.quantity.setText(stkList.get(position).getStockProductQty()+"");

            //Lists of products
            holder.product = (TextView) convertView
                    .findViewById(R.id.pName);
            holder.product.setTag(position);
            holder.product.setText(stkList.get(position).getStockProductName()+"");

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        int tag_position_qty=(Integer) holder.quantity.getTag();
        holder.quantity.setId(tag_position_qty);

        Button btnRemove = (Button) convertView.findViewById(R.id.Productdel);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StockRequestListActivity x = new StockRequestListActivity();
                x.delete(position);
            }
        });


        return convertView;
    }

    class ViewHolder {
        TextView product, quantity;
    }
}
