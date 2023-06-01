package com.emsi.Maven.JDBC.ImportExportToDataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.emsi.Maven.JDBC.entities.Restaurant;
import com.emsi.Maven.JDBC.service.RestaurantService;

public class RestaurantDataTxt {
    private RestaurantService restaurantService = new RestaurantService();

    public void importDataFromTextFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<Restaurant> restaurantList = new ArrayList<>();
            String readLine = br.readLine();

            while (readLine != null) {
                String[] restaurantData = readLine.split("\\|");

                Restaurant restaurant = new Restaurant();
                restaurant.setPatente(Integer.parseInt(restaurantData[0].trim()));
                restaurant.setNom(restaurantData[1].trim());
                restaurant.setAdresse(restaurantData[2].trim());
                restaurant.setVille(restaurantData[3].trim());
                restaurant.setCa(Double.parseDouble(restaurantData[4].trim()));
                restaurant.setGerant(restaurantData[5].trim());
                restaurant.setHeureOuverture(restaurantData[6].trim());
                restaurant.setHeureFermeture(restaurantData[7].trim());
                restaurant.setType(restaurantData[8].trim());

                restaurantList.add(restaurant);
                readLine = br.readLine();
            }

            System.out.println("Importing Data From Text is done!\nWaiting for saving to the database...");
            for (Restaurant restaurant : restaurantList) {
                restaurantService.save(restaurant);
            }
            System.out.println("Saving Restaurants From Text To Database is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToTextFile(String filePath) {
        List<Restaurant> restaurants = restaurantService.findAll();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Restaurant restaurant : restaurants) {
                String restaurantData = restaurant.getPatente() + " | " + restaurant.getNom() + " | " +
                        restaurant.getAdresse() + " | " + restaurant.getVille() + " | " + restaurant.getCa() +
                        " | " + restaurant.getGerant() + " | " + restaurant.getHeureOuverture() + " | " +
                        restaurant.getHeureFermeture() + " | " + restaurant.getType();

                bw.write(restaurantData);
                bw.newLine();
            }

            System.out.println("Exporting Data From Database To Text is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
