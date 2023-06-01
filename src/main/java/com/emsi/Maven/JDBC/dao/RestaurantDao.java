package com.emsi.Maven.JDBC.dao;

import java.util.List;

import com.emsi.Maven.JDBC.entities.Restaurant;

public interface RestaurantDao {
	void insert(Restaurant restaurant);

	void update(Restaurant restaurant);

	void deleteById(Integer id);

	Restaurant findById(Integer id);

	List<Restaurant> findAll();

	boolean validateUser(String username, String password);
}
