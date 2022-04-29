package delivery.businessLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private Date orderDate;

    public Order (int orderID, int clientID, Date orderDate) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == null || object.getClass() != this.getClass()) return true;
        Order order = (Order) object;
        return orderID == order.orderID;
    }

    @Override
    public int hashCode() {
        int value = 13;
        value = 37 * value + orderID;
        value = 37 * value + clientID;
        if (orderDate != null) {
            value = 37 * value + orderDate.hashCode();
        }
        return value;
    }
}
