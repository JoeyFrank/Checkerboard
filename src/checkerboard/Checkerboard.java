/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Joey
 */
public class Checkerboard {
    private double width;
    private double height;
    private int rows;
    private int columns;
    private double rectangleWidth;
    private double rectangleHeight;
    private Color lightColor;
    private Color darkColor;
    private AnchorPane anchorPane;
    
    //constructor with default colors
    public Checkerboard(int numRows, int numColumns, double boardWidth, double boardHeight) {
        this.rows = numRows;
        this.columns = numColumns;
        this.width = boardWidth;
        this.height = boardHeight;
        this.lightColor = Color.RED;
        this.darkColor = Color.BLACK;
        
        anchorPane = new AnchorPane();
    }
    
    //constructor with color parameters
    public Checkerboard(int numRows, int numColumns, double boardWidth, double boardHeight, Color lightColor, Color darkColor) {
        this(numRows,numColumns,boardWidth,boardHeight);
        this.lightColor = lightColor;
        this.darkColor = darkColor;
    }
    
    public AnchorPane build(double width, double height) {
        clear();
        
        this.width = width;
        this.height = height;
        
        rectangleWidth = (width / (double)columns);
        rectangleHeight = (height / (double)rows);
        
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);
        
        //System.out.println("update board");
         
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Rectangle rect;
                
                //math for grid colors
                if( (row+col)%2 == 0 ){
                    rect = new Rectangle(rectangleWidth, rectangleHeight, lightColor);
                } else {
                    rect = new Rectangle(rectangleWidth, rectangleHeight, darkColor);
                }
                
                //setting position in anchorpane
                AnchorPane.setTopAnchor(rect, row*rectangleHeight);
                AnchorPane.setLeftAnchor(rect, col*rectangleWidth);
                
                anchorPane.getChildren().add(rect);
            }
        }
        
        return anchorPane;
    }
    
    public void clear() {
        if(anchorPane != null){
            anchorPane.getChildren().clear();
        }
    }
    
    public AnchorPane getBoard() {
        return (anchorPane != null) ? anchorPane : null;
    }
    
    public int getNumRows() {
        return rows;
    }
    
    public int getNumColumns() {
        return columns;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public double getRectangleWidth() {
        return rectangleWidth;
    }
    
    public double getRectangleHeight() {
        return rectangleHeight;
    }
    
    public Color getLightColor() {
        return lightColor;
    }
    
    public Color getDarkColor() {
        return darkColor;
    }
}
