package glm.seclass.qc.edu.grocerylistmanagement;

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

import java.util.ArrayList;



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
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

            final Context cont = this;

            AlertDialog.Builder builder = new AlertDialog.Builder(cont);
            builder.setTitle("Search for item:");
            final EditText input = new EditText(cont);
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
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(cont);
                        builder1.setTitle("Item not found. Input Type.");
                        final EditText input = new EditText(cont);
                        builder1.setView(input);
                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String newItemName = in;
                                String newItemCategory = input.getText().toString();
                                itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('" + newItemName + "','" + newItemCategory + "')");

                                //Adding the new item to the list
                                itemList.add(newItemName);
                                ListAdapter itemAdapter = new CustomArrayAdapter(getApplicationContext(), itemList);

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
//
//        if(id == R.id.deleteItem){
//
//
//        }

        return super.onOptionsItemSelected(item);
    }

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
