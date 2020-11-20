package com.harelmallac.edendale.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.R;
import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.StockRequestMainClass;

import java.util.ArrayList;

public class StockRequestMainAdapter extends BaseAdapter {
    private Context context;
    private static ArrayList<StockRequestMainClass> sList;

    LayoutInflater mInflater;
    public StockRequestMainAdapter(Context context, ArrayList<StockRequestMainClass> sList){
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
            convertView = mInflater.inflate(R.layout.stock_request_main_list_layout, null);

            //Quantity
            holder.quantity = (EditText) convertView
                    .findViewById(R.id.stkMainProdQty);
            holder.quantity.setTag(position);
            holder.quantity.setText(sList.get(position).getStkprodQty()+"");

            //================================================================
            holder.quantity.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (hasFocus==true)
                    {
                        if (holder.quantity.getText().toString().compareTo("0")==0)
                        {
                            holder.quantity.setText("");
                        }
                    }
                }
            });
            //================================================================

            //Lists of products
            holder.product = (TextView) convertView
                    .findViewById(R.id.stkMainProdName);
            holder.product.setTag(position);
            holder.product.setText(sList.get(position).getStkprodName()+"");

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        int tag_position_qty=(Integer) holder.quantity.getTag();
        holder.quantity.setId(tag_position_qty);

        holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                final int position2 = holder.quantity.getId();
                final EditText Caption = (EditText) holder.quantity;
                if(Caption.getText().toString().length()>0){
                    sList.get(position2).setStkprodQty(Caption.getText().toString());
                }else{
                    //Toast.makeText(context, "Please enter some value", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        return convertView;
    }

    class ViewHolder {
        EditText quantity;
        TextView product;
    }
}
