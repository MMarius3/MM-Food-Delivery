package delivery.businessLayer;

import delivery.dataLayer.FIleWriter;
import delivery.dataLayer.Serializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService implements IDeliveryServiceProcessing {

    public int computeBill(HashSet<MenuItem> menuItems) {
        int nr = 0;
        for (MenuItem menuItem : menuItems) {
            nr += menuItem.getPrice();
        }
        return nr;
    }

    public String getKey(HashMap<String, Order> clientsOrdering, Order order) {
        String username = null;
        for (Map.Entry<String, Order> entry: clientsOrdering.entrySet()) {
            if (entry.getValue() == order) {
                username = entry.getKey();
            }
        }
        return username;
    }

    public HashSet<String> filterClients(int oftenProducts, int orderValue) {
        HashSet<String> filteredClients = null;
        Serializer serializer = new Serializer();
        HashMap<String, Order> clientsOrdering = serializer.deserializeClientToOrder("serializeClientOrdering.txt");
        Map<Order, HashSet<MenuItem>> orders = serializer.deserializeOrders("serializeOrders.txt");
        HashMap<String, Integer> clientOrders = null;
        Iterator mIterator = orders.entrySet().iterator();
        while(mIterator.hasNext()) {
            Map.Entry mapCouple = (Map.Entry) mIterator.next();
            Order currentOrder = (Order) mapCouple.getKey();
            String username = getKey(clientsOrdering, currentOrder);
            int orderBill = computeBill((HashSet<MenuItem>) mapCouple.getValue());
            if (orderBill >= orderValue) {
                if (clientOrders.containsKey(username)) {
                    int nrOfOrders = clientOrders.get(username);
                    clientOrders.replace(username, nrOfOrders + 1);
                } else {
                    clientOrders.put(username, 1);
                }
            }
        }
        if (clientOrders != null) {
            Iterator mClientsIterator = clientOrders.entrySet().iterator();
            while (mClientsIterator.hasNext()) {
                Map.Entry mapCouple = (Map.Entry) mClientsIterator.next();
                if ((int)mapCouple.getValue() >= oftenProducts) {
                    filteredClients.add(mapCouple.getKey().toString());
                }
            }
        }
        return filteredClients;
    }

    public HashMap<MenuItem, Integer> filterProducts(Date date) {
        HashMap<MenuItem, Integer> items = null;
        Serializer serializer = new Serializer();
        Map<Order, HashSet<MenuItem>> orders = serializer.deserializeOrders("serializeOrders.txt");
        Iterator mIterator = orders.entrySet().iterator();
        while(mIterator.hasNext()) {
            Map.Entry mapCouple = (Map.Entry) mIterator.next();
            Date orderDate = ((Order)mapCouple.getKey()).getOrderDate();
            if (date.equals(orderDate)) {
                for (MenuItem menuItem : (HashSet<MenuItem>)mapCouple.getValue()) {
                    if (items.containsKey(menuItem)) {
                        int nrOfOrders = items.get(menuItem);
                        items.replace(menuItem, nrOfOrders + 1);
                    } else {
                        items.put(menuItem, 1);
                    }
                }
            }
        }
        return items;
    }

    public MenuItem findByTitle(HashSet<MenuItem> items, String title) {
        for (MenuItem item : items) {
            if (item.getTitle().contains(title)) {
                return item;
            }
        }
        return null;
    }


    public MenuItem compositeItems(HashSet<MenuItem> items) {
        String title = "";
        float rating = 0;
        int calories = 0, protein = 0, fat = 0, sodium = 0, price = 0;
        int index = 0;
        for (MenuItem item : items) {
            title = title.concat(item.getTitle());
            title = title.concat("; ");
            rating += item.getRating();
            calories += item.getCalories();
            protein += item.getProtein();
            fat += item.getFat();
            sodium += item.getSodium();
            price += item.getPrice();
            index++;
        }
        rating /= index;
        MenuItem menuItem = new BaseProduct(title, rating, calories, protein, fat, sodium, price, 0);
        return menuItem;
    }

    public void modifyItems(MenuItem menuItem) {
        Serializer serializerClientOrdering = new Serializer();
        HashSet<MenuItem> menuItems;
        MenuItem itemToChange = null;
        menuItems = serializerClientOrdering.deserializeItems("serializeItems.txt");
        for (MenuItem item : menuItems) {
            if (item.equals(menuItem)) {
                itemToChange = item;
            }
        }
        if (itemToChange != null) {
            menuItems.remove(itemToChange);
            int productOrders = itemToChange.getNrOfOrders();
            itemToChange.setNrOfOrders(productOrders + 1);
            menuItems.add(itemToChange);
        }
        serializerClientOrdering.serializeItems(menuItems, "serializeItems.txt");
    }

    public void addInOrder(MenuItem menuItem, String username) {
        modifyItems(menuItem);
        Serializer serializerClientOrdering = new Serializer();
        HashMap<String, Order> clientOrdering = null;
        clientOrdering = serializerClientOrdering.deserializeClientToOrder("serializeClientOrdering.txt");
        if (clientOrdering != null && clientOrdering.containsKey(username)) {
            Order order = clientOrdering.get(username);
            Serializer serializerAddNewOrder = new Serializer();
            Map<Order, HashSet<MenuItem>> orders;
            orders = serializerAddNewOrder.deserializeOrders("serializeOrders.txt");
            HashSet<MenuItem> menuItems = orders.get(order);
            menuItems.add(menuItem);
            orders.replace(order, menuItems);
            serializerAddNewOrder.serializeOrders(orders, "serializeOrders.txt");
        } else {
            Date date = new Date();
            Random random = new Random();
            int clientID = random.nextInt(1000);
            int orderID = random.nextInt(1000);
            Order order = new Order(clientID, orderID, date);
            HashSet<MenuItem> menuItems = new HashSet<>();
            menuItems.add(menuItem);
            Serializer serializerAddNewOrder = new Serializer();
            Map<Order, HashSet<MenuItem>> orders;
            orders = serializerAddNewOrder.deserializeOrders("serializeOrders.txt");
            if (orders == null) {
                orders = new HashMap<>();
            }
            orders.put(order, menuItems);
            serializerAddNewOrder.serializeOrders(orders, "serializeOrders.txt");
            clientOrdering = new HashMap<>();
            clientOrdering.put(username, order);
        }
        serializerClientOrdering.serializeClientToOrder(clientOrdering, "serializeClientOrdering.txt");
    }

    public void addProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        Serializer serializerItems = new Serializer();
        HashSet<MenuItem> items;
        items = serializerItems.deserializeItems("serializeItems.txt");
        items.add(new BaseProduct(title, rating, calories, protein, fat, sodium, price, 0));
        serializerItems.serializeItems(items, "serializeItems.txt");
    }

    public void deleteProduct(String title) {
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
        serializerItems.serializeItems(items, "serializeItems.txt");
    }

    public void firstReport(int startHour, int endHour) {
        Serializer serializer = new Serializer();
        Map<Order, HashSet<MenuItem>> orders;
        orders = serializer.deserializeOrders("serializeOrders.txt");
        List<Order> filteredOrders = orders.entrySet().stream()
                .filter(s -> {Date date = (s.getKey()).getOrderDate();
                        DateFormat df = new SimpleDateFormat("HH");
                        int hour = Integer.parseInt(df.format(date));
                        return (startHour <= hour && hour <= endHour);})
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        FIleWriter fIleWriter = new FIleWriter();
        fIleWriter.writeFirstReport(filteredOrders);
    }

    public void secondReport(int oftenProducts) {
        Serializer serializer = new Serializer();
        HashSet<MenuItem> menuItems;
        menuItems = serializer.deserializeItems("serializeItems.txt");
        List<MenuItem> filteredItems = menuItems.stream()
                .filter(s -> (s.getNrOfOrders() >= oftenProducts))
                .collect(Collectors.toList());
        FIleWriter fIleWriter = new FIleWriter();
        fIleWriter.writeSecondReport(filteredItems);
    }

    public void thirdReport(int oftenProducts, int orderValue) {
        HashSet<String> filteredClients = filterClients(oftenProducts, orderValue);
        FIleWriter fIleWriter = new FIleWriter();
        fIleWriter.writeThirdReport(filteredClients);
    }

    public void forthReport(String day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(day);
        } catch(ParseException pe) {
            pe.printStackTrace();
        }
        HashMap<MenuItem, Integer> filteredProducts = filterProducts(date);
        FIleWriter fIleWriter = new FIleWriter();
        fIleWriter.writeForthReport(filteredProducts);
    }

    public void addProductToComposite(String title) {
        HashSet<MenuItem> items;
        HashSet<MenuItem> menuItems;
        Serializer serializer = new Serializer();
        items = serializer.deserializeItems("serializeComposite.txt");
        menuItems = serializer.deserializeItems("serializeItems.txt");
        MenuItem menuItem = findByTitle(menuItems, title);
        items.add(menuItem);
        serializer.serializeItems(items, "serializeComposite.txt");
    }

    public void compositeProducts() {
        Serializer serializer = new Serializer();
        HashSet<MenuItem> itemsToComposite;
        itemsToComposite = serializer.deserializeItems("serializeComposite.txt");
        MenuItem menuItem = compositeItems(itemsToComposite);
        Serializer serializerItems = new Serializer();
        HashSet<MenuItem> items;
        items = serializerItems.deserializeItems("serializeItems.txt");
        items.add(menuItem);
        serializerItems.serializeItems(items, "serializeItems.txt");
    }

    public void addItemByClient(String title) {
        Serializer serializerItems = new Serializer();
        HashSet<MenuItem> items;
        items = serializerItems.deserializeItems("serializeItems.txt");
        MenuItem menuItem = findByTitle(items, title);
        Serializer serializerUsername = new Serializer();
        String currentUsername = serializerUsername.deserializeUsername("serializeUsername.txt");
        addInOrder(menuItem, currentUsername);
    }

    public void placeOrder() {
        FIleWriter billFileWriter = new FIleWriter();
        Serializer serializerClientOrdering = new Serializer();
        HashMap<String, Order> clientOrdering;
        clientOrdering = serializerClientOrdering.deserializeClientToOrder("serializeClientOrdering.txt");
        Serializer serializerAddNewOrder = new Serializer();
        Map<Order, HashSet<MenuItem>> orders;
        orders = serializerAddNewOrder.deserializeOrders("serializeOrders.txt");
        Serializer serializerUsername = new Serializer();
        String currentUsername = serializerUsername.deserializeUsername("serializeUsername.txt");
        HashSet<MenuItem> menuItems = orders.get(clientOrdering.get(currentUsername));
        billFileWriter.writeToFile(menuItems);
    }

    public void getNewItems() {
        Path path = Paths.get("C:\\Users\\Marius\\Desktop\\Products.csv");

        if (Files.exists(path)) {
            HashSet<MenuItem> items = new HashSet<>();
            try(Stream<String> stream = Files.lines(path)) {
                stream
                        .skip(1)
                        .limit(10)
                        .distinct()
                        .forEach(line -> {
                            String[] currentLine = line.split(",");
                            String title;
                            int calories, protein, fat, sodium, price;
                            title = currentLine[0];
                            try {
                                float rating = Float.parseFloat(currentLine[1]);
                            calories = Integer.parseInt(currentLine[2]);
                            protein = Integer.parseInt(currentLine[3]);
                            fat = Integer.parseInt(currentLine[4]);
                            sodium = Integer.parseInt(currentLine[5]);
                            price = Integer.parseInt(currentLine[6]);
                            items.add(new BaseProduct(title, rating, calories, protein, fat, sodium, price, 0));
                            } catch(NumberFormatException e){
                                e.printStackTrace();
                            }
                        });
                Serializer serializer = new Serializer();
                serializer.serializeItems(items, "serializeItems.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
