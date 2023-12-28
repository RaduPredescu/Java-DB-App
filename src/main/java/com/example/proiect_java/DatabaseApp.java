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
    private TableView<Client> tableView;

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

        // New fields for updating records
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

        tableView = new TableView<>();
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

        tableView.getColumns().addAll(idColumn, numeColumn, prenumeColumn, cnpColumn, numarTelefonColumn);

        GridPane.setConstraints(tableView, 0, 8, 4, 1);

        grid.getChildren().addAll(
                idLabel, ID, numeLabel, nume, prenumeLabel, prenume, cnpLabel, cnp,
                numarTelefonLabel, numarTelefon, addButton, deleteIdLabel, deleteID, deleteButton,
                updateIdLabel, updateID, updatedNumeLabel, updatedNume, updatedPrenumeLabel, updatedPrenume,
                updatedCnpLabel, updatedCnp, updatedNumarTelefonLabel, updatedNumarTelefon, updateButton, tableView
        );

        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setScene(scene);

        primaryStage.show();

        loadData();
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
                loadData();
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
                loadData();
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
                loadData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        tableView.getItems().clear();

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

                    tableView.getItems().add(new Client(idClienti, nume, prenume, cnp, numarTelefon));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}