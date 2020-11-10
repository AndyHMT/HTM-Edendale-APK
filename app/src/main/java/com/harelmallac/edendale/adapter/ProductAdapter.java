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

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private static ArrayList<ItemClass> pList;

    LayoutInflater mInflater;
    public ProductAdapter(Context context, ArrayList<ItemClass> pList){
        this.context = context;
        this.pList  = pList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return pList.size();
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
            convertView = mInflater.inflate(R.layout.create_invoice_items_list_view_layout, null);
            holder.discount = (EditText) convertView
                    .findViewById(R.id.DiscountValue);
            holder.discount.setTag(position);
            holder.discount.setText(pList.get(position).getDiscount()+"");
            //================================================================
            holder.discount.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (hasFocus==true)
                    {
                        if (holder.discount.getText().toString().compareTo("0")==0)
                        {
                            holder.discount.setText("");
                        }
                    }
                }
            });
            //================================================================

            //Quantity
            holder.quantity = (EditText) convertView
                    .findViewById(R.id.QuantityValue);
            holder.quantity.setTag(position);
            holder.quantity.setText(pList.get(position).getQuantity()+"");
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
                    .findViewById(R.id.itemName);
            holder.product.setTag(position);
            holder.product.setText(pList.get(position).getName()+"");

            //Total item stock
            holder.total = (TextView) convertView
                    .findViewById(R.id.itemTotal);
            holder.total.setTag(position);
            holder.total.setText(pList.get(position).getTotal()+"");

            Log.e("ID", pList.get(position).getId());


            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        int tag_position=(Integer) holder.discount.getTag();
        holder.discount.setId(tag_position);

        int tag_position_qty=(Integer) holder.quantity.getTag();
        holder.quantity.setId(tag_position_qty);

        holder.discount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                final int position2 = holder.discount.getId();
                final EditText Caption = (EditText) holder.discount;
                if(Caption.getText().toString().length()>0){
                    pList.get(position2).setDiscount(Caption.getText().toString());
                }else{
                    Toast.makeText(context, "Please enter some value", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        holder.quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                final int position2 = holder.quantity.getId();
                final EditText Caption = (EditText) holder.quantity;
                if(Caption.getText().toString().length()>0){
                    pList.get(position2).setQuantity(Caption.getText().toString());
                }else{
                    Toast.makeText(context, "Please enter some value", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        return convertView;
    }

    class ViewHolder {
        EditText discount, quantity;
        TextView product, total;
    }
}
