package glm.seclass.qc.edu.grocerylistmanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;



public class GroceryItems extends AppCompatActivity {


    ArrayList<String> itemList = new ArrayList<String>();
    ArrayList<String> displayList = new ArrayList<String>();

    ListView itemListView;

    SQLiteDatabase itemDB;

    String currentList;
    Integer currentListID;
    Integer currentItemID;
    String currentItemName;
    String currentItemType;
    int defualtQuantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Tests retrieval of list name
        Log.i("ListClicked",getIntent().getStringExtra("ListName"));
        currentList = getIntent().getStringExtra("ListName");
        //Tests retrieval of list position
        Log.i("PositionClicked", String.valueOf(getIntent().getIntExtra("ListID",0)));
        currentListID = getIntent().getIntExtra("ListID",0);

        itemDB = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
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

        displayList = new ArrayList<String>();
        ListAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);

        Cursor c = itemDB.rawQuery("SELECT * FROM listItems",null);
        int nameIndex = c.getColumnIndex("ItemName");
        int listNameIndex = c.getColumnIndex("ListName");
        c.moveToFirst();
        if(c.moveToFirst()) {
            do {
                String comparisonName = c.getString(listNameIndex);
                if(currentList.equals(comparisonName)) {
                    Log.i("ItemNameMatch", comparisonName);
                    Log.i("ReturnedName", c.getString(nameIndex));
                    displayList.add(c.getString(nameIndex));
                }
                String savedLists = c.getString(nameIndex);
                itemList.add(savedLists);
            }
            while (c.moveToNext());
        }
        c.close();

        itemListView = (ListView) findViewById(R.id.itemList);
        itemListView.setAdapter(itemAdapter);
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
            final Context cont = this;

            final AlertDialog.Builder builder = new AlertDialog.Builder(cont);
            builder.setTitle("Search for item:");
            final EditText input = new EditText(cont);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String in = input.getText().toString();
                    if(searchItem(in) == true) {
                        itemList.add(in);

                        //case of item that already exists in item database being added to grocery list
                        Log.i("Type found:",getItemType(in));

                        ListAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);

                        int newQuantity2 = 1;
                        String foundItemName = in;
                        String foundItemType = getItemType(in);
                        int foundItemID = getItemID(in);
                        itemDB.execSQL("INSERT INTO listItems (ListID, ItemID, ListName, ItemName, ItemType, CheckMark, Quantity) VALUES ('" + currentListID + "', '" + foundItemID + "', '" + currentList + "', '" + foundItemName + "','" + foundItemType + "', 1, '" + newQuantity2 + "')");

                        itemListView.setAdapter(itemAdapter);
                        //itemAdapter.notify();
                        //checking to see if item successfully made it to listitem
                        getTableAsString(itemDB, "listItems");
                    }
                    else if(searchItem(in) == false) {
                        //creates dialog for the new item being added to the database
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(cont);
                        builder1.setTitle("Item not found. Input Type.");
                        final EditText input = new EditText(cont);
                        builder1.setView(input);
                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String newItemName = in;
                                String newItemCategory = input.getText().toString();
                                itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('" + newItemName + "','" + newItemCategory + "')");
                                int newestItemID = getItemID(newItemName);
                                int newQuantity = 1;
                                itemDB.execSQL("INSERT INTO listItems (ListID, ItemID, ListName, ItemName, ItemType, CheckMark, Quantity) VALUES ('" + currentListID + "', '" + newestItemID + "', '" + currentList + "', '" + newItemName + "','" + newItemCategory + "', 1, '" + defualtQuantity + "')");

                                //Adding the new item to the list
                                itemList.add(newItemName);
                                ListAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);

                                itemListView.setAdapter(itemAdapter);
                                getTableAsString(itemDB, "items");
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
//        if(id == R.id.deleteItem){
//         /*  final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Delete selected items?");
//            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    SparseBooleanArray checkedItem = itemListView.getCheckedItemPositions();
//                    int itemCount = itemListView.getCount();
//                    for(int i=itemCount-1; i >= 0; i--){
//                        if(checkedItem.get(i)){
//                            adapter.remove(itemList.get(i));
//                        }
//                    }
//                    checkedItem.clear();
//                    adapter.notifyDataSetChanged();
//                }
//
//            });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            builder.show();
//            return true;
//        }*/
//    }

        return super.onOptionsItemSelected(item);
    }

    private boolean searchItem(String input) {
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
    public String getItemType(String input) {
        Cursor c = itemDB.rawQuery("SELECT * FROM items", null);
        int typeIndex = c.getColumnIndex("itemType");
        int nameIndex = c.getColumnIndex("itemName");
        c.moveToFirst();
        do {
            String comparisonName = c.getString(nameIndex);
            if(input.equals(comparisonName)) {
                Log.i("ItemTypeMatch", comparisonName);
                Log.i("ReturnedType", c.getString(typeIndex));
                return c.getString(typeIndex);
            }
        }
        while(c.moveToNext());
        Log.i("ItemType","DidNotFindit");
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
            if(input.equals(comparisonName)) {
                //Log.i("ItemTypeMatch", comparisonName);
                //Log.i("ReturnedType", c.getString(typeIndex));
                return d.getInt(idIndex);
            }
        }
        while(d.moveToNext());
        Log.i("getItemIDCheck","DidNotFindit");
        d.close();
        return 0;
    }
    public void getTableAsString(SQLiteDatabase db, String tableName) {
        Log.d("datacheckup", "getTableAsString called");
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }
        allRows.close();
        Log.i("datacheckup",tableString);
    }
}
