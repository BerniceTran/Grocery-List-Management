package glm.seclass.qc.edu.grocerylistmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;



public class GroceryItems extends AppCompatActivity {


    ArrayList<String> itemList = null;

    ListView itemListView;


    SQLiteDatabase itemDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



      //  itemAdapter =new CustomArrayAdapter(this, itemList);
        itemList = new ArrayList<String>();
        itemListView = (ListView) findViewById(R.id.itemList);

        itemDB = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
        itemDB.execSQL("CREATE TABLE IF NOT EXISTS items (ItemID INTEGER PRIMARY KEY, itemName VARCHAR, itemType VARCHAR)");
        itemDB.execSQL("CREATE TABLE IF NOT EXISTS listItems (ListItemID INTEGER PRIMARY KEY, ListID INTEGER, ItemID INTEGER, Quantity INTEGER)");

        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('apple','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('orange','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pear','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cucumber','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lettuce','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cabbage','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bacon','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('prime rib','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ground beef','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chicken wing','meat')");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grocery_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.createItem){


            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Search for item:");
            final EditText input = new EditText(this);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String in = input.getText().toString();

                    if(searchItem(in) == true) {
                        itemList.add(in);
                        ListAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);

                        itemListView.setAdapter(itemAdapter);
                    }
                    else if(searchItem(in) == false) {
                        //creates dialog for the new item being added to the database
                        builder.setTitle("Item not found. Input Type.");
                        builder.setView(input);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String newItemName = in;
                                String newItemCategory = input.getText().toString();
                                itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('" + newItemName + "','" + newItemCategory + "')");

                                //Adding the new item to the list
                                itemList.add(newItemName);
                                ListAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);

                                itemListView.setAdapter(itemAdapter);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();
                            }
                        }); //end type dialog
                        builder.show();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*
    private boolean searchItem(String name) {
        boolean match = false;
        String searchedName = name;
        Cursor c = itemDB.rawQuery("SELECT * FROM items",null);
        int nameIndex = c.getColumnIndex("itemName");
        int counter = 0;
        c.moveToFirst();

        while(c != null) {
            String comparisonName = c.getString(nameIndex);
            if(searchedName.equals(comparisonName)) {
                return true;
            }
            else
                c.moveToNext();
        }
        return false;
    }
*/
    private boolean searchItem(String input) {
        boolean match = false;
        Cursor c = itemDB.rawQuery("SELECT * FROM items",null);
        int nameIndex = c.getColumnIndex("itemName");
        c.moveToFirst();
        do {
            String comparisonName = c.getString(nameIndex);
            if(input.equals(comparisonName)) {
                Log.i("Searchtest","foundit");
                return true;
            }
        }
        while(c.moveToNext());
        Log.i("Searchtest","DidNotFindit");
        c.close();
        return false;
    }

}
