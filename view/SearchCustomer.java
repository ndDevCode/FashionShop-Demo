package view;

import controller.ShopController;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SearchCustomer extends JFrame {
    private JButton backButton;
    private JButton searchButton;

    private JTextField searchField;

    private JLabel totalDisplayLabel;

    private JTable customerDetailsTable;
    private DefaultTableModel customerDTM;

    SearchCustomer() {

        // ----Initialize the Search Customer Window---------------
        setSize(500, 425);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Search Customer");
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

        // ----Setting up the main Container---------------
        JPanel mainContainerPanel = new JPanel(null);

        // Label font
        Font labelFont = new Font("", Font.BOLD, 15);

        // Setting up Components
        JLabel totalLabel = new JLabel("Total :");
        totalLabel.setFont(labelFont);

        totalDisplayLabel = new JLabel("");
        totalDisplayLabel.setFont(labelFont);

        String[] colHeaders = new String[] { "Size", "QTY", "Amount" };
        customerDTM = new DefaultTableModel(colHeaders, 0);
        customerDetailsTable = new JTable(customerDTM);
        customerDetailsTable.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        customerDetailsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        customerDetailsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        customerDetailsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane customerTablePane = new JScrollPane(customerDetailsTable);

        JLabel enterIdLabel = new JLabel("Enter Customer ID :");
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

        // Setting up the locations

        enterIdLabel.setBounds(20, 15, 150, 25);
        searchField.setBounds(180, 12, 180, 32);
        searchButton.setBounds(370, 12, 100, 31);

        customerTablePane.setBounds(45, 60, 400, 233);
        totalLabel.setBounds(55, 300, 100, 25);
        totalDisplayLabel.setBounds(330, 300, 100, 25);

        mainContainerPanel.add(enterIdLabel);
        mainContainerPanel.add(searchField);
        mainContainerPanel.add(searchButton);
        mainContainerPanel.add(customerTablePane);
        mainContainerPanel.add(totalLabel);
        mainContainerPanel.add(totalDisplayLabel);

        add("Center", mainContainerPanel);

        // ---- Setting up Button Click Events--------------------

        // Back Button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FashionShopHome().setVisible(true);
            }

        });

        // Text Field Validation
        searchField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        // Search Button

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                customerDTM.setRowCount(0);
                try {
                    if (searchField.getText().charAt(0) == '0' && searchField.getText().length() == 10) {
                        ResultSet customerData = ShopController.SearchCustomer(searchField.getText());
                        double total=0.0;
                        while (customerData.next()) {
                            Object rowData[] = { customerData.getString(1),
                                    customerData.getInt(2),
                                    customerData.getDouble(3) };
                            customerDTM.addRow(rowData);
                            total+=customerData.getDouble(3);
                        }
                        totalDisplayLabel.setText(String.format("%.2f",total));
                    }else{
                        JOptionPane.showMessageDialog(customerTablePane,
                            "Invalid Customer ID!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(customerTablePane,
                            "Invalid Customer ID!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
    }

}
