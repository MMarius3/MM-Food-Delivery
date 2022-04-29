package delivery.businessLayer;

import java.util.ArrayList;
import java.util.HashSet;

public class CompositeProduct extends MenuItem{

    public int computePrice(HashSet<BaseProduct> products) {
        int price = 0;
        for (BaseProduct product : products) {
            price += product.computePrice();
        }
        return price;
    }
}
