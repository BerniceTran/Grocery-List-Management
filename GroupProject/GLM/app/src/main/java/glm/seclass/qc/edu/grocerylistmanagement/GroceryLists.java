package glm.seclass.qc.edu.grocerylistmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class GroceryLists extends AppCompatActivity {

    SQLiteDatabase listDB;
    ArrayList<String> groceryList = null;
    ArrayAdapter<String> adapter = null;
    ListView lv = null;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listDB = this.openOrCreateDatabase("Lists",MODE_PRIVATE,null);
        listDB.execSQL("CREATE TABLE IF NOT EXISTS lists (ListID INTEGER PRIMARY KEY, listName VARCHAR)");
        listDB.execSQL("CREATE TABLE IF NOT EXISTS customers (CustomerID INTEGER PRIMARY KEY, firstName VARCHAR, lastName VARCHAR, email VARCHAR, password VARCHAR)");
        listDB.execSQL("CREATE TABLE IF NOT EXISTS customerLists (CustomerListID INTEGER PRIMARY KEY, listID INTEGER, CustomerID INTEGER)");

        //placeholder - once we have a login screen, we can make this more official
        listDB.execSQL("INSERT INTO customers (firstName, lastName, email, password) VALUES ('John','Smith','johnsmith@gmail.com','John1234')");

        groceryList = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, groceryList);

        lv = (ListView) findViewById(R.id.groceryList);
        lv.setAdapter(adapter);

        //Access to grocery list contents
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(GroceryLists.this, GroceryItems.class);
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
                //remove
                adapter.remove(groceryList.get(pos));
                String nameToDelete = "yoyo";
                listDB.execSQL("DELETE FROM lists where lists.listName = '"+ nameToDelete +"'  ");
                //refresh
                adapter.notifyDataSetChanged();

                break;
            case R.id.renameList:
                adapter.remove(groceryList.get(pos));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Edit name:");
                final EditText input = new EditText(this);
                String newName = input.getText().toString();
                String oldName = "yoyo";

                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.insert(input.getText().toString(), pos);
                        Collections.sort(groceryList);
                    }
                });
                builder.show();
                listDB.execSQL("UPDATE lists SET listName = '"+ newName +"' WHERE listName = '"+ oldName +"' ");
                break;
        }
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Create new list:");
            final EditText input = new EditText(this);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String DBhelper = input.getText().toString();

                    groceryList.add(DBhelper);
                    Collections.sort(groceryList);
                    lv.setAdapter(adapter);

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