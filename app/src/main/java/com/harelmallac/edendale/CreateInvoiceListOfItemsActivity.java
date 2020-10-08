package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.ItemListAdapter;

import java.util.ArrayList;

public class CreateInvoiceListOfItemsActivity extends AppCompatActivity {

    //private Object ItemListAdapter;
    private static final String TAG = "MainActivity";
    //private Object ItemListAdapter;
    int count = 5;
    Context context = this;
    ListView LVitems;
    ArrayList<ItemClass> ItemList = new ArrayList<>();
    ArrayList<ItemClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice_list_of_items);
        Log.d(TAG, "onCreate: Started");
        LVitems = findViewById(R.id.LVitems);

        for (int i = 1; i < 20; i++) {
            ItemList.add(new ItemClass("product " + i, "", ""));
        }

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
//                Toast.makeText(getApplicationContext(),String.valueOf(LVitems.getChildCount()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),String.valueOf(LVitems.getLastVisiblePosition()),Toast.LENGTH_SHORT).show();

//                View z;
                int chCount = -1;
                int childCount = LVitems.getChildCount();
//                Toast.makeText(getApplicationContext(),String.valueOf(childCount),Toast.LENGTH_SHORT).show();
                for (int i = (count-5); i < (count - 5 + childCount); i++)
                {
                    chCount += 1;
                    v = LVitems.getChildAt(chCount);
                    Discount = v.findViewById(R.id.DiscountValue);
//                    Toast.makeText(getApplicationContext(), Discount.getText().toString(), Toast.LENGTH_SHORT).show();
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
//                        Toast.makeText(getApplicationContext(), ItemList.get(i).getDiscount(), Toast.LENGTH_SHORT).show();
//                        Log.e("output",ItemList.get(i).getDiscount());
                        Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();
                    }
                }



//                ArrayList<String> selectedItemName = new ArrayList<>();
//                selectedItemName.add("sad potato");
//                if (check == true) {
//
//
//                    for (int j = 0; j < 5; j++) {
//                        String Dis = ItemList.get(j).getDiscount();
//                        String Quan = ItemList.get(j).getQuantity();
//                        if (Dis.equals("")) {
//
//                        } else {
//                            if (Quan.equals("")) {
//
//                            } else {
//                                selectedItemName.add(ItemList.get(j).getName());
//                            }
//                        }
//                    }
//                }

//                        Intent intent = new Intent(CreateInvoiceListOfItemsActivity.this, CreateInvoiceLinesActivity.class);
//                        intent.putExtra("list", selectedItemName);
//                        startActivity(intent);

//                  ItemList.get(i).setDiscount(Discount.getText().toString());
//                    Toast.makeText(getApplicationContext(),selectedItemName.get(0), Toast.LENGTH_SHORT).show();

//
//
            }
        });
    //======================================================================================


    }




}