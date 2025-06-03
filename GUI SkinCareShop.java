package GUI;

import Entity.Product;
import Entity.SkinCareProduct;
import File.FileIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SkinCareShop extends JFrame implements ActionListener {

    Font font = new Font("Times New Roman", Font.BOLD, 15);

    JLabel nameLabel, brandLabel, priceLabel, quantityLabel, indexLabel;
    JTextField nameField, brandField, priceField, quantityField, indexField;
    JButton addButton, updateButton, deleteButton, clearButton, saveButton;
    JTextArea productDisplayArea;

    Product[] products = new Product[3];
    FileIO fileOps = new FileIO();

    public SkinCareShop() {
        super("Skin Care Shop");
        this.setSize(550, 500);
        this.setLocation(300, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        this.setIconImage(new ImageIcon("./images/HelloKitty.jpg").getImage());

       
        nameLabel = createLabel("Name:", 10, 20);
        nameField = createTextField(120, 20);

        brandLabel = createLabel("Brand:", 10, 60);
        brandField = createTextField(120, 60);

        priceLabel = createLabel("Price:", 10, 100);
        priceField = createTextField(120, 100);

        quantityLabel = createLabel("Quantity:", 10, 140);
        quantityField = createTextField(120, 140);

        indexLabel = createLabel("Index [0-2]:", 10, 180);
        indexField = createTextField(120, 180);

        
        addButton = createButton("Add", 10, 220);
        updateButton = createButton("Update", 110, 220);
        deleteButton = createButton("Delete", 210, 220);
        clearButton = createButton("Clear", 310, 220);
        clearButton.setBackground(new Color(66, 245, 179));
        saveButton = createButton("Save", 410, 220);
        saveButton.setBackground(Color.YELLOW);

        
        productDisplayArea = new JTextArea();
        productDisplayArea.setFont(font);
        productDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(productDisplayArea);
        scrollPane.setBounds(10, 270, 510, 180);
        this.add(scrollPane);

        updateDisplay();
        this.setVisible(true);
    }

    JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 100, 30);
        label.setFont(font);
        this.add(label);
        return label;
    }

    JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 180, 30);
        tf.setFont(font);
        this.add(tf);
        return tf;
    }

    JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 90, 30);
        btn.setFont(font);
        btn.setBackground(new Color(66, 245, 179));
        btn.addActionListener(this);
        this.add(btn);
        return btn;
    }

    void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                sb.append("[").append(i).append("]\n")
                  .append(products[i].getProductDetails()).append("\n");
            }
        }
        productDisplayArea.setText(sb.toString());
    }

    void clearFields() {
        nameField.setText("");
        brandField.setText("");
        priceField.setText("");
        quantityField.setText("");
        indexField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String name = nameField.getText().trim();
            String brand = brandField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            int index = indexField.getText().isEmpty() ? -1 : Integer.parseInt(indexField.getText().trim());

            if (e.getSource() == addButton) {
                for (int i = 0; i < products.length; i++) {
                    if (products[i] == null) {
                        products[i] = new SkinCareProduct(name, brand, price, quantity);
                        break;
                    }
                }
            } else if (e.getSource() == updateButton && index >= 0 && index < products.length && products[index] != null) {
                products[index] = new SkinCareProduct(name, brand, price, quantity);
            } else if (e.getSource() == deleteButton && index >= 0 && index < products.length) {
                products[index] = null;
            } else if (e.getSource() == clearButton) {
                clearFields();
            } else if (e.getSource() == saveButton) {
                checkAndSave();
            }

            updateDisplay();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void checkAndSave() {
        boolean allFilled = true;
        double total = 0;
        int count = 0;

        for (Product p : products) {
            if (p == null) {
                allFilled = false;
                break;
            }
            count++;
            total += p.getPrice() * p.getQuantity();
        }

        if (allFilled) {
            fileOps.savePurchaseHistory("CUST001", "Cash", products, count, total);
            JOptionPane.showMessageDialog(this, "Purchase history saved.", "Saved", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
