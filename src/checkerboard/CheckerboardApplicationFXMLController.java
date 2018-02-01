/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.awt.MenuBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Joey
 */
public class CheckerboardApplicationFXMLController implements Initializable, Startable {
    
    private Stage stage;
    
    private Checkerboard checkerboard;
    private AnchorPane anchorPane;
   
    @FXML
    private VBox vBox;
    
//    @FXML
//    private MenuBar menuBar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        checkerboard = new Checkerboard(8, 8, 60, 60);
        anchorPane = checkerboard.getBoard();
        vBox.getChildren().add(anchorPane);
        
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refresh();
        };
        
        this.stage.widthProperty().addListener(lambdaChangeListener);
        this.stage.heightProperty().addListener(lambdaChangeListener);
        
        refresh();    
    }
    
    @FXML
    private void handleBoardResize(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        System.out.println("Button ID: " + menuItem.getId());
        checkerboard = new Checkerboard(16, 16, 60, 60);
        anchorPane = checkerboard.getBoard();
        refresh();
    }
    
    private void refresh() {
        checkerboard.build(vBox.getWidth(), (vBox.getHeight() - 29));
    }
}
