package com.mycompany.paint;

import javafx.application.Application; // needed to run application
import javafx.event.ActionEvent; // necessary for button commands
import javafx.event.EventHandler; // necessary for button commands
import javafx.scene.Scene; // visual set-up
import javafx.scene.control.Menu; // menu imports
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image; // image imports
import javafx.stage.FileChooser; // allows choice of files to open
import javafx.stage.FileChooser.ExtensionFilter; // allows choice of files to open
import javafx.stage.Stage; // window to contain objects
import java.io.File;
import javafx.scene.canvas.Canvas; // allows for image crafting
import javafx.scene.canvas.GraphicsContext; // allows visual design for canvas
import javafx.scene.control.ColorPicker; // establishes color choice in program
import javafx.scene.layout.BorderPane; // layout
import javafx.scene.layout.VBox; // layout
import javafx.scene.control.ScrollPane; // allows scroll bars
import javafx.scene.paint.Color; // modify color in elements
import javafx.geometry.Orientation;
import javafx.scene.Cursor; // toggles cursor when using color grabber
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text; // establishes message for colorpicker
import javafx.scene.control.ToolBar; // holds colorpicker
import javafx.scene.control.Slider; // control of line width
import javafx.scene.control.Tab; // allows for tab display
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField; // text tool
import javafx.scene.control.ToggleButton; // toggles color grabber, etc.
import javafx.scene.input.KeyCode; // keyboard UI control imports
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import java.util.Scanner; // allow for user input
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Runs program with all features on canvas.
 * @see stage - allow use of all features presented in code 
 */
public class App extends Application {
    
    // variables for function use
    public static Group root;
    public static VBox vbox;
    public static Scene scene;
    public static Stage stage, showtimer;
    public static BorderPane borderPane;
    public static TabPane tabpane;
    public static Tab firsttab, secondtab, thirdtab;
    public static MenuBar menubar;
    public static Menu menu, helpmenu, editmenu;
    public static MenuItem fileimport, save, saveas, exit, help, about, undo, redo, copy, paste;
    public static FileChooser choosefile;
    public static ScrollPane scroll;
    public static double linesize = 1;
    public static String path;
    public static File filesave, newfile;
    public static double x1, y1, x2, y2;
    public static double zoom = 0.75;
    public static ToggleButton colortransfer;
    public static ColorPicker colorPicker, colorFill;
    public static Canvas canvas;
    public static GraphicsContext visuals;
    public static Button freedraw, redorangedraw, yellowgreendraw,
            bluepurpledraw, straightdraw, drawsquare, drawrectangle,
            drawellipse, drawcircle, drawroundrect, /*drawpolygon,*/ eraser,
            zoomin, zoomout, /*imagepiece,*/ defaultcanvas;
    public static Slider linewidth;
    public static TextField texttool;
    public static Label selectedtool;
    public static ToolBar toolbar, verticaltoolbar;
    private static final FileManager managefiles = new FileManager();
    private static boolean transparency = false;
    private static boolean[][] pixeltransparent = null;
    private final String tempfile = "tempfile.png";
    private static Tooltip freetip, redorangetip, yellowgreentip, bluepurpletip,
            straighttip, squaretip, rectangletip, ellipsetip,
            circletip, roundrecttip, polygontip, erasetip, zoomintip, zoomouttip,
            imagepiecetip, blanktip;
    private static Image freeicon, straighticon, squareicon, rectangleicon, ellipseicon,
            circleicon, roundrecticon, polygonicon, eraseicon, zoominicon, zoomouticon,
            imagepieceicon, blankicon;
    public static Image copyimage;
    private static ImageView seefree, seestraight, seesquare, seerectangle, seeellipse,
            seecircle, seeroundrect, seepolygon, seeerase, seezoomin, seezoomout,
            seeimagepiece, seeblank;
    public static Stop[] rainbow;
    public static LinearGradient rainbowline;
    public static Clipboard clipboard;
    //public static MediaPlayer musicplayer;
    
    /**
     * Returns program with all features.
     * @see stage - allow use of all features presented in code 
     */
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Paint"); // name program
        //Music.music(); // plays music
        
        root = new Group();
        borderPane = new BorderPane(); // establish layout
        vbox = new VBox();
        scene = new Scene(borderPane, 500, 400); // visual set-up
        
