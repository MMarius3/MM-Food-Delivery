package delivery.dataLayer;

import delivery.businessLayer.MenuItem;
import delivery.businessLayer.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FIleWriter {
    public void writeToFile(HashSet<MenuItem> menuItems) {
        File file = new File("bill.txt");
        float avgRating = 0;
        int totalCalories = 0, totalProtein = 0, totalFat = 0, totalSodium = 0, totalPrice = 0;
        int index = 0;
        try {
            FileWriter myWriter = new FileWriter(file.getName());
            for (MenuItem menuItem : menuItems) {
                myWriter.write("Title: " + menuItem.getTitle() + "Price: " + menuItem.getPrice() + "\n");
                avgRating += menuItem.getRating();
                totalCalories += menuItem.getCalories();
                totalProtein += menuItem.getProtein();
                totalFat += menuItem.getFat();
                totalSodium += menuItem.getSodium();
                totalPrice += menuItem.getPrice();
                index++;
            }
            avgRating /= index;
            myWriter.write("Rating: " + avgRating + "\n" +
                    "Total Calories: " + totalCalories + "\n" +
                    "Total Protein: " + totalProtein + "\n" +
                    "Total Fat: " + totalFat + "\n" +
                    "Total Sodium: " + totalSodium + "\n" +
                    "Total Price: " + totalPrice + "\n");
            myWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFirstReport(List<Order> orders) {
        File file = new File("firstReport.txt");
        try {
            FileWriter myWriter = new FileWriter(file.getName());
            for (Order order : orders) {
                myWriter.write("OrderID: " + order.getOrderID() + "\n" +
                        "ClientID: " + order.getClientID() + "\n" +
                        "Date: " + order.getOrderDate() + "\n");
            }
            myWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSecondReport(List<MenuItem> menuItems) {
        File file = new File("secondReport.txt");
        try {
            FileWriter myWriter = new FileWriter(file.getName());
            if (menuItems != null) {
                for (MenuItem menuItem : menuItems) {
                    myWriter.write(menuItem.getTitle() + " was ordered " + menuItem.getNrOfOrders() + "times. \n");
                }
            } else {
                myWriter.write("No products ordered that many times.");
            }
            myWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeThirdReport(HashSet<String> clients) {
        File file = new File("thirdReport.txt");
        try {
            FileWriter myWriter = new FileWriter(file.getName());
            if (clients != null) {
                myWriter.write("The favorite clients are: \n");
                for (String client : clients) {
                    myWriter.write(client + "\n");
                }
            } else {
                myWriter.write("No favorite clients.");
            }
            myWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeForthReport(HashMap<MenuItem, Integer> items) {
        File file = new File("forthReport.txt");
        try {
            FileWriter myWriter = new FileWriter(file.getName());
            if (items != null) {
                myWriter.write("The products ordered within that day are: \n");
                Iterator hmIterator = items.entrySet().iterator();
                while(hmIterator.hasNext()) {
                    Map.Entry mapCouple = (Map.Entry) hmIterator.next();
                    myWriter.write("The item: " + ((MenuItem)mapCouple.getKey()).getTitle() + "was ordered" + mapCouple.getValue() + "times. \n");
                }
            } else {
                myWriter.write("No products ordered that day.");
            }
            myWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
