import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;


public class GildedRoseTest {

  private final static int REG_9_2 = 0;
  private final static int EXP_0_4 = 1;
  private final static int AB_0_48 = 2;
  private final static int SULF_0_100 = 3;
  private final static int BP_12_30 = 4;
  private final static int CJ_2_9 = 5;

  static ArrayList items = null;
  static GildedRose gR = null;

  @Before
  public void setUp() {
    items = new ArrayList<Item>();
    items.add(new Item("reg 9 2", 9, 2));
    items.add(new Item("exp 0 4", 0, 4));
    items.add(new Item("Aged Brie", 0, 48));
    items.add(new Item("Sulfuras - super legendary", 0, 100));
    items.add(new Item("Backstage passes for some nonsense", 12, 30));
    items.add(new Item("Conjured self-esteem", 2, 9));

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
  public void sellIn() {
    GildedRose gRose = new GildedRose(items);
    assertEquals("FAILURE: item sellIn not init'd properly", 9, gRose.getItems().get(REG_9_2).getSellIn());
    gRose.updateQuality();
    assertEquals("FAILURE: item sellIn not dec'd properly", 8, gRose.getItems().get(REG_9_2).getSellIn());
  }

  @Test
  public void regular() {
    GildedRose gRose = new GildedRose(items);
    assertEquals("FAILURE: item quality not init'd properly", 2, gRose.getItems().get(REG_9_2).getQuality());
    gRose.updateQuality();
    assertEquals("FAILURE: item quality not dec'd properly", 1, gRose.getItems().get(REG_9_2).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: item quality negative", 0, gRose.getItems().get(REG_9_2).getQuality());
  }


  @Test
  public void expired() {
    GildedRose gRose = new GildedRose(items);
    assertEquals("FAILURE: expired item quality not init'd properly", 4, gRose.getItems().get(EXP_0_4).getQuality());
    gRose.updateQuality();
    assertEquals("FAILURE: expired item quality not dec'd properly", 2, gRose.getItems().get(EXP_0_4).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: expired item quality negative", 0, gRose.getItems().get(EXP_0_4).getQuality());

  }


  @Test
  public void agedBrie() {
    GildedRose gRose = new GildedRose(items);
    assertEquals("FAILURE: aged brie quality not init'd properly", 48, gRose.getItems().get(AB_0_48).getQuality());
    gRose.updateQuality();
    assertEquals("FAILURE: aged brie quality not inc'd properly", 49, gRose.getItems().get(AB_0_48).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: aged brie quality not inc'd properly", 50, gRose.getItems().get(AB_0_48).getQuality());

  }

  @Test
  public void sulfuras() {
    GildedRose gRose = new GildedRose(items);
    assertEquals("FAILURE: sulfuras quality not init'd properly", 100, gRose.getItems().get(SULF_0_100).getQuality());
    assertEquals("FAILURE: sulfuras sellIn not init'd properly", 0, gRose.getItems().get(SULF_0_100).getSellIn());
    gRose.updateQuality();
    assertEquals("FAILURE: sulfuras quality not maintained properly", 100, gRose.getItems().get(SULF_0_100).getQuality());
    assertEquals("FAILURE: sulfuras sellIn not maintained properly", 0, gRose.getItems().get(SULF_0_100).getSellIn());

  }

  @Test
  public void backstagePasses() {
    GildedRose gRose = new GildedRose(items);
    assertEquals("FAILURE: backstage pass quality not init'd properly", 30, gRose.getItems().get(BP_12_30).getQuality());
    gRose.updateQuality();
    assertEquals("FAILURE: backstage pass quality not inc'd properly (days > 10)", 31, gRose.getItems().get(BP_12_30).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: backstage pass quality not inc'd properly (10 > days > 5)", 34, gRose.getItems().get(BP_12_30).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: backstage pass quality not inc'd properly (10 > days > 5)", 40, gRose.getItems().get(BP_12_30).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: backstage pass quality not inc'd properly (days < 5)", 48, gRose.getItems().get(BP_12_30).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: backstage pass quality not inc'd properly (days < 5, maxed at 50)", 50, gRose.getItems().get(BP_12_30).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: backstage pass quality not inc'd properly (days < 0)", 0, gRose.getItems().get(BP_12_30).getQuality());

  }

  @Test
  public void conjured() {
    GildedRose gRose = new GildedRose(items);
    assertEquals("FAILURE: conjured quality not init'd properly", 9, gRose.getItems().get(CJ_2_9).getQuality());
    gRose.updateQuality();
    assertEquals("FAILURE: conjured quality not dec'd properly", 7, gRose.getItems().get(CJ_2_9).getQuality());
    gRose.updateQuality();
    gRose.updateQuality();
    assertEquals("FAILURE: expired conjured quality not dec'd properly", 1, gRose.getItems().get(CJ_2_9).getQuality());
    gRose.updateQuality();
    assertEquals("FAILURE: conjured quality negative", 0, gRose.getItems().get(CJ_2_9).getQuality());
  }






}
