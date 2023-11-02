package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FashionShopHome extends JFrame {
    private JButton search;
    private JButton status;
    private JButton reports;
    private JButton delete;
    private JButton placeOrder;

    public FashionShopHome() {

        // ----Initialize the home page window---------------
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Fashion Shop");
        ImageIcon shopLogo = new ImageIcon("assets/appLogo.png");
        setIconImage(shopLogo.getImage());
        setResizable(false);

        // ----Setting up the Header Title-------------------
        JLabel mainTitleLabel = new JLabel("Fashion Shop");
        mainTitleLabel.setFont(new Font("", Font.BOLD, 40));
        mainTitleLabel.setBackground(new Color(0, 0, 255));
        mainTitleLabel.setForeground(new Color(255, 255, 255));
        mainTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainTitleLabel.setOpaque(true);
        add("North", mainTitleLabel);

        // ----Setting up the Footer text--------------------
        JLabel footerNoteLabel = new JLabel("Copyright © iCET 2023");
        footerNoteLabel.setFont(new Font("", Font.BOLD, 15));
        footerNoteLabel.setPreferredSize(new Dimension(100, 50));
        footerNoteLabel.setHorizontalAlignment(JLabel.CENTER);
        footerNoteLabel.setVerticalAlignment(JLabel.CENTER);
        add("South", footerNoteLabel);

        // ----Setting up the Right image--------------------
        ImageIcon shopFrontImage = new ImageIcon("assets/homeImage.png");
        JLabel frontImageContainer = new JLabel(shopFrontImage, JLabel.CENTER);
        JPanel frontImagePanel = new JPanel(new BorderLayout());
        frontImagePanel.setBorder(new EmptyBorder(0, 0, 0, 30));
        frontImagePanel.add("Center", frontImageContainer);
        add("East", frontImagePanel);

        // ----Setting up the Option Buttons--------------------

        // Set Dimention and font for top buttons
        Dimension btnSize = new Dimension(190, 45);
        Font topButtonFont = new Font("", Font.BOLD, 15);

        // Initializing the top buttons
        search = new JButton("Search");
        search.setFocusable(false);
        search.setPreferredSize(btnSize);
        search.setFont(topButtonFont);

        status = new JButton("Status");
        status.setPreferredSize(btnSize);
        status.setFocusable(false);
        status.setFont(topButtonFont);

        reports = new JButton("Reports");
        reports.setFocusable(false);
        reports.setPreferredSize(btnSize);
        reports.setFont(topButtonFont);

        delete = new JButton("Delete");
        delete.setFocusable(false);
        delete.setPreferredSize(btnSize);
        delete.setFont(topButtonFont);

        // Initializing the place order button
        placeOrder = new JButton("Place Order");
        placeOrder.setFocusable(false);
        placeOrder.setBackground(new Color(0, 204, 203));
        placeOrder.setFont(new Font("", Font.BOLD, 24));
        placeOrder.setPreferredSize(new Dimension(190, 70));

        // Setting up the top button panel
        JPanel topButtonPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        topButtonPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        topButtonPanel.add(search);
        topButtonPanel.add(status);
        topButtonPanel.add(reports);
        topButtonPanel.add(delete);

        // Setting up the place order button panel
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomButtonPanel.setBorder(new EmptyBorder(0, 0, 35, 0));
        bottomButtonPanel.add(placeOrder);

        // Adding the top and bottom panels to the main button panel
        JPanel allButtonPanel = new JPanel(new BorderLayout());
        allButtonPanel.add("North", topButtonPanel);
        allButtonPanel.add("South", bottomButtonPanel);

        // adding the main button panel to the main application structure
        add("West", allButtonPanel);

        // ---- Setting up Button click Events-------------------------

        // Place Order Button
        
        placeOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new PlaceOrder().setVisible(true);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        // Search Button

        JFrame mainFrame = this;
        search.addActionListener(new ActionListener() {
            String[] options = { "Search Customer", "Search Order", "Cancel" };

            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showOptionDialog(
                        mainFrame,
                        "Please Select the option",
                        "Search Options",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        "1");

                switch (selection) {
                    case 0:
                        dispose();
                        new SearchCustomer().setVisible(true);
                        break;
                    case 1:
                        dispose();
                        new SearchOrder().setVisible(true);
                        break;
                }
            }
        });

        // Delete Button
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DeleteOrder().setVisible(true);
            }

        });

        // Status Button
        status.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ChangeOrderStatus().setVisible(true);   
            }
            
        });

        // Reports Button
        reports.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewReports().setVisible(true);   
            }
            
        });


    }

}