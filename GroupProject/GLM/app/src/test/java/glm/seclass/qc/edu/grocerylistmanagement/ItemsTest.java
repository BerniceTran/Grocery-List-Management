package glm.seclass.qc.edu.grocerylistmanagement;

import org.junit.Test;

import glm.seclass.qc.edu.grocerylistmanagement.GroceryItems;
import glm.seclass.qc.edu.grocerylistmanagement.GroceryItems.Item;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
/*
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
public

}
*/
public class ItemsTest{
  Item i = new Item();

  @Before
  public void setUp(){

  }

  @After
  public void tearDown(){
    i = null;
  }

  /*
   * First Test checks for an existing item
   * that already exists within the database
   * regardless of casing
   */
  @Test
  public void testSearchItem1(){
    i.setName("Bacon");
    assertEquals(true, i.searchItem());
  }

  /*
   * Second Test checks for a nonexisting item
   * that already exists within the database
   */
  @Test
  public void testSearchItem2(){
    i.setName("Happiness");
    assertEquals(false, i.searchItem());
  }

  /*
   * Third Test checks for an invalid input
   * such as a digit that a user could potentially
   * put in
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSearchItem3(){
    i.setName("1Bacon");
    assertEquals(false, i.searchItem());
  }

  /*
   * Fourth Test checks for an invalid input
   * such as a symbol that a user could potentially
   * put in
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSearchItem4(){
    i.setName("Bacon~!");
    assertEquals(false, i.searchItem());
  }

  /*
   * Fifth Test checks for an invalid input
   * such as a blank that a user could potentially
   * put in
   */
  @Test
  public void testSearchItem5(){
    i.setName("");
    assertEquals(false, i.searchItem());
  }

  /*
   * Sixth Test checks for an invalid input
   * such as a empty spaces that a user
   * could potentially put in
   */
  @Test
  public void testSearchItem6(){
    i.setName("       ");
    assertEquals(false, i.searchItem());
  }

  /*
   * Seventh Test checks for an invalid input
   * such as spread out digits within the string
   * that a user input
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSearchItem7(){
    i.setName("B4c0n");
    assertEquals(false, i.searchItem());
  }

  /*
   * Eigth Test checks for an invalid input
   * such as all digits that a user might
   * input
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSearchItem8(){
    i.setName("12345");
    assertEquals(false, i.searchItem());
  }
}
