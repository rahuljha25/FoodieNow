//Restaurant DAO Interface 
package com.foodienow.dao;

import com.foodienow.model.Restaurant;
import java.util.List;

public interface RestaurantDao {
    void addRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(int restaurantId);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurantId);
    List<Restaurant> getAllRestaurants();
    List<Restaurant> searchRestaurants(String keyword);
}
