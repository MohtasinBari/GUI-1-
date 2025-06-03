package File;

import java.io.*; 
import java.util.Scanner;

import interfaces.IFileIOOperations;
import Entity.Product;
import Entity.SkinCareProduct;

public class FileIO implements IFileIOOperations {

    public void savePurchaseHistory(String customerId, String method, Product[] products, int count, double total) {
        try {
            File directory = new File("./File");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(new File(directory, "PurchaseHistory.txt"), true);

            writer.write("Customer ID: " + customerId + "\n");
            writer.write("Method: " + method + "\n");

            for (int i = 0; i < count; i++) {
                Product p = products[i];
                if (p != null) {
                    writer.write("- " + p.getName() + " (" + p.getBrand() + ") - BDT " + p.getPrice() + "\n");
                }
            }

            writer.write("Total: BDT " + total + "\n");
            writer.write("-----\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving purchase history: " + e.getMessage());
        }
    }

    public void loadProducts(Product[] products) {
        try {
            Scanner sc = new Scanner(new File("./File/Products.txt"));
            int index = 0;

            while (sc.hasNextLine() && index < products.length) {
                String line = sc.nextLine();
                String[] data = line.split(";");
                String name = data[0];
                String brand = data[1];
                double price = Double.parseDouble(data[2]);
                int quantity = Integer.parseInt(data[3]);

                products[index++] = new SkinCareProduct(name, brand, price, quantity);
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Products file not found: " + e.getMessage());
        }
    }

    public void saveProducts(Product[] products) {
        try {
            FileWriter writer = new FileWriter(new File("./File/Products.txt"), false);
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null) {
                    Product p = products[i];
                    writer.write(p.getName() + ";" + p.getBrand() + ";" + p.getPrice() + ";" + p.getQuantity() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }
}
