package com.example.proiect_java;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseApp extends Application {

    private TextField updateID;

    private TextField updatedNume;
    private TextField updatedPrenume;
    private TextField updatedCnp;
    private TextField updatedNumarTelefon;
    private Button updateButton;
    private TextField ID;
    private TextField nume;
    private TextField prenume;
    private TextField cnp;
    private TextField numarTelefon;
    private TextField deleteID;
    private TableView<Client> clientTableView;


    private TextField magazinID;
    private TextField numeMagazin;
    private TextField locatie;
    private TextField tipProduse;
    private Button addMagazinButton;
    private TextField deleteMagazinID;
    private Button deleteMagazinButton;
    private TextField updateID_magazin;
    private TextField updatedNumeMagazin;
    private TextField updatedLocatieMagazin;
    private TextField updatedTipProduse;
    private TableView<Magazin> magazinTableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Database App");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // aici incepe client
        Label idLabel = new Label("ID:");
        GridPane.setConstraints(idLabel, 0, 0);

        ID = new TextField();
        GridPane.setConstraints(ID, 1, 0);

        Label numeLabel = new Label("Nume:");
        GridPane.setConstraints(numeLabel, 0, 1);

        nume = new TextField();
        GridPane.setConstraints(nume, 1, 1);

        Label prenumeLabel = new Label("Prenume:");
        GridPane.setConstraints(prenumeLabel, 0, 2);

        prenume = new TextField();
        GridPane.setConstraints(prenume, 1, 2);

        Label cnpLabel = new Label("CNP:");
        GridPane.setConstraints(cnpLabel, 0, 3);

        cnp = new TextField();
        GridPane.setConstraints(cnp, 1, 3);

        Label numarTelefonLabel = new Label("Numar Telefon:");
        GridPane.setConstraints(numarTelefonLabel, 0, 4);

        numarTelefon = new TextField();
        GridPane.setConstraints(numarTelefon, 1, 4);

        Button addButton = new Button("Add to Database");
        addButton.setOnAction(e -> addToDatabase());
        GridPane.setConstraints(addButton, 1, 5);

        Label deleteIdLabel = new Label("ID to delete:");
        GridPane.setConstraints(deleteIdLabel, 0, 6);

        deleteID = new TextField();
        GridPane.setConstraints(deleteID, 1, 6);

        Button deleteButton = new Button("Delete from Database");
        deleteButton.setOnAction(e -> deleteFromDatabase());
        GridPane.setConstraints(deleteButton, 1, 7);

        Label updateIdLabel = new Label("ID to update:");
        GridPane.setConstraints(updateIdLabel, 2, 0);

        updateID = new TextField();
        GridPane.setConstraints(updateID, 3, 0);

        Label updatedNumeLabel = new Label("Updated Nume:");
        GridPane.setConstraints(updatedNumeLabel, 2, 1);

        updatedNume = new TextField();
        GridPane.setConstraints(updatedNume, 3, 1);

        Label updatedPrenumeLabel = new Label("Updated Prenume:");
        GridPane.setConstraints(updatedPrenumeLabel, 2, 2);

        updatedPrenume = new TextField();
        GridPane.setConstraints(updatedPrenume, 3, 2);

        Label updatedCnpLabel = new Label("Updated CNP:");
        GridPane.setConstraints(updatedCnpLabel, 2, 3);

        updatedCnp = new TextField();
        GridPane.setConstraints(updatedCnp, 3, 3);

        Label updatedNumarTelefonLabel = new Label("Updated Numar Telefon:");
        GridPane.setConstraints(updatedNumarTelefonLabel, 2, 4);

        updatedNumarTelefon = new TextField();
        GridPane.setConstraints(updatedNumarTelefon, 3, 4);

        updateButton = new Button("Update Record");
        updateButton.setOnAction(e -> updateRecord());
        GridPane.setConstraints(updateButton, 3, 5);


        clientTableView = new TableView<>();
        TableColumn<Client, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idClienti"));
        TableColumn<Client, String> numeColumn = new TableColumn<>("Nume");
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        TableColumn<Client, String> prenumeColumn = new TableColumn<>("Prenume");
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        TableColumn<Client, String> cnpColumn = new TableColumn<>("CNP");
        cnpColumn.setCellValueFactory(new PropertyValueFactory<>("cnp"));
        TableColumn<Client, String> numarTelefonColumn = new TableColumn<>("Numar Telefon");
        numarTelefonColumn.setCellValueFactory(new PropertyValueFactory<>("numarTelefon"));

        clientTableView.getColumns().addAll(idColumn, numeColumn, prenumeColumn, cnpColumn, numarTelefonColumn);


        // aici incepe magazin
        Label magazinIdLabel = new Label("Magazin ID:");
        GridPane.setConstraints(magazinIdLabel, 4, 0);

        magazinID = new TextField();
        GridPane.setConstraints(magazinID, 5, 0);

        Label numeMagazinLabel = new Label("Nume Magazin:");
        GridPane.setConstraints(numeMagazinLabel, 4, 1);

        numeMagazin = new TextField();
        GridPane.setConstraints(numeMagazin, 5, 1);

        Label locatieLabel = new Label("Locatie:");
        GridPane.setConstraints(locatieLabel, 4, 2);

        locatie = new TextField();
        GridPane.setConstraints(locatie, 5, 2);

        Label tipProduseLabel = new Label("Tip Produse:");
        GridPane.setConstraints(tipProduseLabel, 4, 3);

        tipProduse = new TextField();
        GridPane.setConstraints(tipProduse, 5, 3);

        addMagazinButton = new Button("Add Magazin to Database");
        addMagazinButton.setOnAction(e -> addMagazinToDatabase());
        GridPane.setConstraints(addMagazinButton, 5, 4);

        Label deleteMagazinIdLabel = new Label("Magazin ID to delete:");
        GridPane.setConstraints(deleteMagazinIdLabel, 4, 5);

        deleteMagazinID = new TextField();
        GridPane.setConstraints(deleteMagazinID, 5, 5);

        deleteMagazinButton = new Button("Delete Magazin from Database");
        deleteMagazinButton.setOnAction(e -> deleteMagazinFromDatabase());
        GridPane.setConstraints(deleteMagazinButton, 5, 6);


        Label updateIdMagazinLabel = new Label("Magazin ID to update:");
        GridPane.setConstraints(updateIdMagazinLabel, 6, 0);

        updateID_magazin = new TextField();
        GridPane.setConstraints(updateID_magazin, 7, 0);

        Label updatedNumeMagazinLabel = new Label("Updated Nume Magazin:");
        GridPane.setConstraints(updatedNumeMagazinLabel, 6, 1);

        updatedNumeMagazin = new TextField();
        GridPane.setConstraints(updatedNumeMagazin, 7, 1);

        Label updatedLocatieMagazinLabel = new Label("Updated Locatie Magazin:");
        GridPane.setConstraints(updatedLocatieMagazinLabel, 6, 2);

        updatedLocatieMagazin = new TextField();
        GridPane.setConstraints(updatedLocatieMagazin, 7, 2);

        Label updatedTipProduseLabel = new Label("Updated Tip Produse:");
        GridPane.setConstraints(updatedTipProduseLabel, 6, 3);

        updatedTipProduse = new TextField();
        GridPane.setConstraints(updatedTipProduse, 7, 3);

        Button updateMagazinButton = new Button("Update Magazin Record");
        updateMagazinButton.setOnAction(e -> updateMagazinRecord());
        GridPane.setConstraints(updateMagazinButton, 7, 4);

        magazinTableView = new TableView<>();
        TableColumn<Magazin, Integer> magazinIdColumn = new TableColumn<>("Magazin ID");
        magazinIdColumn.setCellValueFactory(new PropertyValueFactory<>("idMagazin"));
        TableColumn<Magazin, String> numeMagazinColumn = new TableColumn<>("Nume Magazin");
        numeMagazinColumn.setCellValueFactory(new PropertyValueFactory<>("numeMagazin"));
        TableColumn<Magazin, String> locatieColumn = new TableColumn<>("Locatie");
        locatieColumn.setCellValueFactory(new PropertyValueFactory<>("locatie"));
        TableColumn<Magazin, String> tipProduseColumn = new TableColumn<>("Tip Produse");
        tipProduseColumn.setCellValueFactory(new PropertyValueFactory<>("tipProduse"));

        magazinTableView.getColumns().addAll(magazinIdColumn, numeMagazinColumn, locatieColumn, tipProduseColumn);

        GridPane.setConstraints(clientTableView, 0, 8, 4, 1);
        GridPane.setConstraints(magazinTableView, 4, 8, 4, 1);

        grid.getChildren().addAll(
                idLabel, ID, numeLabel, nume, prenumeLabel, prenume, cnpLabel, cnp,
                numarTelefonLabel, numarTelefon, addButton, deleteIdLabel, deleteID, deleteButton,
                updateIdLabel, updateID, updatedNumeLabel, updatedNume, updatedPrenumeLabel, updatedPrenume,
                updatedCnpLabel, updatedCnp, updatedNumarTelefonLabel, updatedNumarTelefon, updateButton, clientTableView,
                magazinIdLabel, magazinID, numeMagazinLabel, numeMagazin, locatieLabel, locatie, tipProduseLabel, tipProduse,
                addMagazinButton, deleteMagazinIdLabel, deleteMagazinID, deleteMagazinButton,
                updateIdMagazinLabel, updateID_magazin, updatedNumeMagazinLabel, updatedNumeMagazin,
                updatedLocatieMagazinLabel, updatedLocatieMagazin, updatedTipProduseLabel, updatedTipProduse,
                updateMagazinButton, magazinTableView
        );

        Scene scene = new Scene(grid, 1200, 600);
        primaryStage.setScene(scene);

        primaryStage.show();

        loadClientData();
        loadMagazinData();
    }


    private void addToDatabase() {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO clienti (ID_client, nume, prenume, CNP, numar_telefon) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(ID.getText()));
                preparedStatement.setString(2, nume.getText());
                preparedStatement.setString(3, prenume.getText());
                preparedStatement.setString(4, cnp.getText());
                preparedStatement.setString(5, numarTelefon.getText());
                preparedStatement.executeUpdate();
                System.out.println("Record added to the database");

                // Reload data into the TableView after adding a record
                loadClientData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String deleteQuery = "DELETE FROM clienti WHERE ID_client = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(deleteID.getText()));
                preparedStatement.executeUpdate();
                System.out.println("Record deleted from the database");

                // Reload data into the TableView after deleting a record
                loadClientData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRecord() {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE clienti SET nume = ?, prenume = ?, CNP = ?, numar_telefon = ? WHERE ID_client = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, updatedNume.getText());
                preparedStatement.setString(2, updatedPrenume.getText());
                preparedStatement.setString(3, updatedCnp.getText());
                preparedStatement.setString(4, updatedNumarTelefon.getText());
                preparedStatement.setInt(5, Integer.parseInt(updateID.getText()));
                preparedStatement.executeUpdate();
                System.out.println("Record updated in the database");

                // Reload data into the TableView after updating a record
                loadClientData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadClientData() {
        clientTableView.getItems().clear();

        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectQuery = "SELECT * FROM clienti";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idClienti = resultSet.getInt("ID_client");
                    String nume = resultSet.getString("nume");
                    String prenume = resultSet.getString("prenume");
                    String cnp = resultSet.getString("cnp");
                    String numarTelefon = resultSet.getString("numar_telefon");

                    clientTableView.getItems().add(new Client(idClienti, nume, prenume, cnp, numarTelefon));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void addMagazinToDatabase() {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO magazine (ID_magazin, nume_magazin, locatie, tip_produse) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(magazinID.getText()));
                preparedStatement.setString(2, numeMagazin.getText());
                preparedStatement.setString(3, locatie.getText());
                preparedStatement.setString(4, tipProduse.getText());
                preparedStatement.executeUpdate();
                System.out.println("Magazin added to the database");

                // Reload data into the MagazinTableView after adding a record
                loadMagazinData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteMagazinFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String deleteQuery = "DELETE FROM magazine WHERE ID_magazin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(deleteMagazinID.getText()));
                preparedStatement.executeUpdate();
                System.out.println("Magazin deleted from the database");

                // Reload data into the MagazinTableView after deleting a record
                loadMagazinData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMagazinData() {
        magazinTableView.getItems().clear();

        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectQuery = "SELECT * FROM magazine";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idMagazin = resultSet.getInt("ID_magazin");
                    String numeMagazin = resultSet.getString("nume_magazin");
                    String locatie = resultSet.getString("locatie");
                    String tipProduse = resultSet.getString("tip_produse");

                    magazinTableView.getItems().add(new Magazin(idMagazin, numeMagazin, locatie, tipProduse));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateMagazinRecord() {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String user = "root";
        String password = "Mercedes22012002!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE magazine SET nume_magazin = ?, locatie = ?, tip_produse = ? WHERE ID_magazin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, updatedNumeMagazin.getText());
                preparedStatement.setString(2, updatedLocatieMagazin.getText());
                preparedStatement.setString(3, updatedTipProduse.getText());
                preparedStatement.setInt(4, Integer.parseInt(updateID_magazin.getText()));
                preparedStatement.executeUpdate();
                System.out.println("Record updated in the database");

                // Reload data into the TableView after updating a record
                loadMagazinData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}