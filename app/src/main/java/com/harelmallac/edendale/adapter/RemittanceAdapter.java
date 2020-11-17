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
import com.harelmallac.edendale.model.RemittanceClass;

import java.util.ArrayList;

public class RemittanceAdapter extends BaseAdapter {
    private Context context;
    private static ArrayList<RemittanceClass> rList;

    LayoutInflater mInflater;
    public RemittanceAdapter(Context context, ArrayList<RemittanceClass> rList){
        this.context = context;
        this.rList  = rList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return rList.size();
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
            convertView = mInflater.inflate(R.layout.remittance_list_layout, null);
            holder.price = (TextView) convertView
                    .findViewById(R.id.rmtNum);
            holder.price.setTag(position);
            holder.price.setText(rList.get(position).getRmtNum()+"");

            //amount
            holder.amount = (EditText) convertView
                    .findViewById(R.id.rmtQty);
            holder.amount.setTag(position);
            holder.amount.setText(rList.get(position).getRmtQty()+"");
            //================================================================
            holder.amount.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (hasFocus==true)
                    {
                        if (holder.amount.getText().toString().compareTo("0")==0)
                        {
                            holder.amount.setText("");
                        }
                    }
                }
            });
            //================================================================

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        int tag_position_qty=(Integer) holder.amount.getTag();
        holder.amount.setId(tag_position_qty);

        holder.amount.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                final int position2 = holder.amount.getId();
                final EditText Caption = (EditText) holder.amount;
                if(Caption.getText().toString().length()>0){
                    rList.get(position2).setRmtQty(Caption.getText().toString());
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
        EditText amount;
        TextView price;
    }
}
