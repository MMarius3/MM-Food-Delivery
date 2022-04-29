package delivery.presentationLayer;

import delivery.controller.Client;
import delivery.controller.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EmployeeGUI {

    public JFrame employeeFrame, employeeAccessFrame;

    public JPanel employeeMainPanel, employeeFirstPanel, employeeAccessPanel;

    public JButton logIn, signUp;

    public JTextField usernameText, passwordText;

    public JLabel employeeLabel;

    public EmployeeGUI() { GUI(); }

    private void GUI() {

        employeeFrame = new JFrame("Employee");
        employeeFrame.setSize(700, 900);
        employeeFrame.setLayout(new GridLayout(1, 1));
        employeeMainPanel = new JPanel();
        employeeMainPanel.setBackground(Color.darkGray);
        employeeFirstPanel = new JPanel();
        employeeFirstPanel.setBackground(Color.darkGray);

        GridLayout employeeRegistration = new GridLayout(2,1);
        employeeFirstPanel.setLayout(employeeRegistration);
        employeeFirstPanel.setPreferredSize(new Dimension(400,200));
        logIn = this.createSimpleButton("LOG IN");
        signUp = this.createSimpleButton("SIGN UP");
        employeeFirstPanel.add(logIn);
        employeeFirstPanel.add(signUp);

        usernameText = new JTextField(5);
        usernameText.setPreferredSize(new Dimension(50, 10));
        employeeFirstPanel.add(new JLabel("<html><font color='white'>Username: </font></html>", JLabel.LEFT));
        employeeFirstPanel.add(usernameText);
        passwordText = new JTextField(5);
        passwordText.setPreferredSize(new Dimension(50, 10));
        employeeFirstPanel.add(new JLabel("<html><font color='white'>Password: </font></html>", JLabel.LEFT));
        employeeFirstPanel.add(passwordText);

        employeeLabel = new JLabel("", JLabel.LEFT);
        employeeLabel.setPreferredSize(new Dimension(333,70));
        employeeLabel.setText("<html><font color='FFFFFF'></font></html>");
        employeeFirstPanel.add(employeeLabel);

        employeeMainPanel.add(employeeFirstPanel);
        employeeFrame.add(employeeMainPanel);

        employeeAccessFrame = new JFrame("Employee Page");
        employeeAccessFrame.setSize(700, 900);
        employeeAccessFrame.setLayout(new GridLayout(1, 1));
        employeeAccessPanel = new JPanel();
        employeeAccessPanel.setBackground(Color.darkGray);
        employeeAccessFrame.add(employeeAccessPanel);
    }

    private JButton createSimpleButton(String text) {
        JButton button = new JButton("<html><p style='font-size:21px;font-family:Helvetica;'>"+text+"</p></html>");
        button.setForeground(Color.yellow);
        button.setBackground(Color.darkGray);
        Border line = BorderFactory.createLineBorder(Color.darkGray);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    public void showEmployeeRegistrationPanel() {
        logIn.setActionCommand("logIn");
        signUp.setActionCommand("signUp");
        logIn.addActionListener(new Employee(this));
        signUp.addActionListener(new Employee(this));
        employeeFrame.setVisible(true);
    }

    public void showEmployeePanel() {
        employeeAccessFrame.setVisible(true);
    }
}
