package delivery.dataLayer;

import delivery.businessLayer.MenuItem;
import delivery.businessLayer.Order;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Serializer {
    public void serializeHash(HashMap<String, String> map, String filename) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(map);
            out.close();
            file.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public HashMap<String, String> deserializeHash(String filename) {
        HashMap<String, String> map;
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            try {
                map = (HashMap<String, String>) in.readObject();
                in.close();
                file.close();
                return map;
            } catch(ClassNotFoundException cne) {
                cne.printStackTrace();
            }
            return null;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public void serializeItems(HashSet<MenuItem> items, String filename) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(items);
            out.close();
            file.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public HashSet<MenuItem> deserializeItems(String filename) {
        HashSet<MenuItem> items;
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            try {
                items = (HashSet<MenuItem>) in.readObject();
                in.close();
                file.close();
                return items;
            } catch(ClassNotFoundException cne) {
                cne.printStackTrace();
            }
            return null;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public void serializeOrders(Map<Order, HashSet<MenuItem>> orders, String filename) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(orders);
            out.close();
            file.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Map<Order, HashSet<MenuItem>> deserializeOrders(String filename) {
        Map<Order, HashSet<MenuItem>> orders;
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            try {
                orders = (Map<Order, HashSet<MenuItem>>) in.readObject();
                in.close();
                file.close();
                return orders;
            } catch(ClassNotFoundException cne) {
                cne.printStackTrace();
            }
            return null;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public void serializeClientToOrder(HashMap<String, Order> clientToOrder, String filename) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(clientToOrder);
            out.close();
            file.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public HashMap<String, Order> deserializeClientToOrder(String filename) {
        HashMap<String, Order> clientToOrder;
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            try {
                clientToOrder = (HashMap<String, Order>) in.readObject();
                in.close();
                file.close();
                return clientToOrder;
            } catch(ClassNotFoundException cne) {
                cne.printStackTrace();
            }
            return null;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public void serializeUsername(String username, String filename) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            DataOutputStream out = new DataOutputStream(file);
            out.writeUTF(username);
            out.close();
            file.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String deserializeUsername(String filename) {
        String username;
        try {
            FileInputStream file = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(file);
            try {
                username = in.readUTF();
                in.close();
                file.close();
                return username;
            } catch(UTFDataFormatException utf) {
                utf.printStackTrace();
            }
            return null;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
