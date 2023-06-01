package com.emsi.Maven.JDBC.controller;

import com.emsi.Maven.JDBC.dao.impl.DB;
import com.emsi.Maven.JDBC.entities.Restaurant;
import com.emsi.Maven.JDBC.service.RestaurantService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RestaurantFormController {
    @FXML
    public TextField nomTextField;
    @FXML
    public TextField adresseTextField;
    @FXML
    public TextField villeTextField;
    @FXML
    public TextField caTextField;
    @FXML
    public TextField gerantTextField;
    @FXML
    public TextField heureOuvertureTextField;
    @FXML
    public TextField heureFermetureTextField;
    @FXML
    public TextField typeTextField;
    @FXML
    public Button createUpdateButton;

    public RestaurantService restaurantService = new RestaurantService();

    public void initialize() {
        setupDatabaseConnection();
        if (RestaurantListController.tableRestaurants != null) {
            createUpdateButton.setText("Update");
            fillForm();
        } else {
            createUpdateButton.setText("Create");
        }
    }

    public void setupDatabaseConnection() {
        DB.getConnection();
    }

    @FXML
    public void createRestaurant() {
        if (RestaurantListController.tableRestaurants != null) {
            updateRestaurant();
            return;
        }
        if (nomTextField.getText().isEmpty() || adresseTextField.getText().isEmpty()
                || villeTextField.getText().isEmpty() || caTextField.getText().isEmpty()
                || gerantTextField.getText().isEmpty() || heureOuvertureTextField.getText().isEmpty()
                || heureFermetureTextField.getText().isEmpty() || typeTextField.getText().isEmpty()) {
            showErrorMessage("error", "Missing input", "Please enter all the required fields.");
            return;
        }
        String nom = nomTextField.getText();
        String adresse = adresseTextField.getText();
        String ville = villeTextField.getText();
        double ca = Double.parseDouble(caTextField.getText());
        String gerant = gerantTextField.getText();
        String heureOuverture = heureOuvertureTextField.getText();
        String heureFermeture = heureFermetureTextField.getText();
        String type = typeTextField.getText();

        Restaurant restaurant = new Restaurant(nom, adresse, ville, ca, gerant, heureOuverture, heureFermeture, type);
        restaurantService.save(restaurant);

        System.out.println("Restaurant inserted successfully!");
        showErrorMessage("information", "Valid input", "Restaurant inserted successfully!");
        Stage formStage = (Stage) nomTextField.getScene().getWindow();
        formStage.close();
    }

    @FXML
    public void fillForm() {
        Restaurant selectedRestaurant = RestaurantListController.tableRestaurants;
        nomTextField.setText(selectedRestaurant.getNom());
        adresseTextField.setText(selectedRestaurant.getAdresse());
        villeTextField.setText(selectedRestaurant.getVille());
        caTextField.setText(String.valueOf(selectedRestaurant.getCa()));
        gerantTextField.setText(selectedRestaurant.getGerant());
        heureOuvertureTextField.setText(selectedRestaurant.getHeureOuverture());
        heureFermetureTextField.setText(selectedRestaurant.getHeureFermeture());
        typeTextField.setText(selectedRestaurant.getType());
    }

    @FXML
    public void updateRestaurant() {
        if (nomTextField.getText().isEmpty() || adresseTextField.getText().isEmpty()
                || villeTextField.getText().isEmpty() || caTextField.getText().isEmpty()
                || gerantTextField.getText().isEmpty() || heureOuvertureTextField.getText().isEmpty()
                || heureFermetureTextField.getText().isEmpty() || typeTextField.getText().isEmpty()) {
            showErrorMessage("error", "Missing input", "Please enter all the required fields.");
            return;
        }
        Restaurant selectedRestaurant = RestaurantListController.tableRestaurants;
        if (selectedRestaurant == null) {
            System.out.println("Please select a restaurant to update.");
            showErrorMessage("error", "Invalid Selection", "Please select a restaurant to update.");
            return;
        }
        int id = selectedRestaurant.getPatente();
        String nom = nomTextField.getText();
        String adresse = adresseTextField.getText();
        String ville = villeTextField.getText();
        double ca = Double.parseDouble(caTextField.getText());
        String gerant = gerantTextField.getText();
        String heureOuverture = heureOuvertureTextField.getText();
        String heureFermeture = heureFermetureTextField.getText();
        String type = typeTextField.getText();

        Restaurant restaurant = new Restaurant(id, nom, adresse, ville, ca, gerant, heureOuverture, heureFermeture, type);
        restaurantService.update(restaurant);

        System.out.println("Restaurant updated successfully!");
        showErrorMessage("information", "Valid input", "Restaurant updated successfully!");
        Stage formStage = (Stage) nomTextField.getScene().getWindow();
        formStage.close();
    }

    @FXML
    public void clearRestaurant() {
        nomTextField.clear();
        adresseTextField.clear();
        villeTextField.clear();
        caTextField.clear();
        gerantTextField.clear();
        heureOuvertureTextField.clear();
        heureFermetureTextField.clear();
        typeTextField.clear();
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
