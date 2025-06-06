

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DashboardController {

    @FXML private TableView<ObservableList<String>> eventTable;
    @FXML private TableColumn<ObservableList<String>, String> nameColumn;
    @FXML private TableColumn<ObservableList<String>, String> dateColumn;
    @FXML private TableColumn<ObservableList<String>, String> timeColumn;

    @FXML
    public void initialize() {
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, date, time FROM events")) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("name"));
                row.add(rs.getString("date"));
                row.add(rs.getString("time"));
                eventTable.getItems().add(row);
            }

            nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(0)));
            dateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(1)));
            timeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(2)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToCreateEvent() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateEvent.fxml"));
            Stage stage = (Stage) eventTable.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
