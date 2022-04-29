package delivery.businessLayer;

public interface IDeliveryServiceProcessing {

    void addProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price);
    void deleteProduct(String title);
    void firstReport(int startHour, int endHour);
    void secondReport(int oftenProducts);
    void thirdReport(int oftenProducts, int orderValue);
    void forthReport(String day);
    void addProductToComposite(String title);
    void compositeProducts();
    void addItemByClient(String title);
    void placeOrder();
}
