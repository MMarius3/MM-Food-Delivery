package delivery.presentationLayer;

import delivery.controller.Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientGUI {

    public JFrame clientFrame, clientAccessFrame;

    public JPanel clientMainPanel, clientFirstPanel, clientAccessPanel, clientAccessFilerPanel, clientAccessOrderPanel;

    public JButton logIn, signUp, filter, placeOrder, addItem;

    public JTextField usernameText, passwordText, titleText, ratingText, caloriesText,
                      proteinText, fatText, sodiumText, priceText, addItemText;

    public JLabel clientLabel;

    public ClientGUI() { GUI(); }

    private void GUI() {

        clientFrame = new JFrame("Client");
        clientFrame.setSize(700, 900);
        clientFrame.setLayout(new GridLayout(1, 1));
        clientMainPanel = new JPanel();
        clientMainPanel.setBackground(Color.darkGray);
        clientFirstPanel = new JPanel();
        clientFirstPanel.setBackground(Color.darkGray);

        GridLayout clientRegistration = new GridLayout(2,1);
        clientFirstPanel.setLayout(clientRegistration);
        clientFirstPanel.setPreferredSize(new Dimension(400,200));
        logIn = this.createSimpleButton("LOG IN");
        signUp = this.createSimpleButton("SIGN UP");
        clientFirstPanel.add(logIn);
        clientFirstPanel.add(signUp);

        usernameText = new JTextField(5);
        usernameText.setPreferredSize(new Dimension(50, 10));
        clientFirstPanel.add(new JLabel("<html><font color='white'>Username: </font></html>", JLabel.LEFT));
        clientFirstPanel.add(usernameText);
        passwordText = new JTextField(5);
        passwordText.setPreferredSize(new Dimension(50, 10));
        clientFirstPanel.add(new JLabel("<html><font color='white'>Password: </font></html>", JLabel.LEFT));
        clientFirstPanel.add(passwordText);

        clientLabel = new JLabel("", JLabel.LEFT);
        clientLabel.setPreferredSize(new Dimension(333,70));
        clientLabel.setText("<html><font color='FFFFFF'></font></html>");
        clientFirstPanel.add(clientLabel);

        clientMainPanel.add(clientFirstPanel);
        clientFrame.add(clientMainPanel);

        clientAccessFrame = new JFrame("Client Page");
        clientAccessFrame.setSize(700, 900);
        clientAccessFrame.setLayout(new GridLayout(1, 1));
        clientAccessPanel = new JPanel();
        clientAccessPanel.setBackground(Color.darkGray);
        clientAccessFrame.add(clientAccessPanel);

        clientAccessFilerPanel = new JPanel();
        clientAccessFilerPanel.setBackground(Color.darkGray);

        filter = this.createSimpleButton("FILTER");
        clientAccessFilerPanel.add(filter);

        titleText = new JTextField(5);
        titleText.setPreferredSize(new Dimension(300, 30));
        clientAccessFilerPanel.add(new JLabel("<html><font color='white'>Title: </font></html>", JLabel.LEFT));
        clientAccessFilerPanel.add(titleText);
        ratingText = new JTextField(5);
        ratingText.setPreferredSize(new Dimension(100, 30));
        clientAccessFilerPanel.add(new JLabel("<html><font color='white'>Rating: </font></html>", JLabel.LEFT));
        clientAccessFilerPanel.add(ratingText);
        caloriesText = new JTextField(5);
        caloriesText.setPreferredSize(new Dimension(100, 30));
        clientAccessFilerPanel.add(new JLabel("<html><font color='white'>Calories: </font></html>", JLabel.LEFT));
        clientAccessFilerPanel.add(caloriesText);
        proteinText = new JTextField(5);
        proteinText.setPreferredSize(new Dimension(100, 30));
        clientAccessFilerPanel.add(new JLabel("<html><font color='white'>Protein: </font></html>", JLabel.LEFT));
        clientAccessFilerPanel.add(proteinText);
        fatText = new JTextField(5);
        fatText.setPreferredSize(new Dimension(100, 30));
        clientAccessFilerPanel.add(new JLabel("<html><font color='white'>Fat: </font></html>", JLabel.LEFT));
        clientAccessFilerPanel.add(fatText);
        sodiumText = new JTextField(5);
        sodiumText.setPreferredSize(new Dimension(100, 30));
        clientAccessFilerPanel.add(new JLabel("<html><font color='white'>Sodium: </font></html>", JLabel.LEFT));
        clientAccessFilerPanel.add(sodiumText);
        priceText = new JTextField(5);
        priceText.setPreferredSize(new Dimension(100, 30));
        clientAccessFilerPanel.add(new JLabel("<html><font color='white'>Price: </font></html>", JLabel.LEFT));
        clientAccessFilerPanel.add(priceText);

        clientAccessFrame.add(clientAccessFilerPanel);

        clientAccessOrderPanel = new JPanel();
        clientAccessOrderPanel.setBackground(Color.darkGray);

        addItem = this.createSimpleButton("ADD ITEM");
        placeOrder = this.createSimpleButton("PLACE ORDER");
        clientAccessOrderPanel.add(addItem);
        clientAccessOrderPanel.add(placeOrder);

        addItemText = new JTextField(5);
        addItemText.setPreferredSize(new Dimension(300, 30));
        clientAccessOrderPanel.add(new JLabel("<html><font color='white'>Add Item By Title: </font></html>", JLabel.LEFT));
        clientAccessOrderPanel.add(addItemText);

        clientAccessFrame.add(clientAccessOrderPanel);

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

    public void showClientRegistrationPanel() {
        logIn.setActionCommand("logIn");
        signUp.setActionCommand("signUp");
        logIn.addActionListener(new Client(this));
        signUp.addActionListener(new Client(this));
        clientFrame.setVisible(true);
    }

    public void showClientPanel() {
        filter.setActionCommand("filter");
        filter.addActionListener(new Client(this));
        addItem.setActionCommand("addItem");
        addItem.addActionListener(new Client(this));
        placeOrder.setActionCommand("placeOrder");
        placeOrder.addActionListener(new Client(this));
        clientAccessFrame.setVisible(true);
    }
}
