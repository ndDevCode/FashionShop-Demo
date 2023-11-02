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

public class ItemsByAmount extends JFrame {
    private JButton backButton;

    private JTable itemsByAmountTable;
    private DefaultTableModel itemsByAmountTableModel;

    ItemsByAmount() {
        // ----Initialize the Search Customer Window---------------
        setSize(500, 351);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Items By Amount");
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

        String[] colHeaders = new String[] { "Size", "QTY", "Amount" };
        itemsByAmountTableModel = new DefaultTableModel(colHeaders, 0){

            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        itemsByAmountTable = new JTable(itemsByAmountTableModel);
        itemsByAmountTable.setRowHeight(36);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < colHeaders.length; i++) {
            itemsByAmountTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        

        JScrollPane bestInCustomerPane = new JScrollPane(itemsByAmountTable);
        JPanel tableContainerPanel = new JPanel(new BorderLayout());
        tableContainerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        tableContainerPanel.add(bestInCustomerPane, BorderLayout.CENTER);

        loadItemsByAmount();

        add("Center", tableContainerPanel);
        
        
        // ---- Setting up Button Click Events--------------------

        // Back Button
        backButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewReports().setVisible(true);
            }

        });
    }

    private void loadItemsByAmount() {
        
        try {
            ResultSet bestInCustomers = ShopController.getItemsByAmount();
            itemsByAmountTableModel.setRowCount(0);
            while (bestInCustomers.next()) {
                Object rowData[] = { bestInCustomers.getString(1),
                        bestInCustomers.getString(2),
                        bestInCustomers.getString(3)
                     };

                itemsByAmountTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}