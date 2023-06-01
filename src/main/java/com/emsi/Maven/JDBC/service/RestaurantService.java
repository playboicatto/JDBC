package com.emsi.Maven.JDBC.service;

import java.util.List;

import com.emsi.Maven.JDBC.dao.RestaurantDao;
import com.emsi.Maven.JDBC.dao.impl.RestaurantDaoImp;
import com.emsi.Maven.JDBC.entities.Restaurant;

public class RestaurantService {
	private RestaurantDao restaurantDao = new RestaurantDaoImp();

	public List<Restaurant> findAll() {
		return restaurantDao.findAll();
	}

	public void saveOrUpdate(Restaurant restaurant) {
		if (restaurant.getPatente() == null) {
			restaurantDao.insert(restaurant);
		} else {
			restaurantDao.update(restaurant);
		}
	}

	public void save(Restaurant restaurant) {
		restaurantDao.insert(restaurant);
	}

	public void update(Restaurant restaurant) {
		restaurantDao.update(restaurant);
	}

	public void remove(Restaurant restaurant) {
		restaurantDao.deleteById(restaurant.getPatente());
	}

	public boolean validateUser(String username, String password) {
		return restaurantDao.validateUser(username, password);
	}
}
