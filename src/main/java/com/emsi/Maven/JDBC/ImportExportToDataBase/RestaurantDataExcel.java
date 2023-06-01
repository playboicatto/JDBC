package com.emsi.Maven.JDBC.ImportExportToDataBase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emsi.Maven.JDBC.entities.Restaurant;
import com.emsi.Maven.JDBC.service.RestaurantService;

public class RestaurantDataExcel {
    private RestaurantService restaurantService = new RestaurantService();

    public void importDataFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            List<Restaurant> restaurantList = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // skip the header row
                    continue;
                }

                Restaurant restaurant = new Restaurant();
                Cell patenteCell = row.getCell(0);
                Cell nomCell = row.getCell(1);
                Cell adresseCell = row.getCell(2);
                Cell villeCell = row.getCell(3);
                Cell caCell = row.getCell(4);
                Cell gerantCell = row.getCell(5);
                Cell heureOuvertureCell = row.getCell(6);
                Cell heureFermetureCell = row.getCell(7);
                Cell typeCell = row.getCell(8);

                restaurant.setPatente((int) patenteCell.getNumericCellValue());
                restaurant.setNom(nomCell.getStringCellValue());
                restaurant.setAdresse(adresseCell.getStringCellValue());
                restaurant.setVille(villeCell.getStringCellValue());
                restaurant.setCa(caCell.getNumericCellValue());
                restaurant.setGerant(gerantCell.getStringCellValue());
                restaurant.setHeureOuverture(heureOuvertureCell.getStringCellValue());
                restaurant.setHeureFermeture(heureFermetureCell.getStringCellValue());
                restaurant.setType(typeCell.getStringCellValue());

                restaurantList.add(restaurant);
            }

            System.out.println("Importing Data From Excel is done!\nWaiting for saving to database...");
            for (Restaurant restaurant : restaurantList) {
                restaurantService.save(restaurant);
            }
            System.out.println("Saving Restaurants From Excel To Database is done!");
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataToExcel(String filePath) {
        List<Restaurant> restaurants = restaurantService.findAll();

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Restaurant Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Patente");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Adresse");
            headerRow.createCell(3).setCellValue("Ville");
            headerRow.createCell(4).setCellValue("CA");
            headerRow.createCell(5).setCellValue("Gerant");
            headerRow.createCell(6).setCellValue("Heure Ouverture");
            headerRow.createCell(7).setCellValue("Heure Fermeture");
            headerRow.createCell(8).setCellValue("Type");

            int rowIndex = 1;

            for (Restaurant restaurant : restaurants) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(restaurant.getPatente());
                dataRow.createCell(1).setCellValue(restaurant.getNom());
                dataRow.createCell(2).setCellValue(restaurant.getAdresse());
                dataRow.createCell(3).setCellValue(restaurant.getVille());
                dataRow.createCell(4).setCellValue(restaurant.getCa());
                dataRow.createCell(5).setCellValue(restaurant.getGerant());
                dataRow.createCell(6).setCellValue(restaurant.getHeureOuverture());
                dataRow.createCell(7).setCellValue(restaurant.getHeureFermeture());
                dataRow.createCell(8).setCellValue(restaurant.getType());
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            workbook.close();
            System.out.println("Exporting Data From Database To Excel is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
