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
import java.sql.SQLException;

public class ViewCustomers extends JFrame {
    private JButton backButton;

    private JTable viewCustomerTable;
    private DefaultTableModel viewCustomerTableModel;

    ViewCustomers() throws SQLException {
        // ----Initialize the Search Customer Window---------------
        setSize(500, 425);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("View Customers");
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

        String[] colHeaders = new String[] { "Customer ID", "QTY", "Amount" };
        viewCustomerTableModel = new DefaultTableModel(colHeaders, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false
                return false;
            }
        };
        viewCustomerTable = new JTable(viewCustomerTableModel);
        viewCustomerTable.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < colHeaders.length; i++) {
            viewCustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane bestInCustomerPane = new JScrollPane(viewCustomerTable);
        JPanel tableContainerPanel = new JPanel(new BorderLayout());
        tableContainerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        tableContainerPanel.add(bestInCustomerPane, BorderLayout.CENTER);

        loadViewCustomers();

        add("Center", tableContainerPanel);
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

    private void loadViewCustomers() {
        try {
            ResultSet allCustomerData = ShopController.getAllCustomers();
            viewCustomerTableModel.setRowCount(0);
            while (allCustomerData.next()) {
                Object rowData[] = { allCustomerData.getString(1),
                        allCustomerData.getString(2),
                        allCustomerData.getString(3) };

                viewCustomerTableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
