package delivery.controller;

import delivery.businessLayer.Order;
import delivery.dataLayer.Serializer;
import delivery.presentationLayer.EmployeeGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Employee implements ActionListener {
    EmployeeGUI panel;
    HashMap<String, String> employees;
    Serializer serializer = new Serializer();

    public String[][] populateTable(HashMap<String, Order> clientOrdering) {
        String[][] employeeData = new String[20][3];
        Iterator hmIterator = clientOrdering.entrySet().iterator();
        int index = 0;
        while(hmIterator.hasNext()) {
            Map.Entry mapCouple = (Map.Entry) hmIterator.next();
            employeeData[index][0] = mapCouple.getKey().toString();
            employeeData[index][1] = String.valueOf(((Order)mapCouple.getValue()).getOrderID());
            employeeData[index][2] = ((Order) mapCouple.getValue()).getOrderDate().toString();
        }
        return employeeData;
    }

    public Employee(EmployeeGUI panel) {this.panel = panel; }
    public void actionPerformed(ActionEvent e) {
        if (employees == null) { employees = new HashMap<>(); }
        employees = serializer.deserializeHash("serializeEmployeesFile.txt");
        String command = e.getActionCommand();
        String username = panel.usernameText.getText();
        String password = panel.passwordText.getText();
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println(employees);
        if (command.equals("logIn")) {
            System.out.println(employees);
            if (employees.containsKey(username)) {
                if (employees.get(username).equals(password)) {
                    Serializer serializer = new Serializer();
                    HashMap<String, Order> clientOrdering;
                    clientOrdering = serializer.deserializeClientToOrder("serializeClientOrdering.txt");
                    String[][] employeeData;
                    employeeData = populateTable(clientOrdering);
                    String[] employeeColumnNames = {"Client", "Order", "Date"};
                    JTable employeeTable = new JTable(employeeData, employeeColumnNames);
                    employeeTable.setBounds(30,40,200,150);
                    JScrollPane employeeScroll = new JScrollPane(employeeTable);
                    panel.employeeAccessPanel.add(employeeScroll);
                    panel.showEmployeePanel();
                } else {
                    panel.employeeLabel.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Your password may be incorrect.</b></p></html>");
                }
            } else {
                panel.employeeLabel.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Your username may be incorrect.</b></p></html>");
            }
        } else if (command.equals("signUp")) {
            employees.put(username, password);
        }
        serializer.serializeHash(employees, "serializeEmployeesFile.txt");
        System.out.println(employees);
    }

}
