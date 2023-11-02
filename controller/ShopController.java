package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.*;

public class ShopController {

    private static Connection connection;
    private static Statement statement;

    private ShopController(){}
    
    static {
        try {
            ShopController.connection = DBConnection.getInstance().getConnection();
            ShopController.statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean placeOrder(Orders order) throws SQLException, ClassNotFoundException {
        int updatedRowCount = statement.executeUpdate("INSERT INTO orders VALUES('" + order.getOrderId() + "'," +
                "'" + order.getCustomerId() + "','" + order.getSize() + "','" + order.getOrderQty() + "'," +
                "'" + order.getAmount() + "','" + order.getOrderStatus() + "')");

        if(updatedRowCount == 1){
            return true;
        }
        return false;
    }

    public static String getNextOrderId() throws SQLException { // generate orderID
        
        ResultSet result = statement.executeQuery("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        result.next();

        String id = result.getString("orderId");
        int c = Integer.parseInt(id.substring(4, 10));
        return "ODR#" + String.format("%06d", ++c);
    }

    public static ResultSet SearchCustomer(String customerId) throws SQLException {

        ResultSet customerData = statement
                .executeQuery("SELECT size, SUM(quantity), SUM(amount) FROM orders WHERE customerId='" + customerId
                        + "' GROUP BY size ORDER BY SUM(amount) DESC");
        return customerData;

    }

    public static ResultSet searchOrder(String orderId) throws SQLException {
        ResultSet orderData = statement.executeQuery("SELECT * FROM orders WHERE orderId='"+orderId+"'");
        return orderData;
    }

    public static boolean deleteOrder(String orderId) throws SQLException {
        int deletedRowCount = statement.executeUpdate("DELETE FROM orders WHERE orderId='"+orderId+"';");
        if(deletedRowCount == 1){
            return true;
        }
        return false;
    }

    public static boolean setOrderStatus(int status, String orderId) throws SQLException {
        int changedRowCount = statement.executeUpdate("UPDATE orders SET status="+status+" WHERE orderId='"+orderId+"';");
        if(changedRowCount == 1){
            return true;
        }
        return false;
    }

    public static ResultSet getAllCustomers() throws SQLException{
        ResultSet customerData = statement
                .executeQuery("SELECT customerId,SUM(quantity),SUM(amount) FROM orders GROUP BY customerId");
        return customerData;
    }

    public static ResultSet getBestInCustomers() throws SQLException {
        ResultSet customerData = statement
                .executeQuery
                ("SELECT customerId,SUM(quantity),SUM(amount) AS totAmount FROM orders GROUP BY customerId ORDER BY totAmount DESC");
        return customerData;
    }

    public static ResultSet getAllCustomersBySize() throws SQLException {
        ResultSet customerData = statement
                .executeQuery
                ("SELECT customerId,\r\n" + //
                        "       SUM(CASE WHEN size = 'XS' THEN quantity ELSE 0 END) AS XS,\r\n" + //
                        "       SUM(CASE WHEN size = 'S' THEN quantity ELSE 0 END) AS S,\r\n" + //
                        "       SUM(CASE WHEN size = 'M' THEN quantity ELSE 0 END) AS M,\r\n" + //
                        "       SUM(CASE WHEN size = 'L' THEN quantity ELSE 0 END) AS L,\r\n" + //
                        "       SUM(CASE WHEN size = 'XL' THEN quantity ELSE 0 END) AS XL,\r\n" + //
                        "       SUM(CASE WHEN size = 'XXL' THEN quantity ELSE 0 END) AS XXL,\r\n" + //
                        "       SUM(amount) AS totAmount\r\n" + //
                        "FROM orders\r\n" + //
                        "GROUP BY customerId;");
                        
        return customerData;
    }

    public static ResultSet getItemsByQty() throws SQLException {
        ResultSet customerData = statement
                .executeQuery
                ("SELECT size, SUM(quantity) AS totQty, SUM(amount) FROM orders GROUP BY size ORDER BY totQty DESC");
        return customerData;
    }

    public static ResultSet getItemsByAmount() throws SQLException {
        ResultSet customerData = statement
                .executeQuery
                ("SELECT size, SUM(quantity), SUM(amount) AS totAmount FROM orders GROUP BY size ORDER BY totAmount DESC");
        return customerData;
    }

    public static ResultSet getAllOrders() throws SQLException {
        ResultSet customerData = statement
                .executeQuery
                ("SELECT * FROM orders ORDER BY orderId DESC");
        return customerData;
    }

    public static ResultSet getAllOrdersByAmount() throws SQLException {
        ResultSet customerData = statement
                .executeQuery
                ("SELECT * FROM orders ORDER BY amount DESC");
        return customerData;
    }
}
