package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker() {

    }

    public SqlTracker(Connection connection) {
        this.cn = connection;
    }


    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement pstmt = cn.prepareStatement("INSERT INTO items (name, desciption) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDesciption());
            pstmt.execute();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        int row = 0;
        try (PreparedStatement pstmt = cn.prepareStatement("UPDATE items SET name = ?, desciption = ? WHERE id = ?")) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDesciption());
            pstmt.setLong(3, id);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row > 0;
    }

    @Override
    public boolean delete(int id) {
        int row = 0;
        try (PreparedStatement pstmt = cn.prepareStatement("DELETE FROM items WHERE id = ?")) {
            pstmt.setLong(1, id);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row > 0;
    }

    @Override
    public List<Item> findAll() {

        List<Item> result = new ArrayList<>();
        try (Statement pstmt = cn.createStatement()) {
            var resultSet = pstmt.executeQuery("SELECT id, name, desciption FROM items");
            while (resultSet.next()) {
                var item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getInt("id"));
                item.setDesciption(resultSet.getString("desciption"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void findAll(Consumer<Item> consumer) {
        try (Statement pstmt = cn.createStatement()) {
            var resultSet = pstmt.executeQuery("SELECT id, name, desciption FROM items");
            while (resultSet.next()) {
                var item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getInt("id"));
                item.setDesciption(resultSet.getString("desciption"));
                consumer.accept(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement pstmt = cn.prepareStatement("SELECT id, name, desciption FROM items WHERE name = ?")) {
            pstmt.setString(1, key);
            var resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                var item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getInt("id"));
                item.setDesciption(resultSet.getString("desciption"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (PreparedStatement pstmt = cn.prepareStatement("SELECT id, name, desciption FROM items WHERE id = ?")) {
            pstmt.setLong(1, id);
            var resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                result = new Item(resultSet.getString("name"));
                result.setId(resultSet.getInt("id"));
                result.setDesciption(resultSet.getString("desciption"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}