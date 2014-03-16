import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class GildedRose {

	private static ArrayList<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
    items = new ArrayList<Item>();
    items.add(new Item("+5 Dexterity Vest", 10, 20));
    items.add(new Item("Aged Brie", 2, 0));
    items.add(new Item("Elixir of the Mongoose", 5, 7));
    items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
    items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
    items.add(new Item("Conjured Mana Cake", 3, 6));

    updateQuality();
  }

  public GildedRose(ArrayList<Item> items) {
    this.items = items;

  }

  public ArrayList<Item> getItems() {
    return items;
  }
	
  public static void updateQuality() {
    Item item = null;
    
    for (int i = 0; i < items.size(); i++) {
      item = items.get(i);

      /* Sulfuras: do nothing for this legendary item */
      if (!isItemType(item.getName(), "Sulfuras")) {
        
        /* Brie: +1, max: 50 */
        if (isItemType(item.getName(), "Aged Brie")) {
          if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
          }
      
        /* Backstage passes: sellIn > 10: +1, sellIn <= 10: +2, sellIn <= 5: +3, sellIn < 0: 0, max: 50 */ 
        } else if (isItemType(item.getName(), "Backstage passes")) {
          if (item.getSellIn() > 10) {
            item.setQuality(item.getQuality() + 1);

          } else if (item.getSellIn() > 5) {
            item.setQuality(item.getQuality() + 2);

          } else if (item.getSellIn() > 0) {
            item.setQuality(item.getQuality() + 3);

          } else {
            item.setQuality(0);

          }

          if (item.getQuality() > 50) {
            item.setQuality(50);

          }

        /* Conjured: sellIn >= 0: -2, sellIn < 0: -4, min = 0 */ 
        } else if (isItemType(item.getName(), "Conjured")) {
          if (item.getSellIn() > 0) {
            item.setQuality(item.getQuality() - 2);
          } else {
            item.setQuality(item.getQuality() - 4);
          }

        /* Regular: sellIn > 0: -1, sellIn <= 0: -2, min = 0 */
        } else {
          if (item.getSellIn() > 0) {
            item.setQuality(item.getQuality() - 1);
          } else {
            item.setQuality(item.getQuality() - 2);
          }

        }

        /* Item quality can be no less than 0 */
        if (item.getQuality() < 0) {
          item.setQuality(0);
        }

        item.setSellIn(item.getSellIn() - 1);

      }
    }
  }


  private static boolean isItemType(String itemName, String itemType) {
    Pattern p = Pattern.compile(itemType);
    Matcher m = p.matcher(itemName);
    return m.lookingAt();
  }
  

}

