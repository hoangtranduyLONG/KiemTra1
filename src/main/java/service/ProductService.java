package service;


import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductService {
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2006?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String query = "select * from product;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                products.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return products;
    }

    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        String query = "select * from product where name like ?;";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFind = rs.getString("name");
                int price = rs.getInt("price");

                products.add(new Product(id, nameFind, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> findByPrice(int start, int end) {
        List<Product> products = new ArrayList<>();
        String query = "select * from product where price between ? and ?;";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(String.valueOf(start)));
            preparedStatement.setInt(2, Integer.parseInt(String.valueOf(end)));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFind = rs.getString("name");
                int price = rs.getInt("price");
                products.add(new Product(id, nameFind, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
