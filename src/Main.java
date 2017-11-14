import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.text.Text;

/**
 * Create by hieuduong on 11/13/17
 *
 * CSC 201 - Assignment 12
 * Problem 12.6:
 *
 * (NumberFormatException) Listing 6.8 implements the hex2Dec(String
 * hexString) method, which converts a hex string into a decimal number.
 * Implement the hex2Dec method to throw a NumberFormatException if the
 * string is not a hex string.
 */

public class Main extends Application{

    private TextField inputTxt = new TextField();
    private Label status = new Label();
    @Override
    public void start(Stage primaryStage) {

        // Create a pane
        BorderPane pane = new BorderPane();

        // Place nodes in the pane
        pane.setTop(getHBox());
        pane.setLeft(getVBox());

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Convert Hex to Decimal"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * Create Hbox to hold the shuffle button
     * @return
     */
    private HBox getHBox() {
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: silver");

        Button b1 = new Button("Submit");
        hBox.getChildren().add(inputTxt);
        hBox.getChildren().add(b1);
        hBox.setAlignment(Pos.CENTER);

        b1.setOnAction(e -> convertToHex());

        return hBox;
    }

    public VBox getVBox() {
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.getChildren().addAll(status);
        return vBox;
    }

    /**
     * Convert to Hex method
     */
    private void convertToHex(){

        String hex = inputTxt.getText().toUpperCase();
        if(hex.isEmpty()){
            showAlertBox("Value is required!");
            inputTxt.requestFocus();
        }
        else {
            try {
                int value = Integer.parseInt(hex, 16);
                int decimalValue = 0;
                for (int i = 0; i < hex.length(); i++) {
                    char hexChar = hex.charAt(i);
                    decimalValue = decimalValue * 16 + hexCharToDecimal(hexChar);
                }
                status.setText("Decimal valus is: " + decimalValue);
            } catch (NumberFormatException ex) {
                showAlertBox(hex + " is not a valid hex");
            }
        }
    }

    /**
     * Get value of hex char
     * @param ch
     * @return
     */
    private int hexCharToDecimal(char ch) {
        if(ch >= 'A' && ch <= 'F')
            return 10 + ch - 'A';
        else // ch is '0', '1', ..., or '9'
            return ch - '0';

    }

    /**
     * show alert box
     * @param msg
     */
    private void showAlertBox(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }
}