        tabpane = new TabPane(); // tab layout
        firsttab = new Tab("Tab #1"); // individual tabs
        secondtab = new Tab("Tab #2");
        thirdtab = new Tab("Tab #3");
        tabpane.getTabs().addAll(firsttab, secondtab, thirdtab); // display tabs
                                               
        menubar = new MenuBar(); // create menu bar object
        
        menu = new Menu("File"); // menu object & name
        menubar.getMenus().add(menu); // add menu to menu bar
        
        helpmenu = new Menu("Help"); // help menu
        menubar.getMenus().add(helpmenu); // add help menu to menu bar
        
        editmenu = new Menu("Edit");
        menubar.getMenus().add(editmenu);
               
        canvas = new Canvas(); // create canvas object
        visuals = canvas.getGraphicsContext2D(); // permit visual design
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, 1500, 1500);
        borderPane.setTop(vbox); // establishes menu bar
        root.getChildren().add(canvas);
        
        scroll = new ScrollPane(canvas); // create scroll bars
                                                      
        stage.setScene(scene); // update code for layout
        stage.show();
                      
        fileimport = new MenuItem("Import File"); // create menu items
        save = new MenuItem("Save");
        saveas = new MenuItem("Save As");
        exit = new MenuItem("Exit");
        //Adding all the menu items to the file menu
        menu.getItems().addAll(fileimport, save, saveas, exit);
        
        choosefile = new FileChooser(); // set up file chooser
        choosefile.setTitle("Choose File");
        choosefile.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*")); // allow all files to be chosen for display
        borderPane.setCenter(scroll); // allow images to be displayed with scroll bars
        
        help = new MenuItem("Help"); // instructions for program
        about = new MenuItem("About"); // name of program, programmer, version
        helpmenu.getItems().addAll(help, about);
        
        undo = new MenuItem("Undo");
        redo = new MenuItem("Redo");
        copy = new MenuItem("Copy");
        paste = new MenuItem("Paste");
        editmenu.getItems().addAll(undo, redo, copy, paste);
        
        freedraw = new Button("Free Draw"); // draw tools/shape options + icons
        redorangedraw = new Button("Rainbow Draw #1");
        yellowgreendraw = new Button("Rainbow Draw #2");
        bluepurpledraw = new Button("Rainbow Draw #3");
        straightdraw = new Button("Straight Draw (Pencil Tool)");
        drawsquare = new Button("Square");
        drawrectangle = new Button("Rectangle");
        drawellipse = new Button("Ellipse");
        drawcircle = new Button("Circle");
        drawroundrect = new Button("Round Rectangle");
        //drawpolygon = new Button("Other Polygons...");
        eraser = new Button("Eraser");
        zoomin = new Button("Zoom In"); // zoom objects
        zoomout = new Button("Zoom Out");        
        //imagepiece = new Button("Select/Move Image Section"); // select and move piece of an image
        defaultcanvas = new Button("Default Canvas"); // sets up white frame for canvas
        

        // mouseover explanations for drawing tools
        freetip = new Tooltip("Press to draw on canvas freely");
        Tooltip.install(freedraw, freetip);
        redorangetip = new Tooltip("Press to draw red/orange gradient on canvas");
        Tooltip.install(redorangedraw, redorangetip);
        yellowgreentip = new Tooltip("Press to draw yellow/green gradient on canvas");
        Tooltip.install(yellowgreendraw, yellowgreentip);
        bluepurpletip = new Tooltip("Press to draw blue/purple gradient on canvas");
        Tooltip.install(bluepurpledraw, bluepurpletip);
        straighttip = new Tooltip("Press to draw straight line on canvas");
        Tooltip.install(straightdraw, straighttip);
        squaretip = new Tooltip("Press to draw square on canvas");
        Tooltip.install(drawsquare, squaretip);
        rectangletip = new Tooltip("Press to draw rectangle on canvas");
        Tooltip.install(drawrectangle, rectangletip);
        ellipsetip = new Tooltip("Press to draw ellipse on canvas");
        Tooltip.install(drawellipse, ellipsetip);
        circletip = new Tooltip("Press to draw circle on canvas");
        Tooltip.install(drawcircle, circletip);
        roundrecttip = new Tooltip("Press to draw round rectangle on canvas");
        Tooltip.install(drawroundrect, roundrecttip);
