package glm.seclass.qc.edu.grocerylistmanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;
import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;
import android.view.ContextMenu;
import android.view.View.OnLongClickListener;

public class GroceryItems extends AppCompatActivity {

    private  ArrayList<Item> itemList = new ArrayList<Item>();
    private ArrayList<String> quantityList = new ArrayList<String>();
    private ArrayAdapter<Item> itemAdapter = null;
    ListView itemListView;

    SQLiteDatabase itemDB;

    String currentList;
    Integer currentListID;
    int defualtQuantity = 1;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Tests retrieval of list name
        Log.i("ListClicked", getIntent().getStringExtra("ListName"));
        currentList = getIntent().getStringExtra("ListName");

        //Tests retrieval of list position
        Log.i("PositionClicked", String.valueOf(getIntent().getIntExtra("ListID", 0)));
        currentListID = getIntent().getIntExtra("ListID", 0);

        itemDB = this.openOrCreateDatabase("Items", MODE_PRIVATE, null);
        itemDB.execSQL("CREATE TABLE IF NOT EXISTS items (ItemID INTEGER PRIMARY KEY, itemName VARCHAR, itemType VARCHAR)");
        itemDB.execSQL("CREATE TABLE IF NOT EXISTS listItems (ListItemID INTEGER PRIMARY KEY, ListID INTEGER, ItemID INTEGER, ListName VARCHAR, ItemName VARCHAR, ItemType VARCHAR, CheckMark INTEGER, Quantity INTEGER)");

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

