package delivery.presentationLayer;

import delivery.controller.Administrator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdministratorGUI {

    public JFrame adminFrame, adminAccessFrame;

    public JPanel adminMainPanel, adminFirstPanel, adminAccessPanel, adminAccessTablePanel, adminAccessReportsPanel;

    public JButton logIn, signUp, addProduct, editProduct, deleteProduct, viewProducts,
                   firstReport, secondReport, thirdReport, forthReport, addProductToComposite, compositeProducts;

    public JTextField usernameText, passwordText, productTitleText, productRatingText,
                      productCaloriesText, productProteinText, productFatText, productSodiumText, productPriceText,
                      startHour, endHour, oftenProducts, highOrders, day;

    public JLabel adminLabel;

    public AdministratorGUI() { GUI(); }

    private void GUI() {

        adminFrame = new JFrame("Admin");
        adminFrame.setSize(700, 900);
        adminFrame.setLayout(new GridLayout(1, 1));
        adminMainPanel = new JPanel();
        adminMainPanel.setBackground(Color.darkGray);
        adminFirstPanel = new JPanel();
        adminFirstPanel.setBackground(Color.darkGray);

        GridLayout adminRegistration = new GridLayout(2,1);
        adminFirstPanel.setLayout(adminRegistration);
        adminFirstPanel.setPreferredSize(new Dimension(400,200));
        logIn = this.createSimpleButton("LOG IN");
        signUp = this.createSimpleButton("SIGN UP");
        adminFirstPanel.add(logIn);
        adminFirstPanel.add(signUp);

        usernameText = new JTextField(5);
        usernameText.setPreferredSize(new Dimension(50, 10));
        adminFirstPanel.add(new JLabel("<html><font color='white'>Username: </font></html>", JLabel.LEFT));
        adminFirstPanel.add(usernameText);
        passwordText = new JTextField(5);
        passwordText.setPreferredSize(new Dimension(50, 10));
        adminFirstPanel.add(new JLabel("<html><font color='white'>Password: </font></html>", JLabel.LEFT));
        adminFirstPanel.add(passwordText);

        adminLabel = new JLabel("", JLabel.LEFT);
        adminLabel.setPreferredSize(new Dimension(333,70));
        adminLabel.setText("<html><font color='FFFFFF'></font></html>");
        adminFirstPanel.add(adminLabel);

        adminMainPanel.add(adminFirstPanel);
        adminFrame.add(adminMainPanel);

        adminAccessFrame = new JFrame("Admin Page");
        adminAccessFrame.setSize(700, 900);
        adminAccessFrame.setLayout(new GridLayout(1, 1));

        adminAccessPanel = new JPanel();
        adminAccessPanel.setBackground(Color.darkGray);

        GridLayout adminOp = new GridLayout(9,1);
        addProduct = this.createSimpleButton("add new product");
        editProduct = this.createSimpleButton("edit product");
        deleteProduct = this.createSimpleButton("delete product");
        viewProducts = this.createSimpleButton("show products");
        addProductToComposite = this.createSimpleButton("add product to composite");
        compositeProducts = this.createSimpleButton("composite");
        adminAccessPanel.add(addProduct);
        adminAccessPanel.add(editProduct);
        adminAccessPanel.add(deleteProduct);
        adminAccessPanel.add(viewProducts);
        adminAccessPanel.add(addProductToComposite);
        adminAccessPanel.add(compositeProducts);

        productTitleText = new JTextField(5);
        adminAccessPanel.add(new JLabel("<html><font color='white'>Title: </font></html>", JLabel.LEFT));
        adminAccessPanel.add(productTitleText);
        productRatingText = new JTextField(5);
        adminAccessPanel.add(new JLabel("<html><font color='white'>Rating: </font></html>", JLabel.LEFT));
        adminAccessPanel.add(productRatingText);
        productProteinText = new JTextField(5);
        adminAccessPanel.add(new JLabel("<html><font color='white'>Protein: </font></html>", JLabel.LEFT));
        adminAccessPanel.add(productProteinText);
        productCaloriesText = new JTextField(5);
        adminAccessPanel.add(new JLabel("<html><font color='white'>Calories: </font></html>", JLabel.LEFT));
        adminAccessPanel.add(productCaloriesText);
        productFatText = new JTextField(5);
        adminAccessPanel.add(new JLabel("<html><font color='white'>Fat: </font></html>", JLabel.LEFT));
        adminAccessPanel.add(productFatText);
        productSodiumText = new JTextField(5);
        adminAccessPanel.add(new JLabel("<html><font color='white'>Sodium: </font></html>", JLabel.LEFT));
        adminAccessPanel.add(productSodiumText);
        productPriceText = new JTextField(5);
        adminAccessPanel.add(new JLabel("<html><font color='white'>Price: </font></html>", JLabel.LEFT));
        adminAccessPanel.add(productPriceText);
        adminAccessPanel.setLayout(adminOp);

        adminAccessTablePanel = new JPanel();
        adminAccessTablePanel.setBackground(Color.darkGray);

        adminAccessReportsPanel = new JPanel();
        adminAccessReportsPanel.setBackground(Color.darkGray);

        GridLayout adminReports = new GridLayout(9,1);
        firstReport = this.createSimpleButton("Report 1");
        secondReport = this.createSimpleButton("Report 2");
        thirdReport = this.createSimpleButton("Report 3");
        forthReport = this.createSimpleButton("Report 4");
        adminAccessReportsPanel.add(firstReport);
        adminAccessReportsPanel.add(secondReport);
        adminAccessReportsPanel.add(thirdReport);
        adminAccessReportsPanel.add(forthReport);
        adminAccessReportsPanel.setLayout(adminReports);

        startHour = new JTextField(5);
        adminAccessReportsPanel.add(new JLabel("<html><font color='white'>start hour: </font></html>", JLabel.LEFT));
        adminAccessReportsPanel.add(startHour);
        endHour = new JTextField(5);
        adminAccessReportsPanel.add(new JLabel("<html><font color='white'>end hour: </font></html>", JLabel.LEFT));
        adminAccessReportsPanel.add(endHour);
        oftenProducts = new JTextField(5);
        adminAccessReportsPanel.add(new JLabel("<html><font color='white'>minimum number of orders: </font></html>", JLabel.LEFT));
        adminAccessReportsPanel.add(oftenProducts);
        highOrders = new JTextField(5);
        adminAccessReportsPanel.add(new JLabel("<html><font color='white'>minimum value of order: </font></html>", JLabel.LEFT));
        adminAccessReportsPanel.add(highOrders);
        day = new JTextField(5);
        adminAccessReportsPanel.add(new JLabel("<html><font color='white'>Introduce a day (dd-mm-yyyy): </font></html>", JLabel.LEFT));
        adminAccessReportsPanel.add(day);

        adminAccessFrame.add(adminAccessPanel);
        adminAccessFrame.add(adminAccessTablePanel);
        adminAccessFrame.add(adminAccessReportsPanel);
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

    public void showAdminRegistrationPanel() {
        logIn.setActionCommand("logIn");
        signUp.setActionCommand("signUp");
        logIn.addActionListener(new Administrator(this));
        signUp.addActionListener(new Administrator(this));
        adminFrame.setVisible(true);
    }

    public void showAdminPanel() {
        addProduct.setActionCommand("addProduct");
        editProduct.setActionCommand("editProduct");
        deleteProduct.setActionCommand("deleteProduct");
        viewProducts.setActionCommand("viewProducts");
        addProductToComposite.setActionCommand("addProductToComposite");
        compositeProducts.setActionCommand("compositeProducts");
        firstReport.setActionCommand("firstReport");
        secondReport.setActionCommand("secondReport");
        thirdReport.setActionCommand("thirdReport");
        forthReport.setActionCommand("forthReport");
        addProduct.addActionListener(new Administrator(this));
        editProduct.addActionListener(new Administrator(this));
        deleteProduct.addActionListener(new Administrator(this));
        viewProducts.addActionListener(new Administrator(this));
        addProductToComposite.addActionListener(new Administrator(this));
        compositeProducts.addActionListener(new Administrator(this));
        firstReport.addActionListener(new Administrator(this));
        secondReport.addActionListener(new Administrator(this));
        thirdReport.addActionListener(new Administrator(this));
        forthReport.addActionListener(new Administrator(this));
        adminAccessFrame.setVisible(true);
    }
}
