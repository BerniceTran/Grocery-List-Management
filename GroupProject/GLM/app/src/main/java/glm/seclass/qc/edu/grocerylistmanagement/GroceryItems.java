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
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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

        /** SQL Inserts Begin HERE */
        //fruits
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('apples','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('oranges','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pears','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('apricots','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('avocados','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bananas','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('berries','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cherries','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('grapefruit','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('grapes','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('kiwi','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lemons','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('limes','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('melons','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('nectarines','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('watermelon','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('peaches','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('papaya','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('plums','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pomegranate','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('blueberries','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('strawberries','fruit')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('raspberry','fruit')");

        //vegetables
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cucumber','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lettuce','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cabbage','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('atrichooke','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('asparagus','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('basil','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('beets','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('broccoli','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cauliflower','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('carrots','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('celery','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chilies','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chives','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cilantro','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('corn','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('eggplant','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('garlic','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('green onions','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('romaine lettuce','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('onions','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bell peppers','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('peppers','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('potatoes','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('salad greens','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('sprouts','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('spinach','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('squash','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pumpkin','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('tomatoes','vegetable')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('zuucchini','vegetable')");

        //meat
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bacon','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('prime rib','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lunchmeat','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ham','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('turkey','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pork','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chicken','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lamb','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('steak','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('hot dogs','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ground beef','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ground chicken','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ground turkey','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ground pork','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ground beef','meat')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('sausage','meat')");

        //breakfast
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cereal','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('poptarts','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pancake mix','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pancakes','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('toaster strudel','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('oatmeal','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('grits','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pancake syrup','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('syrup','breakfast')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('waffles','breakfast')");

        //frozen
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pizza bites','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chicken tenders','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('desserts','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('fish sticks','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('fruit','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ice','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ice cream','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ice pops','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('popsicles','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pie shells','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pot pies','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('fries','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pizza','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('juice','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('vegetables','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('burgers','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('fish','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('juice','frozen')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('TV dinners','frozen')");

        //dairy
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('butter','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cheese','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cheddar','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cream cheese','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cream','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('mozzarella','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('eggs','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('egg whites','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('feta cheese','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('half & half','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('milk','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('swiss cheese','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('whipped cream','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('yogurt','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('plain yogurt','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('sour cream','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('american cheese','dairy')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('provolone','dairy')");

        //snacks
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('candy','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cookies','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('crackers','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('dried fruit','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('fruit snacks','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('gelatin','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('graham crackers','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('granola bars','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('gum','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('nuts','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('popcorn','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('potato chips','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('tortilla chips','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chips','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pretzels','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pudding','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('raisins','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('seeds','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('beer mix','snacks')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('trail mix','snacks')");

        //seafood
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('catfish','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cod','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('crab','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('halibut','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lobster','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('oysters','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('salmon','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('shrimp','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('tilapia','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('tuna','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('prawn','seafood')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('caviar','seafood')");

        //baking
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('baking powder','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('baking soda','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bread crumbs','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cake decor','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cake mix','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('canned milk','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chocolate chips','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cocoa','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cornmeal','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cornstarch','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('flour','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('food coloring','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('frosting','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('muffin mix','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pie crust','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('shortening','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('sugar','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('brown sugar','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('powdered sugar','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('yeast','baking')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('sprinkles','baking')");

        //bakery
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bagels','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bread','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('donuts','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('doughnuts','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cake','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('croutons','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('dinner rolls','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('hamburger buns','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('hotdog buns','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('muffins','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pastries','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pie','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pita bread','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('corn tortillas','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('flour tortillas','bakery')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('croissants','bakery')");

        //canned goods
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('applesauce','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('baked beans','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('black beans','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('broth','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bullion cubes','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('canned fruit','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('canned vegetables','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('chili','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('creamed corn','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('jam','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('jelly','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('mushrooms','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('green olives','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('black olives','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pasta sauce','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('peanut butter','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pie filling','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pickles','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('soup','canned goods')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('clam chowder','canned goods')");

        //Pasta & rice
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('brown rice','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('burger helper','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('couscous','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('elbow macaroni','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lasagna','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('mac & cheese','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('rigatoni','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('noodle mix','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ramen noodles','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('rice mix','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('jasmine rice','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('white rice','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('angel hair','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('spaghetti','pasta & rice')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('fettucine','pasta & rice')");

        //Seasoning
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bay leaves','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cinnamon','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cloves','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cumin','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('curry','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('dill','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('garlic salt','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('garlic powder','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('gravy mix','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('italian seasoning','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('marinade','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('oregano','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('paprika','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('pepper','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('poppy seed','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('sage','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('salt','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('vanilla extract','seasoning')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cayenne','seasoning')");

        //Condiments
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bbq sauce','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('catsup','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('ketchup','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cocktail sauce','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cooking spray','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('honey','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('horseradish','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('hot sauce','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lemon juice','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('mayonnaise','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('mustard','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('olive oil','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('relish','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('salad dressing','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('soy sauce','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('salsa','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('teriyaki','condiments')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('vinegar','condiments')");

        //Beverages
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('beer','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('champagne','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('club soda','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('coffee','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('tea','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('diet soda','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('energy drinks','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('apple juice','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('orange juice','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('soda','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('wine','beverages')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('liquor','beverages')");

        //Paper Products
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('aluminum foil','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('coffee filters','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cups','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('garbage bags','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('napkins','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('paper plates','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('paper towels','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('plastic bags','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('plastic cutlery','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('plastic wrap','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('straws','paper product')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('wax paper','paper product')");

        //Personal Care
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('conditioner','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('deoderant','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lotion','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bath soap','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('bug repellant','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('cotton swabs','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('dental floss','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('family planning','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('facial tissue','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('feminime products','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('hair spray','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('hand soap','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('lip care','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('makeup','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('mouthwash','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('razors','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('shampoo','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('shaving cream','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('sunscreen','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('toilet tissue','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('toothbrush','personal')");
        itemDB.execSQL("INSERT INTO items (itemName, itemType) VALUES ('toothpaste','personal')");
        /** SQL Inserts End Here */

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
          //    itemAdapter.notifyDataSetChanged();

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

        if (id == R.id.uncheckBoxes) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Remove all checks?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    removeChecks(itemListView);
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
