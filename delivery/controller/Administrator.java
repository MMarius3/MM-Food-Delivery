package delivery.controller;

import delivery.businessLayer.*;
import delivery.businessLayer.MenuItem;
import delivery.presentationLayer.AdministratorGUI;
import delivery.dataLayer.Serializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Administrator implements ActionListener {
    AdministratorGUI panel;
    HashMap<String, String> administrators;
    Serializer serializer = new Serializer();

    public void assignValuesToRow(String[][] adminData, int index, MenuItem menuItem) {
        adminData[index][0] = menuItem.getTitle();
        adminData[index][1] = String.valueOf(menuItem.getRating());
        adminData[index][2] = String.valueOf(menuItem.getCalories());
        adminData[index][3] = String.valueOf(menuItem.getProtein());
        adminData[index][4] = String.valueOf(menuItem.getFat());
        adminData[index][5] = String.valueOf(menuItem.getSodium());
        adminData[index][6] = String.valueOf(menuItem.getPrice());
    }

    public String[][] populateTable(HashSet<MenuItem> items) {
        String[][] adminData = new String[30][7];
        int index = 0;
        for (MenuItem menuItem : items) {
            assignValuesToRow(adminData, index, menuItem);
            index++;
        }
        return adminData;
    }

    public Administrator(AdministratorGUI panel) {this.panel = panel; }
        public void actionPerformed(ActionEvent e) {
        if (administrators == null) { administrators = new HashMap<>(); }
        administrators = serializer.deserializeHash("serializeAdminsFile.txt");
        String command = e.getActionCommand();
        String username = panel.usernameText.getText();
        String password = panel.passwordText.getText();
        System.out.println("username: " + username);
        System.out.println("password: " + password);
            System.out.println(administrators);
        if (command.equals("logIn")) {
            System.out.println(administrators);
            if (administrators.containsKey(username)) {
                if (administrators.get(username).equals(password)) {
                    panel.showAdminPanel();
                } else {
                    panel.adminLabel.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Your password may be incorrect.</b></p></html>");
                }
            } else {
                panel.adminLabel.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Your username may be incorrect.</b></p></html>");
            }
        } else if (command.equals("signUp")) {
            administrators.put(username, password);
        } else if (command.equals("addProduct")) {
            String title = panel.productTitleText.getText();
            float rating = Float.parseFloat(panel.productRatingText.getText());
            int calories = Integer.parseInt(panel.productCaloriesText.getText());
            int protein = Integer.parseInt(panel.productProteinText.getText());
            int fat = Integer.parseInt(panel.productFatText.getText());
            int sodium = Integer.parseInt(panel.productSodiumText.getText());
            int price = Integer.parseInt(panel.productPriceText.getText());
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.addProduct(title, rating, calories, protein, fat, sodium, price);
        } else if (command.equals("editProduct")) {
            String title = panel.productTitleText.getText();
            float rating = 0;
            int calories = 0;
            int protein = 0;
            int fat = 0;
            int sodium = 0;
            int price = 0;
            Serializer serializerItems = new Serializer();
            HashSet<MenuItem> items;
            items = serializerItems.deserializeItems("serializeItems.txt");
            MenuItem item = null;
            for (MenuItem menuItem : items) {
                if (menuItem.getTitle().equals(title)) {
                    item = menuItem;
                    break;
                }
            }
            items.remove(item);
            rating = item.getRating();
            calories = item.getCalories();
            protein = item.getProtein();
            fat = item.getFat();
            sodium = item.getSodium();
            price = item.getPrice();

            if (!panel.productRatingText.getText().isEmpty()) {
                rating = Integer.parseInt(panel.productRatingText.getText());
            }
            if (!panel.productCaloriesText.getText().isEmpty()) {
                calories = Integer.parseInt(panel.productCaloriesText.getText());
            }
            if (!panel.productProteinText.getText().isEmpty()) {
                protein = Integer.parseInt(panel.productProteinText.getText());
            }
            if (!panel.productFatText.getText().isEmpty()) {
                fat = Integer.parseInt(panel.productFatText.getText());
            }
            if (!panel.productSodiumText.getText().isEmpty()) {
                sodium = Integer.parseInt(panel.productSodiumText.getText());
            }
            if (!panel.productPriceText.getText().isEmpty()) {
                price = Integer.parseInt(panel.productPriceText.getText());
            }
            items.add(new BaseProduct(title, rating, calories, protein, fat, sodium, price, 0));
            serializerItems.serializeItems(items, "serializeItems.txt");

        } else if (command.equals("deleteProduct")) {
            String title = panel.productTitleText.getText();
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.deleteProduct(title);
        } else if (command.equals("viewProducts")) {
            String[][] adminData;
            String[] adminColumnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
            Serializer serializerItems = new Serializer();
            HashSet<MenuItem> items;
            items = serializerItems.deserializeItems("serializeItems.txt");
            adminData = populateTable(items);
            JTable adminTable = new JTable(adminData, adminColumnNames);
            adminTable.setBounds(30,40,200,150);
            JScrollPane adminScroll = new JScrollPane(adminTable);
            panel.adminAccessTablePanel.add(adminScroll);
            panel.showAdminPanel();
        } else if (command.equals("firstReport")) {
            int startHour = Integer.parseInt(panel.startHour.getText());
            int endHour = Integer.parseInt(panel.endHour.getText());
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.firstReport(startHour, endHour);
        } else if (command.equals("secondReport")) {
            int oftenProducts = Integer.parseInt(panel.oftenProducts.getText());
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.secondReport(oftenProducts);
        } else if (command.equals("thirdReport")) {
            int oftenProducts = Integer.parseInt(panel.oftenProducts.getText());
            int orderValue = Integer.parseInt(panel.highOrders.getText());
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.thirdReport(oftenProducts, orderValue);
        } else if (command.equals("forthReport")) {
            String day = panel.day.getText();
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.forthReport(day);
        } else if (command.equals("addProductToComposite")) {
            String title = panel.productTitleText.getText();
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.addProductToComposite(title);
        } else if (command.equals("compositeProducts")) {
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.compositeProducts();
        }
            serializer.serializeHash(administrators, "serializeAdminsFile.txt");
            System.out.println(administrators);
    }
}
