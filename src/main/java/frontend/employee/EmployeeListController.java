package frontend.employee;

import com.jfoenix.controls.JFXTextField;
import entities.Employee;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import orm.EmployeeDatabaseService;
import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeListController implements Initializable {

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> colVorname, colNachname, colStatusArbRecht, colBerufsstatus;

    @FXML
    private TableColumn<Employee, Integer> colID;

    @FXML
    public void initialize(URL url, ResourceBundle rb){

        txtSearch.setLabelFloat(true);

        colID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        colVorname.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFirstName()));
        colNachname.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastName()));
        colStatusArbRecht.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStateByEmploymentLaw().getDescription()));
        colBerufsstatus.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProfessionalStanding().getDescription()));

        EmployeeDatabaseService employeeDatabaseService = new EmployeeDatabaseService();
        List<Employee> employees = employeeDatabaseService.getAll(Employee.class);
        ObservableList<Employee> employeeList = FXCollections.observableList(employees);

        tableView.setItems(employeeList);

    }

    public void editEmployee(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Employee currentItemSelected = tableView.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("frontend/employee/editemployee.fxml"));
            Parent root = fxmlLoader.load();
            EditEmployeeController editEmployeeController = fxmlLoader.<EditEmployeeController>getController();
            editEmployeeController.getDataFromEmployeeView(currentItemSelected);
            Scene editScene = new Scene(root,500,500);
            editScene.getStylesheets().add(Main.class.getResource("/styles/basic.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(editScene);
            stage.show();
        }
    }

    public void addEmployee(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("frontend/employee/editemployee.fxml"));
        Parent root = fxmlLoader.load();
        Scene addScene = new Scene(root,500,500);
        addScene.getStylesheets().add(Main.class.getResource("/styles/basic.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(addScene);
        stage.show();
    }
}
