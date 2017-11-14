package glm.seclass.qc.edu.grocerylistmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class GroceryItems extends AppCompatActivity {


    ArrayList<Item> itemList = null;
    ItemAdapter adapter;

    ListView lv;
    String searchItem;

    SQLiteDatabase itemDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemList = new ArrayList<Item>();

        lv = (ListView) findViewById(R.id.itemList);

        itemDB = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
        itemDB.execSQL("CREATE TABLE IF NOT EXISTS items (ItemID INTEGER PRIMARY KEY, itemName VARCHAR, itemType VARCHAR, price DOUBLE)");
        itemDB.execSQL("CREATE TABLE IF NOT EXISTS listItems (ListItemID INTEGER PRIMARY KEY, ListID INTEGER, ItemID INTEGER, Quantity INTEGER)");

        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('apple','fruit',1.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('orange','fruit',3.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('pear','fruit',2.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('cucumber','vegetable',.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('lettuce','vegetable',3.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('cabbage','vegetable',5.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('bacon','meat',5.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('prime rib','meat',7.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('ground beef','meat',8.15)");
        itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('chicken wing','meat',9.15)");
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


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Search for item:");
            final EditText input = new EditText(this);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {

                    searchItem = input.getText().toString();
                    Item product = new Item();

                    product.setName(searchItem); //setting name to item object
                    itemList.add(product);

                    ItemAdapter adapter = new ItemAdapter(getApplicationContext(), R.layout.item_row, itemList);

                    lv.setAdapter(adapter);


                    // itemDB.execSQL("INSERT INTO items (itemName, itemType, price) VALUES ('" +newItemName+ "','" +newItemCategory+ "','"+newItemPrice+"')");
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


    public class Item {
        private String name;

        //empty constructor for item
        public Item(){
        }
        //object constructor for item
        public Item(String name){
            this.name = name;
        }
        //set method for name
        public void setName(String name) {
            this.name = name;
        }
        //returns item name
        public String getName() {
            return name;
        }
    }

    private boolean searchItem() {
        boolean match = false;
        String searchedName = "hi";
        Cursor c = itemDB.rawQuery("SELECT * FROM items",null);
        int nameIndex = c.getColumnIndex("itemName");
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
    public class ItemAdapter extends ArrayAdapter {
        private List<Item> itemList;
        private int resource;
        private LayoutInflater inflater;

        public ItemAdapter(Context context, int resource, List<Item> objects) {
            super(context, resource, objects);
            itemList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView itemName;
            CheckBox checkBox;
            EditText quantity;

            itemName = (TextView) convertView.findViewById(R.id.itemName);
            checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            quantity = (EditText) convertView.findViewById(R.id.quantity);

            itemName.setText(searchItem);

            return convertView;
        }
    }

}
