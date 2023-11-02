package view;

import controller.ShopController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AllCustomers extends JFrame {
    private JButton backButton;

    private JTable allCustomersTable;
    private DefaultTableModel allCustomersTableModel;

    AllCustomers() {
        // ----Initialize the Search Customer Window---------------
        setSize(700, 425);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("All Customers");
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

        String[] colHeaders = { "Customer ID", "XS", "S", "M", "L", "XL", "XXL", "Amount" };
        allCustomersTableModel = new DefaultTableModel(colHeaders, 0){

            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        
        allCustomersTable = new JTable(allCustomersTableModel);
        allCustomersTable.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < colHeaders.length; i++) {
            allCustomersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set the specific preferred width for columns 0 and 7
        
        allCustomersTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        allCustomersTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        
        JScrollPane allCustomerPane = new JScrollPane(allCustomersTable);
       
        int remainingWidth = (allCustomerPane.getWidth() - 250) / (colHeaders.length - 2);

       
        for (int i = 1; i < colHeaders.length - 1; i++) {
            allCustomersTable.getColumnModel().getColumn(i).setPreferredWidth(remainingWidth);
        }

        JPanel tableContainerPanel = new JPanel(new BorderLayout());
        tableContainerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        tableContainerPanel.add(allCustomerPane, BorderLayout.CENTER);

        loadAllCustomers();
        add(tableContainerPanel);
        
        // ---- Setting up Button Click Events--------------------

        // Back Button
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewReports().setVisible(true);
            }

        });

    }

   

    private void loadAllCustomers() {
        try {
            ResultSet bestInCustomers = ShopController.getAllCustomersBySize();
            allCustomersTableModel.setRowCount(0);
            while (bestInCustomers.next()) {
                Object rowData[] = { bestInCustomers.getString(1),
                        bestInCustomers.getString(2),
                        bestInCustomers.getString(3),
                        bestInCustomers.getString(4),
                        bestInCustomers.getString(5),
                        bestInCustomers.getString(6),
                        bestInCustomers.getString(7),
                        bestInCustomers.getString(8),
                     };

                allCustomersTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

}
