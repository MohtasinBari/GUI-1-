package Entity;

public class SkinCareProduct extends Product {

    public SkinCareProduct() {}

    public SkinCareProduct(String name, String brand, double price, int quantity) {
        super(name, brand, price, quantity);
    }

    @Override
    public String getProductType() {
        return "Skin Care";
    }
}
