package com.emsi.Maven.JDBC.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emsi.Maven.JDBC.dao.RestaurantDao;
import com.emsi.Maven.JDBC.entities.Restaurant;

public class RestaurantDaoImp implements RestaurantDao {

	private Connection conn = DB.getConnection();

	@Override
	public void insert(Restaurant restaurant) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO restaurant (nom, adresse, ville, ca, gerant, heureOuverture, heureFermeture, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setString(3, restaurant.getVille());
			ps.setDouble(4, restaurant.getCa());
			ps.setString(5, restaurant.getGerant());
			ps.setString(6, restaurant.getHeureOuverture());
			ps.setString(7, restaurant.getHeureFermeture());
			ps.setString(8, restaurant.getType());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					restaurant.setPatente(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			System.err.println("Problème d'insertion d'un restaurant");
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Restaurant restaurant) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(
					"UPDATE restaurant SET nom = ?, adresse = ?, ville = ?, ca = ?, gerant = ?, heureOuverture = ?, heureFermeture = ?, type = ? WHERE patente = ?");

			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setString(3, restaurant.getVille());
			ps.setDouble(4, restaurant.getCa());
			ps.setString(5, restaurant.getGerant());
			ps.setString(6, restaurant.getHeureOuverture());
			ps.setString(7, restaurant.getHeureFermeture());
			ps.setString(8, restaurant.getType());
			ps.setInt(9, restaurant.getPatente());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Problème de mise à jour d'un restaurant");
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM restaurant WHERE patente = ?");

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Problème de suppression d'un restaurant");
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Restaurant findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM restaurant WHERE patente = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Restaurant restaurant = new Restaurant();

				restaurant.setPatente(rs.getInt("patente"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setVille(rs.getString("ville"));
				restaurant.setCa(rs.getDouble("ca"));
				restaurant.setGerant(rs.getString("gerant"));
				restaurant.setHeureOuverture(rs.getString("heure_ouverture"));
				restaurant.setHeureFermeture(rs.getString("heure_fermeture"));
				restaurant.setType(rs.getString("type"));

				return restaurant;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("Problème de requête pour trouver un restaurant");
			return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Restaurant> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM restaurant");
			rs = ps.executeQuery();

			List<Restaurant> listRestaurant = new ArrayList<>();

			while (rs.next()) {
				Restaurant restaurant = new Restaurant();

				restaurant.setPatente(rs.getInt("patente"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setVille(rs.getString("ville"));
				restaurant.setCa(rs.getDouble("ca"));
				restaurant.setGerant(rs.getString("gerant"));
				restaurant.setHeureOuverture(rs.getString("heureOuverture"));
				restaurant.setHeureFermeture(rs.getString("heureFermeture"));
				restaurant.setType(rs.getString("type"));

				listRestaurant.add(restaurant);
			}

			return listRestaurant;
		} catch (SQLException e) {
			System.err.println("Problème de requête pour sélectionner un restaurant");
			return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public boolean validateUser(String username, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println("Problème de requête pour trouver un utilisateur");
			return false;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
}
