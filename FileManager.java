package com.mycompany.paint;

import static com.mycompany.paint.App.canvas;
import static com.mycompany.paint.App.choosefile;
import static com.mycompany.paint.App.filesave;
import static com.mycompany.paint.App.newfile;
import static com.mycompany.paint.App.path;
import static com.mycompany.paint.App.stage;
import static com.mycompany.paint.App.visuals;
import static com.mycompany.paint.App.zoom;
import java.awt.image.RenderedImage; // saves edited image
import java.io.File;
import java.io.IOException; // used in save function
import java.util.Optional; // determines result when choosing options in smart save
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert; // alerts user with smart save when exiting
import javafx.scene.control.ButtonType; // used to make "ok" buttons to exit help/about messages
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage; // allows for saving image files
import javafx.stage.FileChooser;
import javax.imageio.ImageIO; // used alongside IO Exception

public class FileManager extends Draw {
    
    /**
     * Returns ability to import a file upon clicking button
     * @return fileimport - allows File Explorer to be opened
     * @return visuals - allows file selected to be present on canvas
     * @see canvas - actions executed will appear on canvas
     */
    public static void fileimport() {
        File fileimport = choosefile.showOpenDialog(stage);
        Image imageimport = new Image(fileimport.toURI().toString());
        canvas.setWidth(imageimport.getWidth());
        canvas.setHeight(imageimport.getHeight());
        visuals.drawImage(imageimport, 0, 0);
        canvas.setScaleX(zoom); // scales image depending on working space size
        canvas.setScaleY(zoom); // set value, will change later to accommodate different image sizes
    }
    
    /**
     * Returns ability to call File Explorer and save file as a certain type.
     * @param canvas - convert canvas into saveable data type
     * @param filesave - allows file to be saved as any file type
     * @return saveas(canvas, filesave) - save canvas and image as set file type
     * @return savefile - creates FileChooser
     * @return path - allows image and canvas to reach File Explorer
     * @return newfile - saved file becomes new file on File Explorer
     */
    public static void saveas(Canvas canvas, File filesave) {
        FileChooser savefile = new FileChooser(); // brings up File Explorer
        savefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        // files of various types are eligible to be saved
        savefile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "."),
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter(",.jpg", "*.jpg*"),
                new FileChooser.ExtensionFilter(".jpeg", "*.jpeg"),
                new FileChooser.ExtensionFilter(".png", "*.png"));
        filesave = savefile.showSaveDialog(stage);
        path = filesave.toURI().toString();
        if (filesave != null) {          
            try {
                WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null,wi);
                RenderedImage ri = SwingFXUtils.fromFXImage(wi,null);
                ImageIO.write(ri,"png", filesave); // various files types to save
                ImageIO.write(ri,"jpg", filesave);
                ImageIO.write(ri,"gif", filesave);
                newfile = filesave;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Returns ability to save canvas and image.
     * @param canvas - convert canvas into saveable data type
     * @param newfile - allows new save file to overwrite current save file
     * @return save(canvas, newfile) - save canvas and image (overwrite)
     * @return newimage - allows canvas to be overwritten
     */
    public static void save(Canvas canvas, File newfile) {
        if (newfile != null) {
            try {
                WritableImage newimage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, newimage);
                RenderedImage render = SwingFXUtils.fromFXImage(newimage, null);
                ImageIO.write(render, "png", newfile);
                ImageIO.write(render, "jpg", newfile);
                ImageIO.write(render, "gif", newfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Returns Alert message that warns user to save before exiting.
     * @return savemessage - indicates that user should save before exiting
     * @see exitmessage - displays all information necessary before user exits
     */
    public static void exit() {
        Alert exitmessage = new Alert(Alert.AlertType.WARNING); // displays warning message
        exitmessage.setTitle("You are about to exit Paint");
        exitmessage.setHeaderText("Before you leave...");
        String savemessage = "Do you want to save your changes?";
        exitmessage.setContentText(savemessage);
              
        ButtonType saveasbutton = new ButtonType("Save As"); // options prior to exiting Paint
        ButtonType savebutton = new ButtonType("Save");
        ButtonType exitbutton = new ButtonType("Exit");
        ButtonType cancelbutton = new ButtonType("Cancel");
        exitmessage.getButtonTypes().setAll(saveasbutton, savebutton, exitbutton, cancelbutton);
        Optional<ButtonType> result = exitmessage.showAndWait();
               
        if (result.get() == saveasbutton) {
            FileManager.saveas(canvas, filesave);
            System.exit(0);
            stage.close();
        }
        else if (result.get() == savebutton) {
            FileManager.save(canvas, newfile);
            System.exit(0);
            stage.close();
        }
        else if (result.get() == exitbutton) {
            System.exit(0);
            stage.close();
        }
        else {
        }
    }
    
}