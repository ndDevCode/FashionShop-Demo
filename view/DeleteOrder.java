package view;

import controller.ShopController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOrder extends JFrame {

    private JButton backButton;
    private JButton searchButton;
    private JButton deleteOrderButton;

    private JTextField searchField;

    private JLabel customerIdDisplayLabel;
    private JLabel sizeDisplayLabel;
    private JLabel qtyDisplayLabel;
    private JLabel amountDisplayLabel;
    private JLabel statusDisplayLabel;

    DeleteOrder() {

        // ----Initialize the Delete Order Window---------------
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Order");
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

        // ----Setting up the main body--------------------

        // Main body panel
        JPanel mainContentPanel = new JPanel(null);

        // Label font
        Font labelFont = new Font("", Font.BOLD, 15);

        // Component initialization
        JLabel enterIdLabel = new JLabel("Enter Order ID :");
        enterIdLabel.setFont(labelFont);

        searchField = new JTextField(10);
        searchField.setFont(labelFont);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("", Font.BOLD, 18));
        searchButton.setPreferredSize(new Dimension(95, 35));
        searchButton.setBackground(new Color(0, 153, 153));
        searchButton.setForeground(new Color(255, 255, 255));
        searchButton.setOpaque(true);
        searchButton.setFocusable(false);

        JLabel customerIdLabel = new JLabel("Customer ID :");
        customerIdLabel.setFont(labelFont);
        customerIdDisplayLabel = new JLabel("");
        customerIdDisplayLabel.setFont(labelFont);

        JLabel sizeLabel = new JLabel("Size :");
        sizeLabel.setFont(labelFont);
        sizeDisplayLabel = new JLabel("");
        sizeDisplayLabel.setFont(labelFont);

        JLabel qtyLabel = new JLabel("QTY :");
        qtyLabel.setFont(labelFont);
        qtyDisplayLabel = new JLabel("");
        qtyDisplayLabel.setFont(labelFont);

        JLabel amountLabel = new JLabel("Amount :");
        amountLabel.setFont(labelFont);
        amountDisplayLabel = new JLabel("");
        amountDisplayLabel.setFont(labelFont);

        JLabel statusLabel = new JLabel("Status :");
        statusLabel.setFont(labelFont);
        statusDisplayLabel = new JLabel("");
        statusDisplayLabel.setFont(labelFont);

        deleteOrderButton = new JButton("Delete");
        deleteOrderButton.setFont(new Font("", Font.BOLD, 18));
        deleteOrderButton.setPreferredSize(new Dimension(95, 35));
        deleteOrderButton.setBackground(new Color(255, 0, 0));
        deleteOrderButton.setForeground(new Color(255, 255, 255));
        deleteOrderButton.setOpaque(true);
        deleteOrderButton.setFocusable(false);
        deleteOrderButton.setEnabled(false);

        // Arranging the locations of the compoenents
        enterIdLabel.setBounds(20, 15, 120, 25);
        searchField.setBounds(160, 12, 180, 32);
        searchButton.setBounds(360, 12, 100, 31);

        customerIdLabel.setBounds(20, 85, 120, 25);
        customerIdDisplayLabel.setBounds(160, 85, 120, 25);

        sizeLabel.setBounds(20, 120, 120, 25);
        sizeDisplayLabel.setBounds(160, 120, 120, 25);

        qtyLabel.setBounds(20, 155, 120, 25);
        qtyDisplayLabel.setBounds(160, 155, 120, 25);

        amountLabel.setBounds(20, 190, 120, 25);
        amountDisplayLabel.setBounds(160, 190, 120, 25);

        statusLabel.setBounds(20, 225, 120, 25);
        statusDisplayLabel.setBounds(160, 225, 120, 25);

        deleteOrderButton.setBounds(360, 270, 100, 31);

        // Adding the components to the main content panel
        mainContentPanel.add(enterIdLabel);
        mainContentPanel.add(searchField);
        mainContentPanel.add(searchButton);
        mainContentPanel.add(customerIdLabel);
        mainContentPanel.add(customerIdDisplayLabel);
        mainContentPanel.add(sizeLabel);
        mainContentPanel.add(sizeDisplayLabel);
        mainContentPanel.add(qtyLabel);
        mainContentPanel.add(qtyDisplayLabel);
        mainContentPanel.add(amountLabel);
        mainContentPanel.add(amountDisplayLabel);
        mainContentPanel.add(statusLabel);
        mainContentPanel.add(statusDisplayLabel);
        mainContentPanel.add(deleteOrderButton);
        add("Center", mainContentPanel);

        // ---- Setting up Button Click Events--------------------

        JFrame deleteOrderFrame = this;

        // Back Button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FashionShopHome().setVisible(true);
            }

        });

        // Search Button
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet orderData;
                try {
                    orderData = ShopController.searchOrder(searchField.getText());
                    if (orderData.next()) {
                        customerIdDisplayLabel.setText(orderData.getString(2));
                        sizeDisplayLabel.setText(orderData.getString(3));
                        qtyDisplayLabel.setText(Integer.toString(orderData.getInt(4)));
                        amountDisplayLabel.setText(Double.toString(orderData.getDouble(5)));
                        statusDisplayLabel.setText(orderData.getString(6));
                        deleteOrderButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(deleteOrderFrame,
                                "Invalid Order ID!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);

                        searchField.setText("");
                        customerIdDisplayLabel.setText("");
                        qtyDisplayLabel.setText("");
                        amountDisplayLabel.setText("");
                        sizeDisplayLabel.setText("");
                        statusDisplayLabel.setText("");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        // Delete Button

        deleteOrderButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showOptionDialog(deleteOrderFrame,
                        "Do you want to delete this order?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);

                try {
                    switch (selection) {
                        case 0:
                            boolean isDeleted = ShopController.deleteOrder(searchField.getText());
                            if(isDeleted){
                               JOptionPane.showMessageDialog(deleteOrderFrame,
                                    "Order Deleted",
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE); 
                            }else{
                                JOptionPane.showMessageDialog(deleteOrderFrame,
                                    "Unexpected Error Occured",
                                    "Error",
                                    JOptionPane.WARNING_MESSAGE); 
                            }
                            
                            break;
                        case 1:
                            break;
                    }

                    searchField.setText("");
                    customerIdDisplayLabel.setText("");
                    qtyDisplayLabel.setText("");
                    amountDisplayLabel.setText("");
                    sizeDisplayLabel.setText("");
                    statusDisplayLabel.setText("");
                    deleteOrderButton.setEnabled(false);
                } catch (Exception ex) {
                   ex.printStackTrace();
                }

            }

        });

    }
}
