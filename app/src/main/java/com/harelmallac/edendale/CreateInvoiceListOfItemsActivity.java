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

import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.ItemListAdapter;
import com.harelmallac.edendale.model.UserModel;

import java.util.ArrayList;

import static java.sql.Types.NULL;

public class CreateInvoiceListOfItemsActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int count = 5;
    DataBaseHelper db = new DataBaseHelper(this);
    Context context = this;
    ListView LVitems;
    ArrayList<ItemClass> ItemList = new ArrayList<>();
    ArrayList<ItemClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice_list_of_items);
        LVitems = findViewById(R.id.LVitems);

        //#Varun - Load all products on lists view
        populateItemLists();

        for (int i = (count-5); i < count; i++) {
            list.add(ItemList.get(i));
        }

        ItemListAdapter adapter = new ItemListAdapter(this, R.layout.create_invoice_items_list_view_layout, list);
            LVitems.setAdapter(adapter);




        //======================================================================================
        //MOVE NEXT ONE
        //======================================================================================
        Button moveOne = findViewById(R.id.butMoveNextOne);

        moveOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ItemList.size() - count >0) {
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.remove(ItemList.get(i));
                        }
                        catch (Exception e){
                            break;
                        }
                    }
                    count += 5;
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.add(ItemList.get(i));
                        } catch (Exception e) {
                            break;
                        }
                    }

                    ItemListAdapter adapter = new ItemListAdapter(context, R.layout.create_invoice_items_list_view_layout, list);
                    LVitems.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(),"You reached the end of the list", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //======================================================================================
        //MOVE BACK
        //======================================================================================
        Button moveBackOne = findViewById(R.id.butMoveBackOne);

        moveBackOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ItemList.size() + count >ItemList.size() + 5) {
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.remove(ItemList.get(i));
                        }
                        catch (Exception e){
                            break;
                        }
                    }
                    count -= 5;
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.add(ItemList.get(i));
                        } catch (Exception e) {
                            break;
                        }
                    }

                    ItemListAdapter adapter = new ItemListAdapter(context, R.layout.create_invoice_items_list_view_layout, list);
                    LVitems.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(),"You reached the end of the list", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //======================================================================================
        //MOVE NEXT TEN
        //======================================================================================
        Button moveNextTen = findViewById(R.id.butMoveNextTen);

        moveNextTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ItemList.size() - count -5 >0) {
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.remove(ItemList.get(i));
                        }
                        catch (Exception e){
                            break;
                        }
                    }
                    count += 10;
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.add(ItemList.get(i));
                        } catch (Exception e) {
                            break;
                        }
                    }

                    ItemListAdapter adapter = new ItemListAdapter(context, R.layout.create_invoice_items_list_view_layout, list);
                    LVitems.setAdapter(adapter);
                }
                else{
                    if(ItemList.size() - count >0) {
                        for (int i = (count - 5); i < count; i++) {
                            try {
                                list.remove(ItemList.get(i));
                            }
                            catch (Exception e){
                                break;
                            }
                        }
                        count += 5;
                        for (int i = (count - 5); i < count; i++) {
                            try {
                                list.add(ItemList.get(i));
                            } catch (Exception e) {
                                break;
                            }
                        }

                        ItemListAdapter adapter = new ItemListAdapter(context, R.layout.create_invoice_items_list_view_layout, list);
                        LVitems.setAdapter(adapter);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"You reached the end of the list", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        //======================================================================================
        //MOVE BACK TEN
        //======================================================================================
        Button moveBackTen = findViewById(R.id.butMoveBackTen);

        moveBackTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ItemList.size() + count +5 >ItemList.size() + 15) {
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.remove(ItemList.get(i));
                        }
                        catch (Exception e){
                            break;
                        }
                    }
                    count -= 10;
                    for (int i = (count - 5); i < count; i++) {
                        try {
                            list.add(ItemList.get(i));
                        } catch (Exception e) {
                            break;
                        }
                    }

                    ItemListAdapter adapter = new ItemListAdapter(context, R.layout.create_invoice_items_list_view_layout, list);
                    LVitems.setAdapter(adapter);
                }
                else {
                    if (ItemList.size() + count > ItemList.size() + 5) {
                        for (int i = (count - 5); i < count; i++) {
                            try {
                                list.remove(ItemList.get(i));
                            } catch (Exception e) {
                                break;
                            }
                        }
                        count -= 5;
                        for (int i = (count - 5); i < count; i++) {
                            try {
                                list.add(ItemList.get(i));
                            } catch (Exception e) {
                                break;
                            }
                        }

                        ItemListAdapter adapter = new ItemListAdapter(context, R.layout.create_invoice_items_list_view_layout, list);
                        LVitems.setAdapter(adapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "You reached the end of the list", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //======================================================================================

        Button butConfirm = findViewById(R.id.butConfirm);

        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Discount;
                EditText Quantity;
                Boolean check = false;


//                View z;
                int chCount = -1;
                int childCount = LVitems.getChildCount();
//                Toast.makeText(getApplicationContext(),String.valueOf(childCount),Toast.LENGTH_SHORT).show();
                for (int i = (count-5); i < (count - 5 + childCount); i++)
                {
                    chCount += 1;
                    v = LVitems.getChildAt(chCount);
                    Discount = v.findViewById(R.id.DiscountValue);
                    Quantity = v.findViewById(R.id.QuantityValue);

                    if (Discount.getText().toString().equals("")) {

                    } else if (Discount.getText().toString().equals("0")) {
                        Toast.makeText(getApplicationContext(), "Discount Amount cannot be 0", Toast.LENGTH_SHORT).show();
                        check = false;
                        break;
                    } else {
                        if (Quantity.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "Both Discount and Quantity must be added", Toast.LENGTH_SHORT).show();
                            check = false;
                            break;
                        } else if (Quantity.getText().toString().equals("0")) {
                            Toast.makeText(getApplicationContext(), "Quantity cannot be 0", Toast.LENGTH_SHORT).show();
                            check = false;
                            break;
                        } else {
                            ItemList.get(i).setDiscount(Discount.getText().toString());
                            ItemList.get(i).setQuantity(Quantity.getText().toString());
                            check = true;
                        }

                    }
                    if(check==true) {
                        Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //======================================================================================
        //======================================================================================

        Button butFinish = findViewById(R.id.butFinish);

        butFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> selectedItemName = new ArrayList<>();
                ArrayList<String> selectedItemTOtal = new ArrayList<>();
                ArrayList<String> selectedItemDiscount = new ArrayList<>();
                ArrayList<String> selectedItemQuantity = new ArrayList<>();

                for (int j = 0; j < ItemList.size()-1; j++) {
//                    String Name = ItemList.get(j).getName();
//                    String Total = ItemList.get(j).getTotal();
                    String Dis = ItemList.get(j).getDiscount();
                    String Quan = ItemList.get(j).getQuantity();
                    if (Dis.equals("")) {

                    } else {
                        if (Quan.equals("")) {

                        }
                        else {
                            selectedItemName.add(ItemList.get(j).getName());
                            selectedItemTOtal.add(ItemList.get(j).getTotal());
                            selectedItemDiscount.add(ItemList.get(j).getDiscount());
                            selectedItemQuantity.add(ItemList.get(j).getQuantity());
                        }
                    }
                }


                Intent intent = new Intent(CreateInvoiceListOfItemsActivity.this, CreateInvoiceLinesActivity.class);
                intent.putExtra("listName", selectedItemName);
                intent.putExtra("listTotal", selectedItemTOtal);
                intent.putExtra("listDiscount", selectedItemDiscount);
                intent.putExtra("listQuantity", selectedItemQuantity);
                startActivity(intent);
            }
        });

   //======================================================================================

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
                String productQuantity = res.getString(1);
                ItemList.add(new ItemClass(productName, productQuantity, "",""));
                Log.e("Double",productName);
            }

        }
    }
}