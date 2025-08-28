//User Registration & Retrieval DAO interface
package com.foodienow.dao;

import java.util.List;
import com.foodienow.model.User;

public interface UserDao 
{
boolean addUser(User user);
	
	User getUser(int userID);
	
	User getUser(String email);
	
	void updateUser(User user);
	
	void deleteUser(int userId);
	
	User getUserByEmailAndPassword(String email, String password);
     
	List<User> getAllusers();	

}
