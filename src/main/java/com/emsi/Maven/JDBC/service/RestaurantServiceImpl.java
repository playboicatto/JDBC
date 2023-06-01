package com.emsi.Maven.JDBC.service;

import com.emsi.Maven.JDBC.ImportExportToDataBase.RestaurantDataExcel;
import com.emsi.Maven.JDBC.ImportExportToDataBase.RestaurantDataTxt;

public class RestaurantServiceImpl extends RestaurantService {

    private RestaurantDataExcel restaurantDataExcel;
    private RestaurantDataTxt restaurantDataTxt;

    public RestaurantServiceImpl() {
        restaurantDataExcel = new RestaurantDataExcel();
        restaurantDataTxt = new RestaurantDataTxt();
    }

    public void importRestaurantsFromExcel(String filePath) {
        restaurantDataExcel.importDataFromExcel(filePath);
    }

    public void exportRestaurantsToExcel(String filePath) {
        restaurantDataExcel.exportDataToExcel(filePath);
    }

    public void importRestaurantsFromTextFile(String filePath) {
        restaurantDataTxt.importDataFromTextFile(filePath);
    }

    public void exportRestaurantsToTextFile(String filePath) {
        restaurantDataTxt.exportDataToTextFile(filePath);
    }

}
