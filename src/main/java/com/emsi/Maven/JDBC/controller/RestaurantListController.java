package com.emsi.Maven.JDBC.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.emsi.Maven.JDBC.dao.impl.DB;
import com.emsi.Maven.JDBC.entities.Restaurant;
import com.emsi.Maven.JDBC.service.RestaurantService;
import com.emsi.Maven.JDBC.service.RestaurantServiceImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class RestaurantListController {
    @FXML
    public TableView<Restaurant> restaurantTableView;

    @FXML
    public Label usernameLabel;

    public RestaurantService restaurantService = new RestaurantService();
    public static Restaurant tableRestaurants = null;

    public void initialize() {
        setupDatabaseConnection();
        setupTableSelectionListener();
        usernameLabel.setText("Welcome " + LoginController.usernameTF + " !");
    }

    public void setupDatabaseConnection() {
        DB.getConnection();
    }

    @FXML
    public void createRestaurant() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/emsi/Maven/JDBC/View/RestaurantForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(600);
        stage.setMaxWidth(500);
        stage.setMaxHeight(600);
        stage.setTitle("Restaurant Creation");
        stage.setOnHidden(event -> {
            readRestaurant();
        });
        stage.show();
    }

    @FXML
    public void readRestaurant() {
        restaurantTableView.getItems().clear();
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantService.findAll();
        for (Restaurant restaurant : restaurants) {
            restaurantTableView.getItems().add(restaurant);
        }
    }

    @FXML
    public void updateRestaurant() throws IOException {
        Restaurant selectedRestaurant = restaurantTableView.getSelectionModel().getSelectedItem();
        if (selectedRestaurant == null) {
            System.out.println("Please select a restaurant to update.");
            showErrorMessage("error", "Invalid Selection", "Please select a restaurant to update.");
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/emsi/Maven/JDBC/View/RestaurantForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(600);
        stage.setMaxWidth(500);
        stage.setMaxHeight(600);
        stage.setTitle("Restaurant Modification");
        stage.setOnHidden(event -> {
            readRestaurant();
        });
        stage.show();

    }

    @FXML
    public void deleteRestaurant() {
        Restaurant selectedRestaurant = restaurantTableView.getSelectionModel().getSelectedItem();

        if (selectedRestaurant == null) {
            System.out.println("Please select a restaurant to delete.");
            showErrorMessage("error", "Invalid Selection", "Please select a restaurant to delete.");
            return;
        }
        restaurantService.remove(selectedRestaurant);
        System.out.println("Restaurant deleted successfully!");
        showErrorMessage("information", "Valid input", "Restaurant deleted successfully!");
        readRestaurant(); // Refresh the table view after deleting a restaurant
    }

    public void setupTableSelectionListener() {
        restaurantTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Restaurant>() {
            @Override
            public void changed(ObservableValue<? extends Restaurant> observable, Restaurant oldValue, Restaurant newValue) {
                if (newValue != null) {
                    tableRestaurants = newValue;
                } else {
                    tableRestaurants = null;
                }
            }
        });
    }

    @FXML
    public void importData() {
        String filePath = Restaurant.path + "restaurantInputData.xlsx";
        RestaurantServiceImpl restaurantServiceImpl = new RestaurantServiceImpl();
        restaurantServiceImpl.importRestaurantsFromExcel(filePath);
        System.out.println("Restaurants imported successfully!");
        showErrorMessage("information", "Valid input", "Restaurants imported successfully!");
        readRestaurant(); // Refresh the table view after importing restaurants
    }

    @FXML
    public void exportData() {
        RestaurantServiceImpl restaurantServiceImpl = new RestaurantServiceImpl();
        restaurantServiceImpl.exportRestaurantsToExcel(Restaurant.path + "restaurantOutputData.xlsx");
        System.out.println("Restaurants exported successfully!");
        showErrorMessage("information", "Valid input", "Restaurants exported successfully!");
        readRestaurant(); // Refresh the table view after exporting the restaurants
    }

    public void showErrorMessage(String type, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (type.equals("error")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else if (type.equals("information")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else if (type.equals("warning")) {
            alert = new Alert(Alert.AlertType.WARNING);
        } else if (type.equals("confirm")) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
