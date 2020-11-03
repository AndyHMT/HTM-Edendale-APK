
package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.harelmallac.edendale.adapter.ProductAdapter;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.ItemListAdapter;
import com.harelmallac.edendale.model.UserModel;

import java.util.ArrayList;

import static java.sql.Types.NULL;

public class CreateInvoiceListOfItemsActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ProductAdapter adapter;
    DataBaseHelper db = new DataBaseHelper(this);
    ListView LVitems;
    ArrayList<ItemClass> list = new ArrayList<>();
    ArrayList<String> aPid = new ArrayList<>();
    ArrayList<String> aProductName = new ArrayList<>();
    ArrayList<String> aProductTotal = new ArrayList<>();
    ArrayList<String> aProductDiscount = new ArrayList<>();
    ArrayList<String> aProductQty = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice_list_of_items);
        LVitems = findViewById(R.id.LVitems);

        //#Varun - Load all products on lists view
        populateItemLists();

        adapter = new ProductAdapter(this, list);
        LVitems.setAdapter(adapter);


        Button butConfirm = findViewById(R.id.butConfirm);
        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < list.size(); i++){
                    String discount = list.get(i).getDiscount();
                    String qty = list.get(i).getQuantity();
                    if ((discount != "0") && (qty != "0")) {
                        Log.e("selected values ", "Id : "+list.get(i).getId()+"Product: "+list.get(i).getName()+"Discount: "+discount+"Quantity: "+qty);
                        aPid.add(list.get(i).getId());
                        aProductName.add(list.get(i).getName());
                        aProductTotal.add(list.get(i).getTotal());
                        aProductDiscount.add(list.get(i).getDiscount());
                        aProductQty.add(list.get(i).getQuantity());
                    }

                }
                Intent intent = new Intent(CreateInvoiceListOfItemsActivity.this, CreateInvoiceLinesActivity.class);
                intent.putExtra("productId", aPid);
                intent.putExtra("productName", aProductName);
                intent.putExtra("productTotal", aProductTotal);
                intent.putExtra("productDiscount", aProductDiscount);
                intent.putExtra("productQuantity", aProductQty);
                startActivity(intent);
//                EditText Discount;
//                EditText Quantity;
//                Boolean check = false;


//                View z;
//                int chCount = -1;
//                int childCount = LVitems.getChildCount();
//                Toast.makeText(getApplicationContext(),String.valueOf(childCount),Toast.LENGTH_SHORT).show();
//                for (int i = (count-5); i < (count - 5 + childCount); i++)
//                {
//                    chCount += 1;
//                    v = LVitems.getChildAt(chCount);
//                    Discount = v.findViewById(R.id.DiscountValue);
//                    Quantity = v.findViewById(R.id.QuantityValue);
//
//                    if (Discount.getText().toString().equals("")) {
//
//                    } else if (Discount.getText().toString().equals("0")) {
//                        Toast.makeText(getApplicationContext(), "Discount Amount cannot be 0", Toast.LENGTH_SHORT).show();
//                        check = false;
//                        break;
//                    } else {
//                        if (Quantity.getText().toString().equals("")) {
//                            Toast.makeText(getApplicationContext(), "Both Discount and Quantity must be added", Toast.LENGTH_SHORT).show();
//                            check = false;
//                            break;
//                        } else if (Quantity.getText().toString().equals("0")) {
//                            Toast.makeText(getApplicationContext(), "Quantity cannot be 0", Toast.LENGTH_SHORT).show();
//                            check = false;
//                            break;
//                        } else {
//                            ItemList.get(i).setDiscount(Discount.getText().toString());
//                            ItemList.get(i).setQuantity(Quantity.getText().toString());
//                            check = true;
//                        }
//
//                    }
//                    if(check==true) {
//                        Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });
//        //======================================================================================
//        //======================================================================================
//
//        Button butFinish = findViewById(R.id.butFinish);
//
//        butFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ArrayList<String> selectedItemName = new ArrayList<>();
//                ArrayList<String> selectedItemTOtal = new ArrayList<>();
//                ArrayList<String> selectedItemDiscount = new ArrayList<>();
//                ArrayList<String> selectedItemQuantity = new ArrayList<>();
//
//                for (int j = 0; j < ItemList.size()-1; j++) {
////                    String Name = ItemList.get(j).getName();
////                    String Total = ItemList.get(j).getTotal();
//                    String Dis = ItemList.get(j).getDiscount();
//                    String Quan = ItemList.get(j).getQuantity();
//                    if (Dis.equals("")) {
//
//                    } else {
//                        if (Quan.equals("")) {
//
//                        }
//                        else {
//                            selectedItemName.add(ItemList.get(j).getName());
//                            selectedItemTOtal.add(ItemList.get(j).getTotal());
//                            selectedItemDiscount.add(ItemList.get(j).getDiscount());
//                            selectedItemQuantity.add(ItemList.get(j).getQuantity());
//                        }
//                    }
//                }
//
//
//                Intent intent = new Intent(CreateInvoiceListOfItemsActivity.this, CreateInvoiceLinesActivity.class);
//                intent.putExtra("listName", selectedItemName);
//                intent.putExtra("listTotal", selectedItemTOtal);
//                intent.putExtra("listDiscount", selectedItemDiscount);
//                intent.putExtra("listQuantity", selectedItemQuantity);
//                startActivity(intent);
//            }
//        });
//
//   //======================================================================================

    }

    //#Varun - populate products to lists view
    public void populateItemLists() {
        Cursor res = db.getInvoice();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve product data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                String productName = res.getString(7);
                String productId = db.getProductId(productName);
                String productQuantity = res.getString(1);
                list.add(new ItemClass(productId, productName, productQuantity, "0","0"));
            }

        }
    }
}