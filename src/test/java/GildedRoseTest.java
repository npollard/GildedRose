import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;


public class GildedRoseTest {

  static ArrayList items = null;

  @Before
  public void setUp() {
    items = new ArrayList<Item>();
    items.add(new Item("reg 2 5", 2, 5));
  }

  @After
  public void tearDown() {
    items = null;
  }

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}

  @Test
  public void testSellInDecrement() {
    Item reg2 = new Item("reg 2 5", 2, 5);
    assertEquals("FAILURE: item SellIn not init'd properly", reg2.getSellIn(), 2);
  }
}
