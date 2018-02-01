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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Joey
 */
public class CheckerboardApplicationFXMLController implements Initializable, Startable {
    
    private Stage stage;
    
    private Checkerboard checkerboard;
    private AnchorPane anchorPane;
    private final Color[] blueColorScheme = {Color.SKYBLUE, Color.DARKBLUE};
   
    @FXML
    private VBox vBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Controller Initialize");
    } 

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        checkerboard = new Checkerboard(8, 8, 60, 60);
        anchorPane = checkerboard.getBoard();
        vBox.getChildren().add(anchorPane);
        
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refreshBoard();
        };
        
        this.stage.widthProperty().addListener(lambdaChangeListener);
        this.stage.heightProperty().addListener(lambdaChangeListener);
        
        refreshBoard();    
    }
    
    @FXML
    private void handleBoardGridChange(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        int size = Integer.parseInt(menuItem.getId());
        System.out.println("Button ID: " + size);
        
        checkerboard = new Checkerboard(size, size, 60, 60, checkerboard.getLightColor(), checkerboard.getDarkColor());
        
        resetAnchorPane();
        refreshBoard();
    }
    
    @FXML
    private void handleBoardColorChange(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        
        switch(menuItem.getId()){
            case "blueColorScheme":
                checkerboard = new Checkerboard(checkerboard.getNumRows(), checkerboard.getNumColumns(), 60, 60, blueColorScheme[0], blueColorScheme[1]);
                break;
            default:
                checkerboard = new Checkerboard(checkerboard.getNumRows(), checkerboard.getNumColumns(), 60, 60);
        }
        
        resetAnchorPane();
        refreshBoard();
    }
    
    private void refreshBoard() {
        checkerboard.build(vBox.getWidth(), (vBox.getHeight() - 29));
    }
    
    private void resetAnchorPane() {
        vBox.getChildren().remove(anchorPane);
        anchorPane = checkerboard.getBoard();
        vBox.getChildren().add(anchorPane);    
    }
}
