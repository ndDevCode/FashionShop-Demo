package view;

import controller.ShopController;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import model.Orders;

public class PlaceOrder extends JFrame {
    private JButton backButton;
    private JButton placeButton;

    private JTextField customerIdTextField;
    private JTextField sizeTextField;
    private JTextField quantityTextField;

    private JLabel orderIdTextValue;
    private JLabel amountValueLabel;

    public PlaceOrder() throws ClassNotFoundException, SQLException {

        // ----Initialize the Place Order Window---------------
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Place Order");
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

        // ----Setting up the place button------------------
        placeButton = new JButton("Place");
        placeButton.setFont(new Font("", Font.BOLD, 18));
        placeButton.setPreferredSize(new Dimension(95, 35));
        placeButton.setBackground(new Color(0, 153, 153));
        placeButton.setForeground(new Color(255, 255, 255));
        placeButton.setOpaque(true);
        placeButton.setFocusable(false);

        JPanel placeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        placeButtonPanel.add(placeButton);

        add("South", placeButtonPanel);

        // ----Setting up the Body Content----------------------

        // Label Font Size font
        Font labelFont = new Font("", Font.BOLD, 15);
        Font textFieldFont = new Font("", Font.BOLD, 15);

        // Main Body structure
        JPanel mainFormContainer = new JPanel(null);

        // Initialize components in main container
        String nextOrderId = ShopController.getNextOrderId();
        JLabel orderIDLabel = new JLabel("Order ID :");
        orderIDLabel.setFont(labelFont);
        orderIdTextValue = new JLabel(nextOrderId);
        orderIdTextValue.setFont(labelFont);

        JLabel customerIdLabel = new JLabel("Customer ID :");
        customerIdLabel.setFont(labelFont);
        customerIdTextField = new JTextField(10);
        customerIdTextField.setFont(textFieldFont);

        JLabel sizeLabel = new JLabel("Size :");
        JLabel sizeTypes = new JLabel(" (XS/S/M/L/XL/XXL)");
        sizeTypes.setFont(labelFont);
        sizeLabel.setFont(labelFont);
        sizeTextField = new JTextField(10);
        sizeTextField.setFont(textFieldFont);

        JLabel quantityLabel = new JLabel("QTY :");
        quantityLabel.setFont(labelFont);
        quantityTextField = new JTextField(10);
        quantityTextField.setFont(textFieldFont);

        JLabel amountLabel = new JLabel("Amount :");
        amountLabel.setFont(labelFont);
        amountValueLabel = new JLabel("");
        amountValueLabel.setFont(labelFont);

        // Arranging the location of the components
        orderIDLabel.setBounds(20, 20, 120, 50);
        orderIdTextValue.setBounds(150, 20, 100, 50);

        customerIdLabel.setBounds(20, 70, 120, 50);
        customerIdTextField.setBounds(150, 80, 180, 30);

        sizeLabel.setBounds(20, 120, 120, 50);
        sizeTextField.setBounds(150, 130, 180, 30);
        sizeTypes.setBounds(340, 120, 138, 50);

        quantityLabel.setBounds(20, 170, 120, 50);
        quantityTextField.setBounds(150, 180, 180, 30);

        amountLabel.setBounds(20, 230, 120, 50);
        amountValueLabel.setBounds(150, 230, 100, 50);

        // Adding the form components to the mainFormContainer
        mainFormContainer.add(orderIDLabel);
        mainFormContainer.add(orderIdTextValue);
        mainFormContainer.add(customerIdLabel);
        mainFormContainer.add(customerIdTextField);
        mainFormContainer.add(sizeLabel);
        mainFormContainer.add(sizeTextField);
        mainFormContainer.add(sizeTypes);
        mainFormContainer.add(quantityLabel);
        mainFormContainer.add(quantityTextField);
        mainFormContainer.add(amountLabel);
        mainFormContainer.add(amountValueLabel);
        add("Center", mainFormContainer);

        // ---- Setting up Button Click Events--------------------

        JFrame placeOrderFrame = this;
        // Back Button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FashionShopHome().setVisible(true);
            }

        });

        // Customer ID textField Event
        customerIdTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (customerIdTextField.getText().length() != 0) {
                        if (customerIdTextField.getText().charAt(0) != '0' ||
                                customerIdTextField.getText().length() != 10) {

                            JOptionPane.showMessageDialog(placeOrderFrame,
                                    "Invalid Customer ID, Enter Again...",
                                    "Invalid Input",
                                    JOptionPane.ERROR_MESSAGE);
                            customerIdTextField.setText("");
                            customerIdTextField.requestFocus();
                        }
                    }
                } catch (Exception exception) {
                    // exception handle
                }

            }

        });

        customerIdTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        // Size textfield Event
        sizeTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!(sizeTextField.getText().equals("XS") ||
                        sizeTextField.getText().equals("S") ||
                        sizeTextField.getText().equals("M") ||
                        sizeTextField.getText().equals("L") ||
                        sizeTextField.getText().equals("XL") ||
                        sizeTextField.getText().equals("XXL") ||
                        sizeTextField.getText().equals(""))) {
                    JOptionPane.showMessageDialog(placeOrderFrame,
                            "Invalid Size, Enter Again...",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    sizeTextField.setText("");
                    sizeTextField.requestFocus();
                }
            }

        });

        // QTY textfield Event
        quantityTextField.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    int qty = 0;
                    if (quantityTextField.getText().length() != 0) {
                        qty = Integer.parseInt(quantityTextField.getText());
                    }

                    double price = 0.00;
                    switch (sizeTextField.getText()) {
                        case "XS":
                            price = 600.0;
                            break;
                        case "S":
                            price = 800.0;
                            break;
                        case "M":
                            price = 900.0;
                            break;
                        case "L":
                            price = 1000.0;
                            break;
                        case "XL":
                            price = 1100.0;
                            break;
                        case "XXL":
                            price = 1200.0;
                            break;
                    }

                    double amount = qty * price;
                    amountValueLabel.setText(String.format("%.2f", amount));

                } catch (Exception ex) {
                    amountValueLabel.setText("");
                    JOptionPane.showMessageDialog(placeOrderFrame,
                            "Invalid Quantity, Enter Again...",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    quantityTextField.setText("");
                    quantityTextField.requestFocus();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) || c == 48) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

        });

        placeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (orderIdTextValue.getText().length() == 10 &&
                            sizeTextField.getText().length() > 0 &&
                            quantityTextField.getText().length() >= 1 &&
                            customerIdTextField.getText().length() == 10) {

                        Orders order = new Orders(orderIdTextValue.getText(),
                                customerIdTextField.getText(),
                                sizeTextField.getText(),
                                Integer.parseInt(quantityTextField.getText()),
                                Double.parseDouble(amountValueLabel.getText()));

                        ShopController.placeOrder(order);

                        JOptionPane.showMessageDialog(placeOrderFrame,
                                "Order Placed!",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    customerIdTextField.requestFocus();
                    orderIdTextValue.setText(ShopController.getNextOrderId());
                    sizeTextField.setText("");
                    quantityTextField.setText("");
                    customerIdTextField.setText("");
                    amountValueLabel.setText("");

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(placeOrderFrame,
                            "Invalid Details!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });


    }
}
