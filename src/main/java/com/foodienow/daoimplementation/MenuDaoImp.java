//Menu DAO Implementation
package com.foodienow.daoimplementation;

import com.foodienow.dao.MenuDao;
import com.foodienow.model.Menu;
import com.foodienow.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImp implements MenuDao {

    @Override
    public Menu getMenu(int menuId) {
        Menu menu = null;
        String sql = "SELECT * FROM menu WHERE menuId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, menuId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                menu = mapResultSetToMenu(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        String sql = "UPDATE menu SET name=?, description=?, price=?, imageUrl=?, isAvailable=?, restaurantId=? WHERE menuId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, menu.getName());
            stmt.setString(2, menu.getDescription());
            stmt.setDouble(3, menu.getPrice());
            stmt.setString(4, menu.getImageUrl());
            stmt.setBoolean(5, menu.isAvailable());
            stmt.setInt(6, menu.getRestaurantId());
            stmt.setInt(7, menu.getMenuId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        String sql = "DELETE FROM menu WHERE menuId=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, menuId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getAllMenusByResturant(int restaurantId) {   
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE restaurant_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, restaurantId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                menus.add(mapResultSetToMenu(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    @Override
    public void addMenu(Menu menu) {
        String sql = "INSERT INTO menu (name, description, price, imageUrl, isAvailable, restaurantId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, menu.getName());
            stmt.setString(2, menu.getDescription());
            stmt.setDouble(3, menu.getPrice());
            stmt.setString(4, menu.getImageUrl());
            stmt.setBoolean(5, menu.isAvailable());
            stmt.setInt(6, menu.getRestaurantId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Menu mapResultSetToMenu(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setMenuId(rs.getInt("menuId"));
        menu.setName(rs.getString("name"));
        menu.setDescription(rs.getString("description"));
        menu.setPrice(rs.getDouble("price"));
        menu.setImageUrl(rs.getString("imageUrl"));
        menu.setAvailable(rs.getBoolean("isAvailable"));
        menu.setRestaurantId(rs.getInt("restaurant_id"));
        return menu;
    }
}