//        polygontip = new Tooltip("Press to draw other polygons on canvas");
//        Tooltip.install(drawpolygon, polygontip);
        erasetip = new Tooltip("Press to erase the canvas");
        Tooltip.install(eraser, erasetip);
        zoomintip = new Tooltip("Press to zoom in on canvas");
        Tooltip.install(zoomin, zoomintip);
        zoomouttip = new Tooltip("Press to zoom out on canvas");
        Tooltip.install(zoomout, zoomouttip);
//        imagepiecetip = new Tooltip("Press to select a part of the image on the canvas and move it");
//        Tooltip.install(imagepiece, imagepiecetip);
        blanktip = new Tooltip("Press to create a blank canvas");
        Tooltip.install(defaultcanvas, blanktip);
        
        // button icons for drawing tools
//        freeicon = new Image("/Paint/Source Packages/ToolIcons/freedraw.jpg");
//        seefree = new ImageView(freeicon);
//        seefree.setFitWidth(15);
//        seefree.setFitHeight(15);
//        freedraw.setGraphic(seefree);
//        
//        straighticon = new Image("/Paint/Source Packages/ToolIcons/straightdraw.jpg");
//        seestraight = new ImageView(straighticon);
//        seestraight.setFitWidth(15);
//        seestraight.setFitHeight(15);
//        straightdraw.setGraphic(seestraight);
//        
//        squareicon = new Image("/Paint/Source Packages/ToolIcons/square.jpg");
//        seesquare = new ImageView(squareicon);
//        seesquare.setFitWidth(15);
//        seesquare.setFitHeight(15);
//        drawsquare.setGraphic(seesquare);
//        
//        rectangleicon = new Image("/Paint/Source Packages/ToolIcons/rectangle.jpg");
//        seerectangle = new ImageView(rectangleicon);
//        seerectangle.setFitWidth(15);
//        seerectangle.setFitHeight(15);
//        drawrectangle.setGraphic(seerectangle);
//        
//        ellipseicon = new Image("/Paint/Source Packages/ToolIcons/oval.jpg");
//        seeellipse = new ImageView(ellipseicon);
//        seeellipse.setFitWidth(15);
//        seeellipse.setFitHeight(15);
//        drawellipse.setGraphic(seeellipse);
//        
//        circleicon = new Image("/Paint/Source Packages/ToolIcons/circle.jpg");
//        seecircle = new ImageView(circleicon);
//        seecircle.setFitWidth(15);
//        seecircle.setFitHeight(15);
//        drawcircle.setGraphic(seecircle);
//        
//        roundrecticon = new Image("/Paint/Source Packages/ToolIcons/roundrectangle.jpg");
//        seeroundrect = new ImageView(roundrecticon);
//        seeroundrect.setFitWidth(15);
//        seeroundrect.setFitHeight(15);
//        drawroundrect.setGraphic(seeroundrect);
//        
//        polygonicon = new Image("/Paint/Source Packages/ToolIcons/polygon.jpg");
//        seepolygon = new ImageView(polygonicon);
//        seepolygon.setFitWidth(15);
//        seepolygon.setFitHeight(15);
//        drawpolygon.setGraphic(seepolygon);
//        
//        eraseicon = new Image("/Paint/Source Packages/ToolIcons/erase.jpg");
//        seeerase = new ImageView(eraseicon);
//        seeerase.setFitWidth(15);
//        seeerase.setFitHeight(15);
//        eraser.setGraphic(seeerase);
//        
//        zoominicon = new Image("/Paint/Source Packages/ToolIcons/zoomin.jpg");
//        seezoomin = new ImageView(zoominicon);
//        seezoomin.setFitWidth(15);
//        seezoomin.setFitHeight(15);
//        zoomin.setGraphic(seezoomin);
//        
//        zoomouticon = new Image("/Paint/Source Packages/ToolIcons/zoomout.jpg");
//        seezoomout = new ImageView(zoomouticon);
//        seezoomout.setFitWidth(15);
//        seezoomout.setFitHeight(15);
//        zoomout.setGraphic(seezoomout);
//        
//        imagepieceicon = new Image("/Paint/Source Packages/ToolIcons/imagepiece.jpg");
//        seeimagepiece = new ImageView(imagepieceicon);
//        seeimagepiece.setFitWidth(15);
//        seeimagepiece.setFitHeight(15);
//        imagepiece.setGraphic(seeimagepiece);
//        
//        blankicon = new Image("/Paint/Source Packages/ToolIcons/blank.jpg");
//        seeblank = new ImageView(blankicon);
//        seeblank.setFitWidth(15);
//        seeblank.setFitHeight(15);
//        defaultcanvas.setGraphic(seeblank);
        
                      
        linewidth = new Slider(); // object to control width of line drawn
        linewidth.setMin(10); // dimensions for line drawn
        linewidth.setMax(75);
        linewidth.valueProperty().addListener((observe, original, newvalue) -> {
            linesize = newvalue.doubleValue();
        });

        // establish colorpicker object, default color, set in borderpane, toolbar, & vbox
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        final Text choose = new Text("Choose a color");
        choose.setFill(colorPicker.getValue());
                
        borderPane.getChildren().addAll(colorPicker, choose/*, colorFill, choosefill*/);
        
        // specify color to be grabbed and dropped
        colortransfer = new ToggleButton("Transfer Color");
        colortransfer.selectedProperty().addListener(((observe, originalvalue, newvalue) -> {
            if (newvalue) {
                scene.setCursor(Cursor.CROSSHAIR);
            } else {
                scene.setCursor(Cursor.DEFAULT);
            }
        }));
        
        texttool = new TextField(); // text tool object
        selectedtool = new Label("Select a tool!");
                
        toolbar = new ToolBar(colorPicker, texttool, selectedtool);
        verticaltoolbar = new ToolBar(freedraw, redorangedraw, yellowgreendraw,
                                      bluepurpledraw, straightdraw, drawsquare, 
                                      drawrectangle, drawellipse, drawcircle, 
                                      drawroundrect, /*drawpolygon,*/ eraser, zoomin, zoomout, 
                                      /*imagepiece, colortransfer,*/ defaultcanvas);
        verticaltoolbar.setOrientation(Orientation.VERTICAL); // display drawing options on left side
        vbox.getChildren().addAll(menubar, toolbar, linewidth, tabpane);
        borderPane.setLeft(verticaltoolbar);
                
        /**
         * Calls freeDraw() method from Draw.java
         */
        freedraw.setOnAction(new EventHandler<ActionEvent>() { // allows free drawing
            public void handle(ActionEvent event) {
                Draw.freeDraw();
                selectedtool.setText("Free Draw");
            }
        });
        
        /**
         * Calls redorangeDraw() method from Draw.java
         */
        redorangedraw.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Draw.redorangeDraw();
                selectedtool.setText("Rainbow Draw #1");
            } 
        });
        
        /**
         * Calls yellowgreenDraw() method from Draw.java
         */
        yellowgreendraw.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Draw.yellowgreenDraw();
                selectedtool.setText("Rainbow Draw #2");
            } 
        });
        
        /**
         * Calls bluepurpleDraw() method from Draw.java
         */
        bluepurpledraw.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Draw.bluepurpleDraw();
                selectedtool.setText("Rainbow Draw #3");
            } 
        });
        
        /**
         * Calls straightDraw() method from Draw.java
         */
        straightdraw.setOnAction(new EventHandler<ActionEvent>() { // draw straight line (pencil tool)
            public void handle(ActionEvent event) {
                Draw.straightDraw();
                selectedtool.setText("Straight Draw");
            }
        });
        
        /**
         * Calls drawSquare() method from Draw.java
         */
        drawsquare.setOnAction(new EventHandler<ActionEvent>() { // create square on canvas
            public void handle(ActionEvent event) {
                Draw.drawSquare();
                selectedtool.setText("Square");
            }
        });
        
        /**
         * Calls drawRectangle() method from Draw.java
         */
        drawrectangle.setOnAction(new EventHandler<ActionEvent>() { // create rectangle on canvas 
            public void handle(ActionEvent event) {
                Draw.drawRectangle();
                selectedtool.setText("Rectangle");
            }
        });
        
        /**
         * Calls drawEllipse() method from Draw.java
         */
        drawellipse.setOnAction(new EventHandler<ActionEvent>() { // create oval on canvas
            public void handle(ActionEvent event) {
                Draw.drawEllipse();
                selectedtool.setText("Ellipse");
            }
        });
        
        /**
         * Calls drawCircle() method from Draw.java
         */
        drawcircle.setOnAction(new EventHandler<ActionEvent>() { // create circle on canvas
            public void handle(ActionEvent event) {
                Draw.drawCircle();
                selectedtool.setText("Circle");
            }
        });
        
        /**
         * Calls drawRoundRect() method from Draw.java
         */
        drawroundrect.setOnAction(new EventHandler<ActionEvent>() { // create round rectangle on canvas 
            public void handle(ActionEvent event) {
                Draw.drawRoundRect();
                selectedtool.setText("Round Rectangle");
            }
        });
        
        /**
         * Calls drawPolygon() method from Draw.java
         */
