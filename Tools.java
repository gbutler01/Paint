package com.mycompany.paint;

import static com.mycompany.paint.App.canvas;
import static com.mycompany.paint.App.clipboard;
import static com.mycompany.paint.App.copyimage;
import static com.mycompany.paint.App.visuals;
import java.io.File;
import java.util.Stack; // used for undo/redo
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Tools extends Draw {
    
    /**
     * Returns ability to zoom in on canvas upon clicking button
     * @return zoom - modifies value based on method
     * @see canvas - actions executed will appear on canvas
     */
    public static void zoomin() {
        if (zoom <= 2) {
            canvas.setScaleX(zoom + 0.025);
            canvas.setScaleY(zoom + 0.025);
            zoom = zoom + 0.025;
        }
        else {
        }
    }
    
    /**
     * Returns ability to zoom out on canvas upon clicking button
     * @return zoom - modifies value based on method
     * @see canvas - actions executed will appear on canvas
     */
    public static void zoomout() {
        if (zoom >= 0.5) {
            canvas.setScaleX(zoom - 0.025);
            canvas.setScaleY(zoom - 0.025);
            zoom = zoom - 0.025;
        }
        else {
        }
    }
    
    /**
     * Returns ability to select part of an image and move it
     * @see canvas - actions executed will appear on canvas
     */
//    public static void imagepiece() {
//        canvas.setOnMousePressed(e -> {
//                    
//        });
//        canvas.setOnMouseDragged(e -> {
//                   
//        });
//        canvas.setOnMouseReleased(e -> {
//                    
//        });
//    }
    
    /**
     * Returns blank image to be placed on canvas
     * @return blankimage - imports blank image file
     * @see canvas - actions executed will appear on canvas
     */
    public static void defaultcanvas() {
        Image blankimage = new Image("/Paint/Source Packages/BlankCanvas/BlankCanvas.jpg");
        canvas.setWidth(blankimage.getWidth());
        canvas.setHeight(blankimage.getHeight());
        visuals.drawImage(blankimage, 0, 0);
        canvas.setScaleX(2);
        canvas.setScaleY(2);
    }
    
    /**
     * Returns String message that gives directions on navigating program.
     * @return helpmessage - text field that displays help instructions
     * @see helptext - String that contains instructions
     */
    public static void help() {
        Dialog<String> helpmessage = new Dialog<String>();
        helpmessage.setTitle("Help");
        ButtonType helpbutton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        String helptext = "Use Import File to choose an image to display! (Alt + F)\n" +
                          "Click Save to save your image! (Ctrl + S)\n" +
                          "Click Save As to save your image as a particular file in a location of your choice! Do this before clicking Save! (Ctrl + Shift + S)\n" +
                          "Click Exit to exit the program! Smart Save included! (Alt + Space)\n" +
                          "Draw a straight line, draw freely, or choose a shape to construct!\n" +
                          "Choose one of the rainbow color options to add a neat gradient to your image!\n" +
                          "Pick a color to draw your lines!\n" +
                          "Make a mistake? Use the eraser to take it away!\n" +
                          "Use the text tool to take notes!\n" +
                          "Zoom in and out on your image! (Ctrl + Up/Down)\n" +
                          "Click undo to go back on your image if you want to do something differently!\n" +
                          "Click redo to restart your canvas with your image!\n" +
                          "Check how often your program will autosave by clicking Timer!\n" +
                          "Use the slider to control the width of your lines that you draw!\n" +
                          "Start with a blank canvas with the push of a button!\n" +
                          "Not sure what each button does? Hover over them for an explanation!";
        helpmessage.setContentText(helptext);
        helpmessage.getDialogPane().getButtonTypes().add(helpbutton);
        helpmessage.showAndWait();
    }
    
    /**
     * Returns String message that gives information on program.
     * @return aboutmessage - text field that displays information
     * @see abouttext - String that contains information
     */
    public static void about() {
        Dialog<String> aboutmessage = new Dialog<String>();
        aboutmessage.setTitle("About");
        ButtonType aboutbutton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        String abouttext = "Program: Paint 1.0.0\n" +
                           "Made by Graham Butler";
        aboutmessage.setContentText(abouttext);
        aboutmessage.getDialogPane().getButtonTypes().add(aboutbutton);
        aboutmessage.showAndWait();
    }
    
    /**
     * Returns previous iteration of program to undo mistake.
     * @return wi - retains ability to modify image on canvas upon reverting progress
     * @return canvas - retains canvas upon reverting progress
     * @return visuals - retains visual abilities upon reverting progress
     * @see undoImage - presents image before last modification
     */
    public static void undo() {
        Stack undo = new Stack();
        WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        undo.push(canvas.snapshot(null,wi));
        Image undoimage = (Image) undo.pop();
        visuals.drawImage(undoimage, 0, 0);
    }
    
    /**
     * Returns original iteration of project to start over.
     * @return wi - retains ability to modify image on canvas upon reverting progress
     * @return canvas - retains canvas upon reverting progress
     * @return visuals - retains visual abilities upon reverting progress
     * @see redoImage - presents image before starting
     */
    public static void redo() {
        Stack redo = new Stack();
        WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        redo.push(canvas.snapshot(null,wi));
        Image redoimage = (Image) redo.pop();
        visuals.drawImage(redoimage, 0, 0);
    }
    
    /**
     * Takes and stores a copy of what is currently present on the canvas
     * @return copyfile - temporarily stores copy
     * @return copyimage - converts image to transferrable file
     * @return copyview - allows converted file to be viewed
     * @return clipboard - transfer copy to storage
     */
    public static void copy() {
        File copyfile = new File("C:");
        copyimage = new Image(copyfile.toURI().toString());
        ImageView copyview = new ImageView(copyimage);
        clipboard = Clipboard.getSystemClipboard();
    }
    
    /**
     * Places stored copy onto canvas
     * @return content - allows for stored copy to be taken from storage
     * @return copyimage - converts image to transferrable file
     * @return clipboard - transfer copy to storage
     */
    public static void paste() {
        if (clipboard.hasString()) {
            ClipboardContent content = new ClipboardContent();
            content.putImage(copyimage);
            clipboard.setContent(content);
        }
        else {   
        }
    }
        
}