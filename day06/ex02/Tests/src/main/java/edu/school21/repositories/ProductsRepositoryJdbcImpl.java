package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM products")) {
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product(rs.getLong(1),
                        rs.getString(2), rs.getInt(3));
                list.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) {

        try (Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM products WHERE identifier = " + id)) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product product = new Product(rs.getLong(1),
                    rs.getString(2), rs.getInt(3));
                return Optional.of(product);
            }
            else {
                return Optional.empty();
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE products SET name = ?, price = ? WHERE identifier = ?")) {
            st.setString(1, product.getName());
            st.setInt(2, product.getPrice());
            st.setLong(3, product.getIdentifier());
            st.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO products(name, price) VALUES (?, ?)")) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM products WHERE identifier = " + id)) {
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}