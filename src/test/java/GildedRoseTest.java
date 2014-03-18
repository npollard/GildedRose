import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class GildedRoseTest {
  private GildedRose gr;

  @Before public void setUp() {
    gr = new GildedRose();
    ArrayList<Item> items = new ArrayList<Item>();
    items.add(new Item("+5 Dexterity Vest", 10, 20));
    items.add(new Item("Aged Brie", 2, 0));
    items.add(new Item("Elixir of the Mongoose", 5, 7));
    items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
    items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
    items.add(new Item("Conjured Mana Cake", 3, 6));
    gr.items = items;
  }

  @Test
  public void testNormalItem() {
    assertEquals("normal", 10, gr.items.get(0).getSellIn());
    assertEquals("normal", 20, gr.items.get(0).getQuality());
    GildedRose.updateQuality();
    assertEquals("normal updated", 9, gr.items.get(0).getSellIn());
    assertEquals("normal updated", 19, gr.items.get(0).getQuality());
    int q = 100000;
    while (gr.items.get(0).getSellIn() >= 0) {
      GildedRose.updateQuality();
      q = gr.items.get(0).getQuality();
    }
    GildedRose.updateQuality();
    assertEquals("normal expired", q - 2, gr.items.get(0).getQuality());
  }

  @Test
  public void testBrie() {
    assertEquals("brie", 0, gr.items.get(1).getQuality());
    GildedRose.updateQuality();
    assertEquals("brie", 1, gr.items.get(1).getQuality());
    for (int i = 0; i < 100; i++) {
      GildedRose.updateQuality();
    }
    assertEquals("can't exceed 50", 50, gr.items.get(1).getQuality());
  }

  @Test
  public void testSulfuras() {
    assertEquals("sulfuras", 80, gr.items.get(3).getQuality());
    for (int i = 0; i < 100; i++) {
      GildedRose.updateQuality();
    }
    assertEquals("sulfuras", 80, gr.items.get(3).getQuality());
    assertEquals("sulfuras", 0, gr.items.get(3).getSellIn());
  }

  @Test
  public void testPasses() {
    assertEquals("passes", 20, gr.items.get(4).getQuality());
    GildedRose.updateQuality();
    assertEquals("passes", 21, gr.items.get(4).getQuality());
    
    while (gr.items.get(4).getSellIn() > 9) {
      GildedRose.updateQuality();
    }
    int previousQuality = gr.items.get(4).getQuality();
    GildedRose.updateQuality();
    assertEquals("passes < 10 days", previousQuality + 2, gr.items.get(4).getQuality());
    
    while (gr.items.get(4).getSellIn() > 4) {
      GildedRose.updateQuality();
    }
    previousQuality = gr.items.get(4).getQuality();
    GildedRose.updateQuality();
    assertEquals("passes < 5 days", previousQuality + 3, gr.items.get(4).getQuality());

    while (gr.items.get(4).getSellIn() >= 0) {
      GildedRose.updateQuality();
    }
    assertEquals("expired passes", 0, gr.items.get(4).getQuality());

  }



}
