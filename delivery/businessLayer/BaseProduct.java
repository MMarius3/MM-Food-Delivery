package delivery.businessLayer;

public class BaseProduct extends MenuItem{

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price, int nrOfOrders) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
        this.nrOfOrders = nrOfOrders;
    }
    public int computePrice() {
        return this.price;
    }
}
