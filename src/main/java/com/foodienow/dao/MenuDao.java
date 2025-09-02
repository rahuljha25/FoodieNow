//Menu DAO Interface
package com.foodienow.dao;

import com.foodienow.model.Menu;
import java.util.List;

public interface MenuDao {
	Menu getMenu(int menuId);
    void updateMenu(Menu menu);
    void deleteMenu(int menuId);
    List<Menu> getAllMenusByResturant(int restaurantId);
    void addMenu(Menu menu);
   
}
