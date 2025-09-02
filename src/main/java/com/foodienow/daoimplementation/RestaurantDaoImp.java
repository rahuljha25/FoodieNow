//Used for Restaurant feature to fetch restaurant details
package com.foodienow.daoimplementation;

import com.foodienow.dao.RestaurantDao;
import com.foodienow.model.Restaurant;
import com.foodienow.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImp implements RestaurantDao {

    @Override
    public void addRestaurant(Restaurant restaurant) {
        String sql = "INSERT INTO restaurant (name, cuisine, address, rating, image_url, delivery_time, is_active, admin_user_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, restaurant.getName());
            stmt.setString(2, restaurant.getCuisine());
            stmt.setString(3, restaurant.getAddress());
            stmt.setDouble(4, restaurant.getRating());
            stmt.setString(5, restaurant.getImageUrl());
            stmt.setInt(6, restaurant.getDeliveryTime());
            stmt.setBoolean(7, restaurant.isActive());
            stmt.setInt(8, restaurant.getAdminUserId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        Restaurant restaurant = null;
        String sql = "SELECT * FROM restaurant WHERE restaurant_id = ?";  // Fixed column name

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, restaurantId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                restaurant = mapResultSetToRestaurant(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        String sql = "UPDATE restaurant SET name=?, cuisine=?, address=?, rating=?, image_url=?, delivery_time=?, is_active=?, admin_user_id=? WHERE restaurant_id=?";  // ✅ Fixed

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, restaurant.getName());
            stmt.setString(2, restaurant.getCuisine());
            stmt.setString(3, restaurant.getAddress());
            stmt.setDouble(4, restaurant.getRating());
            stmt.setString(5, restaurant.getImageUrl());
            stmt.setInt(6, restaurant.getDeliveryTime());
            stmt.setBoolean(7, restaurant.isActive());
            stmt.setInt(8, restaurant.getAdminUserId());
            stmt.setInt(9, restaurant.getRestaurantId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        String sql = "DELETE FROM restaurant WHERE restaurant_id = ?";  //  Fixed

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, restaurantId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Restaurant> searchRestaurants(String keyword) {
        List<Restaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurant WHERE is_active = 1 AND name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToRestaurant(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurant WHERE is_active = 1;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToRestaurant(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private Restaurant mapResultSetToRestaurant(ResultSet rs) throws SQLException {
        Restaurant r = new Restaurant();
        r.setRestaurantId(rs.getInt("restaurant_id")); // Consistent
        r.setName(rs.getString("name"));
        r.setCuisine(rs.getString("cuisine"));
        r.setAddress(rs.getString("address"));
        r.setRating(rs.getDouble("rating"));
        r.setImageUrl(rs.getString("image_url")); 
        r.setDeliveryTime(rs.getInt("delivery_time")); 
        r.setActive(rs.getBoolean("is_active")); 
        r.setAdminUserId(rs.getInt("admin_user_id")); 
        return r;
    }
}
