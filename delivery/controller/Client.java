package delivery.controller;

import delivery.businessLayer.MenuItem;
import delivery.dataLayer.Serializer;
import delivery.presentationLayer.ClientGUI;
import delivery.businessLayer.DeliveryService;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class Client implements ActionListener {
    ClientGUI panel;
    HashMap<String, String> clients;

    public Client(ClientGUI panel) {this.panel = panel; }

    public void assignValuesToRow(String[][] clientData, int index, MenuItem menuItem) {
        clientData[index][0] = menuItem.getTitle();
        clientData[index][1] = String.valueOf(menuItem.getRating());
        clientData[index][2] = String.valueOf(menuItem.getCalories());
        clientData[index][3] = String.valueOf(menuItem.getProtein());
        clientData[index][4] = String.valueOf(menuItem.getFat());
        clientData[index][5] = String.valueOf(menuItem.getSodium());
        clientData[index][6] = String.valueOf(menuItem.getPrice());
    }

    public String[][] populateTable(HashSet<MenuItem> items) {
        String[][] clientData = new String[30][7];
        int index = 0;
        for (MenuItem menuItem : items) {
            assignValuesToRow(clientData, index, menuItem);
            index++;
        }
        return clientData;
    }

    public String[][] populateTableOnFilter(HashSet<MenuItem> items) {
        String[][] clientData = new String[30][7];
        String title = " ";
        int rating = 0, calories = 0, protein = 0, fat = 0, sodium = 0, price = 0;
        boolean t = false, r = false, c = false, pro = false, f = false, s = false, pri = false;

        if (!panel.titleText.getText().isEmpty()) {
            title = panel.titleText.getText();
            t = true;
        }
        if (!panel.ratingText.getText().isEmpty()) {
            rating = Integer.parseInt(panel.ratingText.getText());
            r = true;
        }
        if (!panel.caloriesText.getText().isEmpty()) {
            calories = Integer.parseInt(panel.caloriesText.getText());
            c = true;
        }
        if (!panel.proteinText.getText().isEmpty()) {
            protein = Integer.parseInt(panel.proteinText.getText());
            pro = true;
        }
        if (!panel.fatText.getText().isEmpty()) {
            fat = Integer.parseInt(panel.fatText.getText());
            f = true;
        }
        if (!panel.sodiumText.getText().isEmpty()) {
            sodium = Integer.parseInt(panel.sodiumText.getText());
            s = true;
        }
        if (!panel.priceText.getText().isEmpty()) {
            price = Integer.parseInt(panel.priceText.getText());
            pri = true;
        }
        int index = 0;
        HashSet<MenuItem> filteredItems;
        boolean[] filterOrNot = {false, false, false, false, false, false, false};
        filterOrNot[0] = t;
        filterOrNot[1] = r;
        filterOrNot[2] = c;
        filterOrNot[3] = pro;
        filterOrNot[4] = f;
        filterOrNot[5] = s;
        filterOrNot[6] = pri;
        String[] titleString = {null};
        titleString[0] = title;
        float[] ratingString = {0};
        ratingString[0] = rating;
        int[] varString = {0, 0, 0, 0, 0, 0};
        varString[0] = calories;
        varString[1] = protein;
        varString[2] = fat;
        varString[3] = sodium;
        varString[4] = price;


        filteredItems = items.stream()
                .filter(item -> ((ratingString[0] == item.getRating() || !filterOrNot[1]) && (varString[0] == item.getCalories() || !filterOrNot[2]) && (varString[1] == item.getProtein() || !filterOrNot[3]) &&
                        (varString[2] == item.getFat() || !filterOrNot[4]) && (varString[3] == item.getSodium() || !filterOrNot[5]) && (varString[4] == item.getPrice() || !filterOrNot[6]) &&
                        (item.getTitle().contains(titleString[0]) || !filterOrNot[0])))
                .collect(Collectors.toCollection(HashSet::new));

        for (MenuItem menuItem : filteredItems) {
                assignValuesToRow(clientData, index, menuItem);
                index++;
        }
        return clientData;
    }

    public void showTableOnFilter() {
        String[][] clientData;
        String[] clientColumnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Serializer serializerItemsOnFilter = new Serializer();
        HashSet<MenuItem> items;
        items = serializerItemsOnFilter.deserializeItems("serializeItems.txt");
        clientData = populateTableOnFilter(items);
        JTable clientTable = new JTable(clientData, clientColumnNames);
        clientTable.setBounds(30,40,200,150);
        JScrollPane clientScroll = new JScrollPane(clientTable);
        panel.clientAccessPanel.add(clientScroll);
        panel.showClientPanel();
    }

    public void actionPerformed(ActionEvent e) {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.getNewItems();
        if (clients == null) { clients = new HashMap<>(); }
        Serializer serializer = new Serializer();
        clients = serializer.deserializeHash("serializeClientsFile.txt");
        String command = e.getActionCommand();
        String username = panel.usernameText.getText();
        String password = panel.passwordText.getText();
        if (command.equals("logIn")) {
            if (clients.containsKey(username)) {
                if (clients.get(username).equals(password)) {
                    String[][] clientData;
                    String[] clientColumnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                    Serializer serializerItems = new Serializer();
                    HashSet<MenuItem> items;
                    items = serializerItems.deserializeItems("serializeItems.txt");
                    clientData = populateTable(items);
                    JTable clientTable = new JTable(clientData, clientColumnNames);
                    clientTable.setBounds(30,40,200,150);
                    JScrollPane clientScroll = new JScrollPane(clientTable);
                    panel.clientAccessPanel.add(clientScroll);
                    panel.showClientPanel();
                    Serializer serializerUsername = new Serializer();
                    serializerUsername.serializeUsername(username, "serializeUsername.txt");
                } else {
                    panel.clientLabel.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Your password may be incorrect.</b></p></html>");
                }
            } else {
                panel.clientLabel.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Your username may be incorrect.</b></p></html>");
            }
        } else if (command.equals("signUp")) {
            clients.put(username, password);
        } else if (command.equals("filter")) {
            showTableOnFilter();
        } else if (command.equals("addItem")) {
            if (!panel.addItemText.getText().isEmpty()) {
                String title;
                title = panel.addItemText.getText();
                DeliveryService delivery = new DeliveryService();
                delivery.addItemByClient(title);
            }
        } else if (command.equals("placeOrder")) {
            DeliveryService delivery = new DeliveryService();
            delivery.placeOrder();
        }
        serializer.serializeHash(clients, "serializeClientsFile.txt");
    }
}
