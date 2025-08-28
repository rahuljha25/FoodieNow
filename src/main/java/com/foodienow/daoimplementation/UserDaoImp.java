// User Registration DAOImp layer
package com.foodienow.daoimplementation;

import com.foodienow.dao.UserDao;
import com.foodienow.util.DBConnection;
import com.foodienow.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImp implements UserDao {

    //  Add User
    public boolean addUser(User user) {
        String sql = "INSERT INTO user (username, email, phone, password, role, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole()); // default "Customer"
            stmt.setString(6, user.getAddress());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Get User by ID
    @Override
    public User getUser(int userId) {
        Connection connection = DBConnection.getConnection();
        String GET_USER_BY_ID = "SELECT * FROM user WHERE userId = ?";
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setInt(1, userId);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                int id = res.getInt("userId");
                String username = res.getString("username");
                String password = res.getString("password");
                String email = res.getString("email");
                String phone = res.getString("phone");
                String address = res.getString("address");
                String role = res.getString("role");
                Date createdDate = res.getDate("createdDate");
                Date lastLoginDate = res.getDate("lastLoginDate");

                user = new User(id, username, password, email, phone, address, role, createdDate, lastLoginDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Update User
    @Override
    public void updateUser(User user) {
        Connection connection = DBConnection.getConnection();
        String UPDATE_USER = "UPDATE user SET username = ?, password = ?, phone = ?, address = ? WHERE userId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setInt(5, user.getUserId());

            int n = preparedStatement.executeUpdate();
            System.out.println(n + " row/s updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete User
    @Override
    public void deleteUser(int userId) {
        String DELETE_USER = "DELETE FROM user WHERE userId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

            preparedStatement.setInt(1, userId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " row/s deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  Get All Users
    @Override
    public List<User> getAllusers() {
        Connection connection = DBConnection.getConnection();
        ArrayList<User> userList = new ArrayList<>();
        String GET_ALL_USERS = "SELECT * FROM user";

        try (Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(GET_ALL_USERS)) {

            while (res.next()) {
                int userId = res.getInt("userId");
                String username = res.getString("username");
                String email = res.getString("email");
                String phone = res.getString("phone");
                String address = res.getString("address");
                String role = res.getString("role");
                Date createdDate = res.getDate("createdDate");
                Date lastLoginDate = res.getDate("lastLoginDate");

                User user = new User(userId, username, null, email, phone, address, role, createdDate, lastLoginDate);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    //  Login Authentication
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        String SQL = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("role"),
                    resultSet.getDate("create_date"),
                    resultSet.getDate("lastdate_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //  Get User by Email
    @Override
    public User getUser(String email) {
        Connection connection = DBConnection.getConnection();
        String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                int id = res.getInt("userId");
                String username = res.getString("username");
                String password = res.getString("password");
                String phone = res.getString("phone");
                String address = res.getString("address");
                String role = res.getString("role");
                Date createdDate = res.getDate("createdDate");
                Date lastLoginDate = res.getDate("lastLoginDate");

                user = new User(id, username, password, email, phone, address, role, createdDate, lastLoginDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
