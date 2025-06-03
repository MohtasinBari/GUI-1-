package Entity;

public abstract class Product {
    private String name;
    private String brand;
    private double price;
    private int quantity;

    public Product() {
		
	}

    public Product(String name, String brand, double price, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract String getProductType();

    public String getName() { 
	return name; 
	}

    public void setName(String name) { 
	this.name = name; 
	}

    public String getBrand() { 
	return brand; 
	}

    public void setBrand(String brand) { 
	this.brand = brand; 
	}

    public double getPrice() { 
	return price; 
	}

    public void setPrice(double price) { 
	this.price = price; 
	}

    public int getQuantity() { 
	return quantity; 
	}

    public void setQuantity(int quantity) { 
	this.quantity = quantity; 
	}

    public String getProductDetails() {
        return  "Type: " + getProductType() + "\n" +
                "Name: " + name + "\n" +
                "Brand: " + brand + "\n" +
                "Price: " + price + " BDT\n" +
                "Quantity: " + quantity + "\n";
    }

    @Override
    public String toString() {
        return getProductDetails();
    }
}
