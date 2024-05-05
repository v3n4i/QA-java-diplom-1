import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Ingredient ingredientOne;
    @Mock
    Ingredient ingredientTwo;
    @Mock
    Bun bun;

    Burger burger = new Burger();

    @Test
    public void setBunTest(){
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientOne);
        Assert.assertFalse(burger.ingredients.isEmpty());
    }

    @Test
    public void removeIndexTest() {
        burger.addIngredient(ingredientOne);
        burger.removeIngredient(0);
        Assert.assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(ingredientOne, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        Mockito.when(bun.getPrice()).thenReturn(Constants.PRICE_FOR_BUN_ONE);
        Mockito.when(ingredientOne.getPrice()).thenReturn(Constants.SAUSE_ONE_PRICE);
        Mockito.when(ingredientTwo.getPrice()).thenReturn(Constants.SAUSE_TWO_PRICE);
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        Assert.assertEquals((Constants.PRICE_FOR_BUN_ONE*2+Constants.SAUSE_ONE_PRICE+Constants.SAUSE_TWO_PRICE), burger.getPrice(), 0.1);
    }

    @Test
    public void getReceiptTest(){
        burger.setBuns(bun);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        Mockito.when(bun.getName()).thenReturn(Constants.BUN_TWO);
        Mockito.when(bun.getPrice()).thenReturn(Constants.PRICE_FOR_BUN_TWO);
        Mockito.when(ingredientOne.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientTwo.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientOne.getName()).thenReturn(Constants.SAUSE_ONE);
        Mockito.when(ingredientTwo.getName()).thenReturn(Constants.FILLING_ONE);
        Mockito.when(ingredientOne.getPrice()).thenReturn(Constants.SAUSE_ONE_PRICE);
        Mockito.when(ingredientTwo.getPrice()).thenReturn(Constants.FILLING_ONE_PRICE);
        String str = String.format("(==== "+Constants.BUN_TWO+" ====)%n") +
                String.format("= "+IngredientType.SAUCE.toString().toLowerCase()+" "+Constants.SAUSE_ONE+" =%n")+
                String.format("= "+IngredientType.FILLING.toString().toLowerCase()+" "+Constants.FILLING_ONE+" =%n")+
                String.format("(==== "+Constants.BUN_TWO+" ====)%n")+
                String.format("%n")+
                String.format("Price: %f%n",(Constants.PRICE_FOR_BUN_TWO*2+Constants.SAUSE_ONE_PRICE+Constants.FILLING_ONE_PRICE));

        Assert.assertEquals(str, burger.getReceipt());


    }
}
