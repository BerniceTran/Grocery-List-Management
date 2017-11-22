package glm.seclass.qc.edu.grocerylistmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;


 public class CustomArrayAdapter extends ArrayAdapter<String>{

    public CustomArrayAdapter(Context context, ArrayList itemList)
    {

        super(context, R.layout.item_row, itemList);
        System.out.print(itemList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater myInflater = LayoutInflater.from(getContext());

        View customView = convertView;
        if(customView == null){
            customView = myInflater.inflate(R.layout.item_row, parent, false);
        }

        String singleGroceryItem = getItem(position);
        TextView name = (TextView) customView.findViewById(R.id.itemName);
        CheckBox box = (CheckBox) customView.findViewById(R.id.checkBox);
        NumberPicker numberPicker = customView.findViewById(R.id.quantity);
        numberPicker.setMaxValue(999);
        numberPicker.setMinValue(1);

        name.setText(singleGroceryItem);

        return customView;
    }

}
