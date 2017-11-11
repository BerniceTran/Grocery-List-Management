package glm.seclass.qc.edu.grocerylistmanagement;

import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class GroceryItems extends AppCompatActivity {

    ArrayList<Item> itemList = null;
    ItemAdapter adapter;
    ListView lv;
    EditText searchItem;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchItem = (EditText) findViewById(R.id.searchItem);
        addButton = (Button) findViewById(R.id.addButton);



        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Item item = new Item();
                itemList = new ArrayList<Item>();

                String sItem = searchItem.getText().toString();
                item.setItem(sItem);
                itemList.add(item);

                lv = (ListView) findViewById(R.id.itemList);
                //addItem();
                ItemAdapter adapter = new ItemAdapter(getApplicationContext(), R.layout.item_row, itemList);
                lv.setAdapter(adapter);



            }


        });





    }
    //testing to see if an item could be added
    private void addItem()
    {
        Item item = new Item();
        item.setItem("Bananas");
        itemList.add(item);
    }

    public class Item
    {
        private String item;

        public void setItem(String id) {this.item = item;}

        public String getItem() {return item;}

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

            itemName.setText(itemList.get(position).getItem());

            return convertView;

        }
    }

}
