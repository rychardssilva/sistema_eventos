import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

public class ParticipantController {

    @FXML private TableView<ObservableList<String>> eventTable;
    @FXML private TableColumn<ObservableList<String>, String> nameColumn;
    @FXML private TableColumn<ObservableList<String>, String> dateColumn;
    @FXML private TableColumn<ObservableList<String>, String> timeColumn;

    @FXML
    public void initialize() {
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, date, time FROM events")) { // agora usando os nomes corretos

            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("name"));   // nome do evento
                row.add(rs.getString("date"));   // data do evento
                row.add(rs.getString("time"));   // hora do evento
                data.add(row);
            }

            eventTable.setItems(data);

            nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
            dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
            timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
