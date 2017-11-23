package glm.seclass.qc.edu.grocerylistmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class GroceryLists extends AppCompatActivity {

    SQLiteDatabase listDB;
    ArrayList<String> groceryList = new ArrayList<String>();
    ArrayAdapter<String> groceryListAdapter = null;
    ListView lv = null;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listDB = this.openOrCreateDatabase("listDB",MODE_PRIVATE,null);
        listDB.execSQL("CREATE TABLE IF NOT EXISTS lists (ListID INTEGER PRIMARY KEY AUTOINCREMENT, listName VARCHAR)");
        //listDB.execSQL("DELETE FROM lists");
        listDB.execSQL("CREATE TABLE IF NOT EXISTS customers (CustomerID INTEGER PRIMARY KEY AUTOINCREMENT, firstName VARCHAR, lastName VARCHAR, email VARCHAR, password VARCHAR)");
        listDB.execSQL("CREATE TABLE IF NOT EXISTS customerLists (CustomerListID INTEGER PRIMARY KEY AUTOINCREMENT, listID INTEGER, CustomerID INTEGER)");
        //listDB.execSQL("DELETE FROM CustomerLists");


        //placeholder - once we have a login screen, we can make this more official
        listDB.execSQL("INSERT INTO customers (firstName, lastName, email, password) VALUES ('John','Smith','johnsmith@gmail.com','John1234')");

     /*   listDB.execSQL("INSERT INTO lists (listName) VALUES ('ChristmasList')");
        listDB.execSQL("INSERT INTO lists (listName) VALUES ('EasterList')");
        listDB.execSQL("INSERT INTO lists (listName) VALUES ('ThanksgivingList')");
*/
        groceryList = new ArrayList<>();
        groceryListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, groceryList);


        //code to populate groceryList ArrayList with values from the list database
        Cursor c = listDB.rawQuery("SELECT * FROM lists",null);
        int nameIndex = c.getColumnIndex("listName");
        c.moveToFirst();

        if(c.moveToFirst()) {
            do {
                String savedLists = c.getString(nameIndex);
                groceryList.add(savedLists);
            }
            while (c.moveToNext());
        }
        c.close();


        lv = (ListView) findViewById(R.id.groceryList);
        lv.setAdapter(groceryListAdapter);

        //       String listName = groceryListAdapter.getItem(pos);
        //Access to grocery list contents
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(GroceryLists.this, GroceryItems.class);
                appInfo.putExtra("ListName", groceryListAdapter.getItem(position));
                long idToPass = groceryListAdapter.getItemId(pos);
                int dbIDToPass = ((int) idToPass) + 1;
                appInfo.putExtra("ListID",dbIDToPass);
                startActivity(appInfo);
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Sets position
                pos = i;
                return false;
            }
        });
        //Register to the context Menu
        this.registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.groceryList)
            this.getMenuInflater().inflate(R.menu.context_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int selectedItemId = item.getItemId();
        //Access to the list view
        switch(selectedItemId) {
            case R.id.deleteList:
                String nameToDelete = groceryListAdapter.getItem(pos);
                //remove
                groceryListAdapter.remove(groceryList.get(pos));
                listDB.execSQL("DELETE FROM lists where lists.listName = '"+ nameToDelete +"'  ");

                //refresh
                groceryListAdapter.notifyDataSetChanged();

                //checking to see if record deleted
                Cursor c = listDB.rawQuery("SELECT * FROM lists",null);
                int nameIndex = c.getColumnIndex("listName");
                c.moveToFirst();
                if(c.moveToFirst()) {
                    do {
                        String savedLists = c.getString(nameIndex);
                        Log.i("deleteListCheck", savedLists);
                    }
                    while (c.moveToNext());
                }
                groceryListAdapter.notifyDataSetChanged();

                closeContextMenu();
                break;

            case R.id.renameList:
                final Context cont = this;
                final String oldName = groceryListAdapter.getItem(pos);
                Log.i("ListToRename",oldName);
                //adapter.remove(groceryList.get(pos));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Edit name:");
                final EditText input = new EditText(this);
                Log.i("NewNameForList",input.getText().toString());
                final long idToRename = groceryListAdapter.getItemId(pos);
                final int integerID = (int) idToRename;
                final int dbPrimaryID = integerID + 1;
                Log.i("RenamedPosition",String.valueOf(integerID));

                builder.setView(input);
                final AlertDialog.Builder ok = builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newListName = input.getText().toString();

                        if(newListName.indexOf("'") >= 0) {
                            Toast message = Toast.makeText(cont, "Illegal key entered",Toast.LENGTH_SHORT);
                            message.show();
                            return;
                        }
                        if(newListName.indexOf("\\") >= 0) {
                            Toast message = Toast.makeText(cont, "Illegal key entered",Toast.LENGTH_SHORT);
                            message.show();
                            return;
                        }
                        for (int i = 0; i < groceryList.size();i++) {
                            if(newListName.equals(groceryList.get(i))) {
                                Toast message = Toast.makeText(cont, "That list exists already",Toast.LENGTH_SHORT);
                                message.show();
                                return;
                            }
                        }
                        listDB.execSQL("UPDATE lists SET listName = ' " + newListName +" ' WHERE ListID =" +dbPrimaryID);
                        groceryListAdapter.notifyDataSetChanged();
                        groceryList.set(integerID, newListName);
                    }

                });
                builder.show();
                groceryListAdapter.notifyDataSetChanged();

                //checking to see if record renamed
                Cursor d = listDB.rawQuery("SELECT * FROM lists",null);
                int nameIndex2 = d.getColumnIndex("listName");
                int listprimary = d.getColumnIndex("ListID");
                d.moveToFirst();
                do {
                    String savedLists = d.getString(nameIndex2);
                    int dbID = d.getInt(listprimary);
                    Log.i("renameListCheck",savedLists);
                    Log.i("renameListCheck", String.valueOf(dbID));
                }
                while(d.moveToNext());

                closeContextMenu();
                break;
        }
        closeOptionsMenu();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grocery_lists, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.createList) {
            final Context cont = this;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Create new list:");
            final EditText input = new EditText(this);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String DBhelper = input.getText().toString();

                    if(DBhelper.indexOf("'") >= 0) {
                        Toast message = Toast.makeText(cont, "Illegal key entered",Toast.LENGTH_SHORT);
                        message.show();
                        return;
                    }
                    if(DBhelper.indexOf("\\") >= 0) {
                        Toast message = Toast.makeText(cont, "Illegal key entered",Toast.LENGTH_SHORT);
                        message.show();
                        return;
                    }
                    for (int i = 0; i < groceryList.size();i++) {
                        if(DBhelper.equals(groceryList.get(i))) {
                            Toast message = Toast.makeText(cont, "That list exists already",Toast.LENGTH_SHORT);
                            message.show();
                            return;
                        }
                    }

                    groceryList.add(DBhelper);
                    Collections.sort(groceryList);
                    lv.setAdapter(groceryListAdapter);

                    //insert into database here
                    listDB.execSQL("INSERT INTO lists (listName) VALUES ('" + DBhelper + "')");
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
}