//        drawpolygon.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                Draw.drawPolygon();
//                selectedtool.setText("Make your own Polygon!");
//            }
//        });
        
        /**
         * Calls erase() method from Draw.java
         */
        eraser.setOnAction(new EventHandler<ActionEvent>() { // allows erasing of colors/images
            public void handle(ActionEvent event) {
                Draw.erase();
                selectedtool.setText("Erase");
            }
        });
        
        /**
         * Calls zoomin() method from Tools.java
         */
        zoomin.setOnAction(new EventHandler<ActionEvent>() { // zoom in, enlarge canvas
            public void handle(ActionEvent event) {
                Tools.zoomin();
            }
        });

        /**
         * Calls zoomout() method from Tools.java
         */
        zoomout.setOnAction(new EventHandler<ActionEvent>() { // zoom out, make canvas smaller
            public void handle(ActionEvent event) {
                Tools.zoomout();
            }
        });
        
        /**
         * Calls imagepiece() method from Tools.java
         */
//        imagepiece.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                Tools.imagepiece();
//            }
//        });
        
        /**
         * Calls defaultcanvas() method from Tools.java
         */
        defaultcanvas.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Tools.defaultcanvas();
            }
        });
                                      
        stage.setScene(scene);
        stage.show();
               
        /**
         * Calls fileimport() method from FileManager.java
         */
        fileimport.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FileManager.fileimport();
            }
        });
        
        /**
         * Calls help() method from Tools.java
         */
        help.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Tools.help();
            }
        });

        /**
         * Calls about() method from Tools.java
         */
        about.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
               Tools.about();
           }
        });
                
        /**
         * Calls saveas() method from FileManager.java
         */
        saveas.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               FileManager.saveas(canvas, filesave); 
            }
        });
        
        /**
         * Calls save() method from FileManager.java
         */
        save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               FileManager.save(canvas, newfile);
            }
        });
                                                      
        /**
         * Calls exit() method from FileManager.java
         */
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FileManager.exit();
            }
        });
        
        /**
         * Calls undo() method from Tools.java
         */
        undo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Tools.undo();
            }
        });
        
        /**
         * Calls redo() method from Tools.java
         */
        redo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Tools.redo();
            }
        });
        
        /**
         * Calls copy() method from Tools.java
         */
        copy.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Tools.copy();
            }
        });
        
        /**
         * Calls paste() method from Tools.java
         */
        paste.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Tools.paste();
            }
        });
        
        // Keyboard UI Controls
        save.setAccelerator(new KeyCodeCombination (KeyCode.S,KeyCombination.CONTROL_DOWN));
        saveas.setAccelerator (new KeyCodeCombination (KeyCode.S,KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN));
        fileimport.setAccelerator(new KeyCodeCombination (KeyCode.F,KeyCombination.ALT_DOWN));
        exit.setAccelerator(new KeyCodeCombination (KeyCode.SPACE,KeyCombination.ALT_DOWN));
        undo.setAccelerator(new KeyCodeCombination (KeyCode.Z,KeyCombination.CONTROL_DOWN));
        redo.setAccelerator(new KeyCodeCombination (KeyCode.Y,KeyCombination.CONTROL_DOWN));
        copy.setAccelerator(new KeyCodeCombination (KeyCode.C,KeyCombination.CONTROL_DOWN));
        paste.setAccelerator(new KeyCodeCombination (KeyCode.V,KeyCombination.CONTROL_DOWN));

    }
    
    /**
     * Unit tests to assure working quality of program
     * @param x, a, b, c, d
     * @return x*x, a*c, a*b, a*d, b*c
     */
    public int add(int x) {
        return x+x;
    }
    
    public int Test1(int a, int b, int c, int d) {
        return a+b;
    }
    
    public int Test2(int a, int b, int c, int d) {
        return a+c;
    }
    
    public int Test3(int a, int b, int c, int d) {
        return a+d;
    }
    
    public int Test4(int a, int b, int c, int d) {
        return b+c;
    }
        
    public static void main(String[] args) {launch(args);}
}