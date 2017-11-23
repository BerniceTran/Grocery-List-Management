package glm.seclass.qc.edu.grocerylistmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RJ on 11/22/2017.
 */

public class Item extends ArrayList<Item> {
    String itemName;
    String itemQuantity;
    int itemCheck;

    Item(){}

    public Item(String itemName, String itemQuantity, int itemCheck) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemCheck = itemCheck;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemCheck() {
        return itemCheck;
    }

    public void setItemCheck(int itemCheck) {
        this.itemCheck = itemCheck;
    }
}
