package edu.gavrilov.repositories.security;

import edu.gavrilov.entity.security.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Класс-репозиторий для работы со списком пользователей в базе данных
 */
@Repository
public class UserDao {

    //Реквизиты для доступа к БД
    private static final String DB_URL = "jdbc:postgresql://ec2-54-247-123-231.eu-west-1.compute.amazonaws.com:5432/d39p92kdthmrkj";
    private static final String USERNAME = "kfkejvvdvdbgpg";
    private static final String PASSWORD = "947deb00f0e3e44e80ba73581cff634b7d3192ec9e3b47ded577e86e7f712a8b";

    /**
     * Метод для получения объекта класса "Пользователь" по имени пользователя
     * @param username - имя пользователя
     * @return User object
     */
    public User getUserByUsername(String username) {

        User user = new User();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, password from users WHERE username = \'" + username + "\'");

            while (rs.next()) {
                user.setLogin(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return user;

    }

    /**
     * Метод для получения уникального идентификатора пользователя по имени пользователя
     * @param username - имя пользователя
     * @return User object
     */
    public int getUserIdByUsername(String username) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        int id = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, username from users WHERE username = \'" + username + "\'");

            while (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return id;

    }

    /**
     * Метод для создания нового аккаунта по логину и паролю
     * @param username - логин
     * @param password - пароль
     * @return true or false
     */
    public boolean createNewAccount(String username, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO users (username, password) VALUES (\'" + username + "\',\'" + password + "\')");
            return true;

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return false;
        }

    }

}
