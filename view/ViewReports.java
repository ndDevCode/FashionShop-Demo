package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ViewReports extends JFrame {
    
    private JButton backButton;
    private JButton viewCustomersButton;
    private JButton bestInCustomersButton;
    private JButton allCustomersButton;
    private JButton itemsByQtyButton;
    private JButton itemsByAmountButton;
    private JButton ordersByAmountButton;
    private JButton allOrdersButton;
    
    ViewReports(){

        // ----Initialize the Reports Window---------------
        setSize(700, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("View Reports");
        ImageIcon shopLogo = new ImageIcon("assets/appLogo.png");
        setIconImage(shopLogo.getImage());
        setResizable(false);

        // ----Setting up the back button-------------------

        backButton = new JButton("Back");
        backButton.setFont(new Font("", Font.BOLD, 18));
        backButton.setPreferredSize(new Dimension(95, 35));
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setOpaque(true);
        backButton.setFocusable(false);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);
        add("North", backButtonPanel);

        // ---- Main Button Container----------------------

        JPanel mainButtonsContainerPanel =  new JPanel(null);

        // ---- Initializing the Buttons-------------------

        Font buttonFont =  new Font("", Font.BOLD, 14);

        viewCustomersButton = new JButton("View Customers");
        viewCustomersButton.setFont(buttonFont);
        viewCustomersButton.setPreferredSize(new Dimension(95, 35));
        viewCustomersButton.setBackground(new Color(72, 224, 0));
        viewCustomersButton.setForeground(new Color(255, 255, 255));
        viewCustomersButton.setOpaque(true);
        viewCustomersButton.setFocusable(false);

        bestInCustomersButton = new JButton("Best in Customers");
        bestInCustomersButton.setFont(buttonFont);
        bestInCustomersButton.setPreferredSize(new Dimension(95, 35));
        bestInCustomersButton.setBackground(new Color(72, 224, 0));
        bestInCustomersButton.setForeground(new Color(255, 255, 255));
        bestInCustomersButton.setOpaque(true);
        bestInCustomersButton.setFocusable(false);

        allCustomersButton = new JButton("All Customers");
        allCustomersButton.setFont(buttonFont);
        allCustomersButton.setPreferredSize(new Dimension(95, 35));
        allCustomersButton.setBackground(new Color(72, 224, 0));
        allCustomersButton.setForeground(new Color(255, 255, 255));
        allCustomersButton.setOpaque(true);
        allCustomersButton.setFocusable(false);

        itemsByQtyButton = new JButton("Categorized By QTY");
        itemsByQtyButton.setFont(buttonFont);
        itemsByQtyButton.setPreferredSize(new Dimension(95, 35));
        itemsByQtyButton.setBackground(new Color(7,49,162));
        itemsByQtyButton.setForeground(new Color(255, 255, 255));
        itemsByQtyButton.setOpaque(true);
        itemsByQtyButton.setFocusable(false);

        itemsByAmountButton = new JButton("Categorized By Amount");
        itemsByAmountButton.setFont(buttonFont);
        itemsByAmountButton.setPreferredSize(new Dimension(95, 35));
        itemsByAmountButton.setBackground(new Color(7,49,162));
        itemsByAmountButton.setForeground(new Color(255, 255, 255));
        itemsByAmountButton.setOpaque(true);
        itemsByAmountButton.setFocusable(false);

        ordersByAmountButton = new JButton("Orders By Amount");
        ordersByAmountButton.setFont(buttonFont);
        ordersByAmountButton.setPreferredSize(new Dimension(95, 35));
        ordersByAmountButton.setBackground(new Color(109,109,109));
        ordersByAmountButton.setForeground(new Color(255, 255, 255));
        ordersByAmountButton.setOpaque(true);
        ordersByAmountButton.setFocusable(false);

        allOrdersButton = new JButton("All Orders");
        allOrdersButton.setFont(buttonFont);
        allOrdersButton.setPreferredSize(new Dimension(95, 35));
        allOrdersButton.setBackground(new Color(109,109,109));
        allOrdersButton.setForeground(new Color(255, 255, 255));
        allOrdersButton.setOpaque(true);
        allOrdersButton.setFocusable(false);

        // Arranging the Buttons location

        viewCustomersButton.setBounds(15, 30, 200, 35);
        bestInCustomersButton.setBounds(15, 75, 200, 35);
        allCustomersButton.setBounds(15, 120, 200, 35);
        itemsByQtyButton.setBounds(245, 48, 200, 35);
        itemsByAmountButton.setBounds(245, 93, 200, 35);
        ordersByAmountButton.setBounds(475, 48, 200, 35);
        allOrdersButton.setBounds(475, 93, 200, 35);

        // Adding Buttons to the main button container

        mainButtonsContainerPanel.add(viewCustomersButton);
        mainButtonsContainerPanel.add(bestInCustomersButton);
        mainButtonsContainerPanel.add(allCustomersButton);
        mainButtonsContainerPanel.add(itemsByQtyButton);
        mainButtonsContainerPanel.add(itemsByAmountButton);
        mainButtonsContainerPanel.add(ordersByAmountButton);
        mainButtonsContainerPanel.add(allOrdersButton);

        add("Center", mainButtonsContainerPanel);

        // ---- Setting up Button Click Events--------------------

       
        // Back Button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FashionShopHome().setVisible(true);
            }

        });

        // View Customer Button
        viewCustomersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new ViewCustomers().setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            
        });

        // Best in Customer Button
        bestInCustomersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BestInCustomers().setVisible(true);
            }
            
        });

        // All Customers Button
        allCustomersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AllCustomers().setVisible(true);
            }
            
        });

        // Items By Quantity Button
        itemsByQtyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ItemsByQty().setVisible(true);
            }
            
        });

        // Items By Amount Butoon
        itemsByAmountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ItemsByAmount().setVisible(true);
            }
        });

        // All Orders Button
        allOrdersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               new AllOrders().setVisible(true);
            }
            
        }); 

        // Orders By Amount
        ordersByAmountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OrdersByAmount().setVisible(true);
            }
            
        });
    }
}
