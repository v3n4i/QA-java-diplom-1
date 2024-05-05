import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;
public class BunTest {

    Bun bun = new Bun(Constants.BUN_ONE, Constants.PRICE_FOR_BUN_ONE);


    @Test
    public void getNameTest() {
        Assert.assertEquals(Constants.BUN_ONE, bun.getName());
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals(Constants.PRICE_FOR_BUN_ONE, bun.getPrice(), 0.1);
    }

}
