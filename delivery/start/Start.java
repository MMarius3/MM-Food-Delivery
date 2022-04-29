package delivery.start;

import delivery.businessLayer.Order;
import delivery.dataLayer.Serializer;
import delivery.presentationLayer.AdministratorGUI;
import delivery.presentationLayer.ClientGUI;
import delivery.presentationLayer.EmployeeGUI;

import java.util.HashMap;

/**
 * @author Macean Marius
 * @since May 2, 2021
 */
public class Start {

    public static void main(String[] args) {

        /*Serializer serializer = new Serializer();
        HashMap<String, Order> clientOrdering = null;
        serializer.serializeClientToOrder(clientOrdering, "serializeClientOrdering.txt");*/

        AdministratorGUI adminPanel = new AdministratorGUI();
        adminPanel.showAdminRegistrationPanel();
        ClientGUI clientPanel = new ClientGUI();
        clientPanel.showClientRegistrationPanel();
        EmployeeGUI employeePanel = new EmployeeGUI();
        employeePanel.showEmployeeRegistrationPanel();
    }
}
