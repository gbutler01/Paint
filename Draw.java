package com.mycompany.paint;

import java.util.Scanner;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Draw extends App {  
    
    /**
     * Returns ability to draw freely on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void freeDraw() {    
        canvas.setOnMousePressed(e -> { // action when clicking on image
            visuals.beginPath();
            visuals.lineTo(e.getX(), e.getY());
            visuals.setLineWidth(linesize);
        });
        canvas.setOnMouseDragged(e -> { // action when dragging clicked mouse
            visuals.setLineWidth(linesize);
            visuals.lineTo(e.getX(), e.getY());
            visuals.stroke();
        });
        canvas.setOnMouseReleased(e -> { // creates end point on line
            visuals.setLineWidth(linesize);
            visuals.closePath();
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw red/orange gradient on canvas upon clicking button
     * @return rainbow - sets up array of colors to be featured
     * @return rainbowline - allows colors in array to cycle through each other
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void redorangeDraw() {    
        rainbow = new Stop[] {new Stop(0,Color.RED), new Stop(1,Color.ORANGE)};
        rainbowline = new LinearGradient(0, 0, 35, 0, false, CycleMethod.REPEAT, rainbow);
        canvas.setOnMousePressed(e -> {
            visuals.beginPath();
            visuals.lineTo(e.getX(), e.getY());
            visuals.setStroke(Color.RED);
            visuals.setLineWidth(linesize);
        });
        canvas.setOnMouseDragged(e -> {
            visuals.setLineWidth(linesize);
            visuals.setStroke(rainbowline);
            visuals.lineTo(e.getX(), e.getY());
            visuals.stroke();
        });
        canvas.setOnMouseReleased(e -> {
            visuals.setLineWidth(linesize);
            visuals.closePath();
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw yellow/green gradient on canvas upon clicking button
     * @return rainbow - sets up array of colors to be featured
     * @return rainbowline - allows colors in array to cycle through each other
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void yellowgreenDraw() {    
        rainbow = new Stop[] {new Stop(0,Color.YELLOW), new Stop(1,Color.GREEN)};
        rainbowline = new LinearGradient(0, 0, 35, 0, false, CycleMethod.REPEAT, rainbow);
        canvas.setOnMousePressed(e -> { // action when clicking on image
            visuals.beginPath();
            visuals.lineTo(e.getX(), e.getY());
            visuals.setStroke(Color.YELLOW);
            visuals.setLineWidth(linesize);
        });
        canvas.setOnMouseDragged(e -> { // action when dragging clicked mouse
            visuals.setLineWidth(linesize);
            visuals.setStroke(rainbowline);
            visuals.lineTo(e.getX(), e.getY());
            visuals.stroke();
        });
        canvas.setOnMouseReleased(e -> { // creates end point on line
            visuals.setLineWidth(linesize);
            visuals.closePath();
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw blue/purple gradient on canvas upon clicking button
     * @return rainbow - sets up array of colors to be featured
     * @return rainbowline - allows colors in array to cycle through each other
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void bluepurpleDraw() {    
        rainbow = new Stop[] {new Stop(0,Color.BLUE), new Stop(1,Color.PURPLE)};
        rainbowline = new LinearGradient(0, 0, 35, 0, false, CycleMethod.REPEAT, rainbow);
        canvas.setOnMousePressed(e -> { // action when clicking on image
            visuals.beginPath();
            visuals.lineTo(e.getX(), e.getY());
            visuals.setStroke(Color.BLUE);
            visuals.setLineWidth(linesize);
        });
        canvas.setOnMouseDragged(e -> { // action when dragging clicked mouse
            visuals.setLineWidth(linesize);
            visuals.setStroke(rainbowline);
            visuals.lineTo(e.getX(), e.getY());
            visuals.stroke();
        });
        canvas.setOnMouseReleased(e -> { // creates end point on line
            visuals.setLineWidth(linesize);
            visuals.closePath();
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw a straight line on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void straightDraw() {
        canvas.setOnMousePressed(e -> { // action when clicking on image
            visuals.beginPath();
            visuals.lineTo(e.getX(), e.getY());
            visuals.setLineWidth(linesize);
        });
        canvas.setOnMouseDragged(e -> { // action when dragging clicked mouse
            visuals.setLineWidth(linesize);
            visuals.stroke();
        });
        canvas.setOnMouseReleased(e -> { // creates end point on line
            visuals.setLineWidth(linesize);
            visuals.lineTo(e.getX(), e.getY());
            visuals.stroke();
            visuals.closePath();
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw square on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void drawSquare() {   
        GraphicsContext visuals = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(e->{
            x1 = e.getX();
            y1 = e.getY();
        });
        canvas.setOnMouseDragged(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            visuals.strokeRect((double)y_2, (double)x_2, absolutex, absolutex);
            visuals.setLineWidth(10);
        });
        canvas.setOnMouseReleased(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            visuals.strokeRect((double)y_2, (double)x_2, absolutex, absolutex);
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw rectangle on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void drawRectangle() {
        GraphicsContext visuals = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(e->{
            x1 = e.getX();
            y1 = e.getY();
        });
        canvas.setOnMouseDragged(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            double absolutey = Math.abs(y1-y2);
            visuals.strokeRect((double)y_2, (double)x_2, absolutex, absolutey);
            visuals.setLineWidth(10);
        });
        canvas.setOnMouseReleased(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            double absolutey = Math.abs(y1-y2);
            visuals.strokeRect((double)y_2, (double)x_2, absolutex, absolutey);
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw oval on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void drawEllipse() {
        GraphicsContext visuals = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(e->{
            x1 = e.getX();
            y1 = e.getY();
        });
        canvas.setOnMouseDragged(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            double absolutey = Math.abs(y1-y2);
            visuals.strokeOval((double)y_2, (double)x_2, absolutex, absolutey);
            visuals.setLineWidth(10);
        });
        canvas.setOnMouseReleased(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            double absolutey = Math.abs(y1-y2);
            visuals.strokeOval((double)y_2, (double)x_2, absolutex, absolutey);
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw circle on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void drawCircle() {
        GraphicsContext visuals = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(e->{
            x1 = e.getX();
            y1 = e.getY();
        });
        canvas.setOnMouseDragged(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            visuals.strokeOval((double)y_2, (double)x_2, absolutex, absolutex);
            visuals.setLineWidth(10);
        });
        canvas.setOnMouseReleased(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            visuals.strokeOval((double)y_2, (double)x_2, absolutex, absolutex);
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw roundrectangle on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void drawRoundRect() {
        GraphicsContext visuals = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(e->{
            x1 = e.getX();
            y1 = e.getY();
        });
        canvas.setOnMouseDragged(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            double absolutey = Math.abs(y1-y2);
            visuals.strokeRoundRect((double)y_2, (double)x_2, absolutex, absolutey, 12, 18);
            visuals.setLineWidth(10);
        });
        canvas.setOnMouseReleased(e->{
            x2 = e.getX();
            y2 = e.getY();
            double y_2 = Math.min(x1, x2);
            double x_2 = Math.min(y1, y2);
            double absolutex = Math.abs(x1-x2);
            double absolutey = Math.abs(y1-y2);
            visuals.strokeRoundRect((double)y_2, (double)x_2, absolutex, absolutey, 12, 18);
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Returns ability to draw user-specified polygon on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
//    public static void drawPolygon() {
//        Dialog<String> polygonsides = new Dialog<String>();
//        polygonsides.setTitle("Other Polygon");
//        String sidenumber = "Enter the number of sides you want for your polygon";
//        polygonsides.setContentText(sidenumber);
//        Scanner input = new Scanner(System.in); // allows for user input when indicated
//        int sides = input.nextInt();
//        TextField sidesinput = new TextField();
//        //sidesinput.getText();
//        //polygonsides.getText().add(sidesinput);
//        ButtonType confirm = new ButtonType("Confirm");
//        polygonsides.getDialogPane().getButtonTypes().add(confirm);
//        if (sides < 5) {
//            Dialog<String> errormessage = new Dialog<String>();
//            errormessage.setTitle("ERROR");
//            String error = "Must enter 5 or higher.";
//            errormessage.setContentText(error);
//            ButtonType okbutton = new ButtonType("Ok");
//            errormessage.getDialogPane().getButtonTypes().add(okbutton);
//        }
//        else {
//            canvas.setOnMousePressed(e -> {
//                 
//            });
//            canvas.setOnMouseDragged(e -> {
//                    
//            });
//            canvas.setOnMouseReleased(e -> {
//                 
//            });
//        }
//        polygonsides.showAndWait();
//    }
    
    /**
     * Returns ability to erase on canvas upon clicking button
     * @return visuals - allows for drawing on canvas
     * @return linesize - indicates size of drawing (connected with linewidth)
     * @return linewidth - indicates parameter width
     * @return colorPicker - indicates color drawn
     * @see canvas - actions executed will appear on canvas
     */
    public static void erase() {
        colorPicker.setValue(Color.WHITE);
        canvas.setOnMousePressed(e -> {
            visuals.beginPath();
            visuals.lineTo(e.getX(), e.getY());
            visuals.setStroke(Color.WHITE);
            visuals.setLineWidth(linesize);
        });
        canvas.setOnMouseDragged(e -> {
            visuals.setLineWidth(linesize);
            visuals.lineTo(e.getX(), e.getY());
            visuals.stroke();
        });
        canvas.setOnMouseReleased(e -> {
            visuals.setLineWidth(linesize);
            visuals.closePath();
        });
        colorPicker.setOnAction(e->visuals.setStroke(colorPicker.getValue()));
    }
    
    /**
     * Accessor & mutator methods to obtain canvas from App.java
     * @return canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
    
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    
    /**
     * Accessor & mutator methods to obtain visuals from App.java
     * @return visuals
     */
    public GraphicsContext getVisuals() {
        return visuals;
    }
    
    public void setVisuals(GraphicsContext visuals) {
        this.visuals = visuals;
    }
    
    /**
     * Accessor & mutator methods to obtain linesize from App.java
     * @return linesize
     */
    public double getLinesize() {
        return linesize;
    }
    
    public void setLinesize(double linesize) {
        this.linesize = linesize;
    }
    
    /**
     * Accessor & mutator methods to obtain colorPicker from App.java
     * @return colorPicker
     */
    public ColorPicker getColorpicker() {
        return colorPicker;
    }
    
    public void setColorpicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }
    
    /**
     * Accessor & mutator methods to obtain coordinates for shapes from App.java
     * @return x1, y1, x2, y2
     */
    public double getx1() {
        return x1;
    }
    
    public void setx1(double x1) {
        this.x1 = x1;
    }
    
    public double gety1() {
        return y1;
    }
    
    public void sety1(double y1) {
        this.y1 = y1;
    }
    
    public double getx2() {
        return x2;
    }
    
    public void setx2(double x2) {
        this.x2 = x2;
    }
    
    public double gety2() {
        return y2;
    }
    
    public void sety2(double y2) {
        this.y2 = y2;
    }
    
    /**
     * Accessor & mutator methods to obtain linewidth from App.java
     * @return linewidth
     */
    public Slider getLinewidth() {
        return linewidth;
    }
    
    public void setLinewidth(Slider linewidth) {
        this.linewidth = linewidth;
    }
}