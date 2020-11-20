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
import com.harelmallac.edendale.model.ProductLinesClass;

import java.util.ArrayList;

public class SelectedProductAdapter extends BaseAdapter {
    private Context context;
    private static ArrayList<ProductLinesClass> pList;
    Button btnRemove;

    LayoutInflater mInflater;
    public SelectedProductAdapter(Context context, ArrayList<ProductLinesClass> pList){
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
            convertView = mInflater.inflate(R.layout.list_view_lines_layout, null);

            Button btnRemove = (Button) convertView.findViewById(R.id.Productdel);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CreateInvoiceLinesActivity x = new CreateInvoiceLinesActivity();
                    x.delete(position);
                    //TextView t1 = (TextView) view.findViewById(R.id.ExclVatView);
                    Log.e("Clicked", "He121");
                }
            });

            //Lists of products
            holder.product = (TextView) convertView
                    .findViewById(R.id.pName);
            holder.product.setTag(position);
            holder.product.setText(pList.get(position).getName()+"");

            //Selected Qty
            holder.qty = (TextView) convertView
                    .findViewById(R.id.pQty);
            holder.qty.setTag(position);
            holder.qty.setText(pList.get(position).getQty()+"");

            //Selected Price
            holder.price = (TextView) convertView
                    .findViewById(R.id.ProductPrice);
            holder.price.setTag(position);
            holder.price.setText(pList.get(position).getPrice()+"");

            //Total item stock
            holder.total = (TextView) convertView
                    .findViewById(R.id.ProductTotal);
            holder.total.setTag(position);
            holder.total.setText(pList.get(position).getTotal()+"");

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();

        }

        return convertView;


    }

    class ViewHolder {
        TextView product, total, price, qty;
    }


}