        itemList = new ArrayList<Item>();
        itemList.clear();
        itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);


        Cursor c = itemDB.rawQuery("SELECT * FROM listItems", null);
        int itemNameIndex = c.getColumnIndex("ItemName");
        int listNameIndex = c.getColumnIndex("ListName");
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                String comparisonName = c.getString(listNameIndex);
                if (currentList.equals(comparisonName)) {
                    Item it = new Item(c.getString(itemNameIndex), "2", 0);
                    itemList.add(it);
                }
            }
            while (c.moveToNext());
        }
        c.close();

        itemListView = (ListView) findViewById(R.id.itemList);
        itemListView.setAdapter(itemAdapter);

        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                return false;
            }
        });

        this.registerForContextMenu(itemListView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.itemList)
            this.getMenuInflater().inflate(R.menu.item_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem i) {
        int selectedItemId = i.getItemId();

        switch (selectedItemId){
            case R.id.deleteItem:

                Item it = itemList.get(position);
                String nameToDelete = it.getItemName();


                //remove
                itemAdapter.remove(it);
                itemDB.execSQL("DELETE FROM listItems where listItems.ListName = '"+ currentList +"'  AND listItems.ItemName = '"+ nameToDelete +"'");
                //refresh
                itemAdapter.notifyDataSetChanged();
                
                closeContextMenu();
                break;
        }
        closeContextMenu();
        return super.onContextItemSelected(i);
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
        Item it = new Item();
        if (id == R.id.createItem) {
            final Context cont = this;

            final AlertDialog.Builder builder = new AlertDialog.Builder(cont);
            builder.setTitle("Search for item:");
            final EditText input = new EditText(cont);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String in = input.getText().toString();

                    if(in.indexOf("'") >= 0) {
                        Toast message = Toast.makeText(cont, "Illegal key entered",Toast.LENGTH_SHORT);
                        message.show();
                        return;
                    }
                    if(in.indexOf("\\") >= 0) {
                        Toast message = Toast.makeText(cont, "Illegal key entered",Toast.LENGTH_SHORT);
                        message.show();
                        return;
                    }
                    //dedupping the item UI
                    for (int i = 0; i < itemList.size();i++) {
                        if(in.equals(itemList.get(i).getItemName())) {
                            Toast message = Toast.makeText(cont, "Already on List",Toast.LENGTH_SHORT);
                            message.show();
                            return;
                        }
                    }

                    if (searchItem(in) == true) {
                        String newQuantity = "1";
                        int defCheck = 0;
                        Item it = new Item(in, newQuantity, defCheck);
                        itemList.add(it);

                        ArrayAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);

                        String foundItemType = getItemType(it.getItemName());
                        int foundItemID = getItemID(it.getItemName());

                        itemDB.execSQL("INSERT INTO listItems (ListID, ItemID, ListName, ItemName, ItemType, CheckMark, Quantity) VALUES ('" + currentListID + "', '" + foundItemID + "', '" + currentList + "', '" + it.getItemName() + "','" + foundItemType + "', 1, '" + it.getItemQuantity() + "')");

                        itemListView.setAdapter(itemAdapter);
                    } else if (searchItem(in) == false) {
                        //creates dialog for the new item being added to the database
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(cont);
                        builder1.setTitle("Grocery type:");
                        final EditText input = new EditText(cont);
                        builder1.setView(input);
                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String newQuantity = "1";
                                int defCheck = 0;
                                Item it = new Item(in, newQuantity, defCheck);

                                String newItemCategory = input.getText().toString();

                                itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('" + it.getItemName() + "','" + newItemCategory + "')");
                                int newestItemID = getItemID(it.getItemName());
                                itemDB.execSQL("INSERT INTO listItems (ListID, ItemID, ListName, ItemName, ItemType, CheckMark, Quantity) VALUES ('" + currentListID + "', '" + newestItemID + "', '" + currentList + "', '" + it.getItemName() + "','" + newItemCategory + "', 1, '" + it.getItemQuantity() + "')");

                                //Adding the new item to the list
                                itemList.add(it);
                                ArrayAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);
                                itemListView.setAdapter(itemAdapter);
                            }
                        });
                        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();
                            }
                        }); //end type dialog
                        builder1.show();
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

        //If user chooses to delete all the item that are checked
        if (id == R.id.deleteItem) {

        }
        return super.onOptionsItemSelected(item);
    }

    private boolean searchItem(String input) {
        Cursor c = itemDB.rawQuery("SELECT * FROM items", null);
        int nameIndex = c.getColumnIndex("itemName");
        c.moveToFirst();
        do {
            String comparisonName = c.getString(nameIndex);
            if (input.equals(comparisonName)) {
                Log.i("Searchtest", "foundit");
                return true;
            }
        }
        while (c.moveToNext());
        Log.i("Searchtest", "DidNotFindit");
        c.close();

        return false;
    }
    public String getItemType(String input) {
        Cursor c = itemDB.rawQuery("SELECT * FROM items", null);
        int typeIndex = c.getColumnIndex("itemType");
        int nameIndex = c.getColumnIndex("itemName");
        c.moveToFirst();
        do {
            String comparisonName = c.getString(nameIndex);
            if (input.equals(comparisonName)) {
                Log.i("ItemTypeMatch", comparisonName);
                Log.i("ReturnedType", c.getString(typeIndex));
                return c.getString(typeIndex);
            }
        }
        while (c.moveToNext());
        Log.i("ItemType", "DidNotFindit");
        c.close();
        return null;
    }
    public int getItemID(String input) {
        Cursor d = itemDB.rawQuery("SELECT * FROM items", null);
        int idIndex = d.getColumnIndex("ItemID");
        int nameIndex = d.getColumnIndex("itemName");
        d.moveToFirst();
        do {
            String comparisonName = d.getString(nameIndex);
            if (input.equals(comparisonName)) {
                return d.getInt(idIndex);
            }
        }
        while (d.moveToNext());
        Log.i("getItemIDCheck", "DidNotFindit");
        d.close();
        return 0;
    }
    public void removeChecks(View itemsScreen) {
        for(int i=0; i < itemListView.getChildCount(); i++){
            ViewGroup checkmarkitem = (ViewGroup)itemListView.getChildAt(i);
            CheckBox checkbox = (CheckBox)checkmarkitem.findViewById(R.id.checkBox);
            checkbox.setChecked(false);
        }
    }

    /*
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        String name = ((TextView) view).getText().toString();

        CheckBox itemCheck = (CheckBox) findViewById(R.id.checkBox);

        if (itemCheck.isChecked()) {
            itemDB.execSQL("UPDATE listItems SET CheckMark = '1' WHERE ListName = '" + name + "'");
        } else {
            itemDB.execSQL("UPDATE listItems SET CheckMark = '0' WHERE ListName = '" + name + "'");
        }
    }
    */

}