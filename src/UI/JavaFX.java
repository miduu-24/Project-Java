package UI;

import Domain.Cake;
import Domain.Comand;
import Service.ServiceCake;
import Service.ServiceComand;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.scene.paint.Color.WHITE;
public class JavaFX extends Application {

    private static ServiceCake cakeService;
    private static ServiceComand comandService;
    private ObservableList<Cake> cakeData;
    private ObservableList<Comand> comandData;
    private ListView<Cake> cakeList;
    private ListView<Comand> comandList;
    private DatePicker comandDatePicker;

    public static void setCake(ServiceCake cake) {
        JavaFX.cakeService = cake;
    }

    public static void setServiceComand(ServiceComand serviceComand) {
        JavaFX.comandService = serviceComand;
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            cakeData = FXCollections.observableArrayList(cakeService.getAllCakes());
            cakeList = new ListView<>(cakeData);
            cakeList.setPrefWidth(200);
            VBox leftContainer = new VBox(cakeList);
            leftContainer.setPadding(new Insets(10));
            leftContainer.setSpacing(8);
            leftContainer.setStyle("-fx-background-color: #336699;");

            comandData = FXCollections.observableArrayList(comandService.getAllComands());
            comandList = new ListView<>(comandData);
            comandList.setPrefWidth(520);
            VBox centerContainer = new VBox(comandList);
            centerContainer.setPadding(new Insets(10));
            centerContainer.setSpacing(8);
            centerContainer.setStyle("-fx-background-color: #336699;");

            VBox rightContainer = createForm();
            rightContainer.setPadding(new Insets(10));
            rightContainer.setSpacing(8);
            rightContainer.setStyle("-fx-background-color: #336699;");

            HBox mainLayout = new HBox(leftContainer, centerContainer, rightContainer);
            mainLayout.setSpacing(15);
            mainLayout.setAlignment(Pos.CENTER_LEFT);
            mainLayout.setStyle("-fx-background-color: #336699;");

            Scene scene = new Scene(mainLayout, 1200, 450);


            stage.setTitle("Cakeshop Manager");
            stage.setResizable(false);
            stage.setX(50);
            stage.setY(50);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private VBox createForm() {
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(8);

        Label cakeNameLabel = new Label("Cake Type:");
        cakeNameLabel.setTextFill(WHITE);
        cakeNameLabel.setStyle("-fx-font-weight: bold");
        TextField cakeNameText = new TextField();
        cakeNameText.setPromptText("Enter cake type");
        cakeNameText.setPrefWidth(250);

        Label cakeIdLabel = new Label("Cake ID:");
        cakeIdLabel.setTextFill(WHITE);
        cakeIdLabel.setStyle("-fx-font-weight: bold");
        TextField cakeIdText = new TextField();
        cakeIdText.setPromptText("Enter cake ID (for update/delete)");
        cakeIdText.setPrefWidth(250);

        form.add(cakeIdLabel, 0, 1);
        form.add(cakeIdText, 1, 1);
        form.add(cakeNameLabel, 0, 0);
        form.add(cakeNameText, 1, 0);

        Button addCakeButton = new Button("Add Cake");
        addCakeButton.setOnAction(e -> addCake(cakeNameText));
        Button updateCakeButton = new Button("Update Cake");
        updateCakeButton.setOnAction(e -> updateCake(cakeNameText, cakeIdText));
        Button deleteCakeButton = new Button("Delete Cake");
        deleteCakeButton.setOnAction(e -> deleteCake(cakeIdText));

        GridPane orderForm = new GridPane();
        orderForm.setVgap(10);
        orderForm.setHgap(8);

        Label orderDateLabel = new Label("Order Date:");
        orderDateLabel.setTextFill(WHITE);
        orderDateLabel.setStyle("-fx-font-weight: bold");
        comandDatePicker = new DatePicker();
        comandDatePicker.setPromptText("Enter order date");
        comandDatePicker.setPrefWidth(250);

        Label comanIdLabel = new Label("Order ID:");
        comanIdLabel.setTextFill(WHITE);
        comanIdLabel.setStyle("-fx-font-weight: bold");
        TextField comanIdText = new TextField();
        comanIdText.setPrefWidth(250);
        comanIdText.setPromptText("Enter order ID (for update/delete)");

        Label cakeIdLabel2 = new Label("Cake IDs:");
        cakeIdLabel2.setTextFill(WHITE);
        cakeIdLabel2.setStyle("-fx-font-weight: bold");
        TextField cakeIdText2 = new TextField();
        cakeIdText2.setPromptText("Enter cake IDs separated by commas");
        cakeIdText2.setPrefWidth(250);


        orderForm.add(comanIdLabel, 0, 0);
        orderForm.add(comanIdText, 1, 0);
        orderForm.add(orderDateLabel, 0, 1);
        orderForm.add(comandDatePicker, 1, 1);
        orderForm.add(cakeIdLabel2, 0, 2);
        orderForm.add(cakeIdText2, 1, 2);

        Button addOrderButton = new Button("Add Order");
        addOrderButton.setOnAction(e -> addOrder(cakeIdText2 ,comandDatePicker));
        Button updateOrderButton = new Button("Update Order");
        updateOrderButton.setOnAction(e -> updateOrder(comanIdText, comandDatePicker));
        Button deleteOrderButton = new Button("Delete Order");
        deleteOrderButton.setOnAction(e -> deleteOrder(comanIdText));

        VBox combinedForm = new VBox(form, addCakeButton, updateCakeButton, deleteCakeButton, orderForm, addOrderButton, updateOrderButton, deleteOrderButton);
        combinedForm.setSpacing(15);

        return combinedForm;
    }

    private void addCake(TextField nameText) {
        try {
            String type = nameText.getText();
            cakeService.addCake(type);
            Cake newCake = cakeService.getAllCakes().get(cakeService.getAllCakes().size() - 1);
            cakeData.add(newCake);
            nameText.clear();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add cake: " + e.getMessage());
        }
    }

    private void updateCake(TextField nameText, TextField idText) {
        try {
            int id = Integer.parseInt(idText.getText());
            String type = nameText.getText();
            try {
                cakeService.updateCake(id, type);
                refreshCakeList();
                nameText.clear();
                idText.clear();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Update Error", "Cake with ID " + id + " does not exist.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid numeric ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update cake: " + e.getMessage());
        }
    }

    private void deleteCake(TextField idText) {
        try {
            int id = Integer.parseInt(idText.getText());
            try {
                cakeService.removeCake(id);
                refreshCakeList();
                idText.clear();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Delete Error", "Cake with ID " + id + " is in an order.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid numeric ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete cake: " + e.getMessage());
        }
    }

    private void refreshCakeList() {
        cakeData.setAll(cakeService.getAllCakes());
        cakeList.setItems(cakeData);
    }

    private void addOrder(TextField cakeIdText, DatePicker datePicker) {
        try {
            String date = datePicker.getValue().toString();
            List<Integer> cakeIds = parseCakeIds(cakeIdText.getText());
            ArrayList<Cake> selectedCakes = new ArrayList<>();
            for (Integer cakeId : cakeIds) {
                Cake cake = cakeService.findbyId(cakeId);
                if (cake != null) {
                    selectedCakes.add(cake);
                }
            }

            comandService.addComand(selectedCakes, date);
            refreshOrderList();
            cakeIdText.clear();
            datePicker.setValue(null);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add order: " + e.getMessage());
        }
    }

    private List<Integer> parseCakeIds(String cakeIds) {
        if (cakeIds == null || cakeIds.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(cakeIds.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


    private void updateOrder(TextField idText, DatePicker datePicker) {
        try {
            int id = Integer.parseInt(idText.getText());
            String date = datePicker.getValue().toString();
            ArrayList<Cake> selectedCakes = new ArrayList<>(cakeList.getSelectionModel().getSelectedItems());
            try {
                comandService.updateComand(id, selectedCakes, date);
                refreshOrderList();
                idText.clear();
                datePicker.setValue(null);
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Update Error", "Order with ID " + id + " does not exist.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid numeric ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update order: " + e.getMessage());
        }
    }

    private void deleteOrder(TextField idText) {
        try {
            int id = Integer.parseInt(idText.getText());
            try {
                comandService.removeComand(id);
                refreshOrderList();
                idText.clear();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Delete Error", "Order with ID " + id + " does not exist.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid numeric ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete order: " + e.getMessage());
        }
    }

    private void refreshOrderList() {
        comandData.setAll(comandService.getAllComands());
        comandList.setItems(comandData);
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
