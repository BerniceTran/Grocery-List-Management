package glm.seclass.qc.edu.grocerylistmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import glm.seclass.qc.edu.grocerylistmanagement.Item;

import java.util.ArrayList;

 public class CustomArrayAdapter extends ArrayAdapter<Item>{

    public CustomArrayAdapter(Context context, ArrayList itemList)
    {
        super(context, R.layout.item_row, itemList);
        System.out.println(itemList);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater myInflater = LayoutInflater.from(getContext());

        View customView = convertView;
        if(customView == null){
            customView = myInflater.inflate(R.layout.item_row, parent, false);
        }

        Item singleGroceryItem = getItem(position);

        TextView name = (TextView) customView.findViewById(R.id.itemName);
        CheckBox box = (CheckBox) customView.findViewById(R.id.checkBox);
        TextView qty = (TextView) customView.findViewById(R.id.qty);
        EditText itemQuantity = (EditText) customView.findViewById(R.id.itemQuantity);

        name.setText(singleGroceryItem.getItemName());
        itemQuantity.setText(singleGroceryItem.getItemQuantity());

        return customView;
    }

}
