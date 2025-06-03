package interfaces;

import Entity.Product;

public interface IFileIOOperations {
    public void savePurchaseHistory(String customerId, String method, Product[] products, int count, double total);
    public void loadProducts(Product[] products);
    public void saveProducts(Product[] products);
}
