package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.connection()){
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()){
                String SQL = "CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";
                statement.executeUpdate(SQL);
                connection.commit();
            } catch (SQLException rollback) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.connection();) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement();){
                String SQL = "DROP TABLE IF EXISTS User";
                statement.executeUpdate(SQL);
                connection.commit();
            } catch (SQLException rollback){
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.connection();) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement();){
                String SQL = "INSERT INTO User (name, lastName, age) VALUES (" + "'" + name + "', " + "'" + lastName + "', " + age + ")";
                statement.executeUpdate(SQL);
                connection.commit();
            } catch (SQLException rollback){
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.connection();) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement();){
            String SQL = "DELETE  FROM User WHERE id = " + id;
            statement.executeUpdate(SQL);
            connection.commit();
            } catch (SQLException rollback){
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.connection().createStatement()) {
            String SQL = "SELECT * FROM User";
            ResultSet set = statement.executeQuery(SQL);
            while (set.next()) {
                User user = new User();

                user.setId(set.getInt("id"));
                user.setName(set.getString("name"));
                user.setLastName(set.getString("lastName"));
                user.setAge(set.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.connection();) {
            connection.setAutoCommit(false);
            try {
            Statement statement = connection.createStatement();
            String SQL = "TRUNCATE TABLE User";
            statement.executeUpdate(SQL);
            connection.commit();
            } catch (SQLException rollback){
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
