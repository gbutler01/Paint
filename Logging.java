package com.mycompany.paint;

import javafx.scene.control.TextArea;

public class Logging extends App {
    
    public final TextArea textArea;

    private boolean verboseMode = false;
    private boolean debugMode = false;

    public Logging(final TextArea textArea) {
        this.textArea = textArea;
    }

    public Logging setVerboseMode(boolean value) {
        verboseMode = value;
        return this;
    }

    public Logging setDebugMode(boolean value) {
        debugMode = value;
        return this;
    }

    public boolean writeMessage(String msg) {
        textArea.appendText(msg);
        return true;
    }

    public boolean logMessage(String msg) {
        return writeMessage(msg + "\n");
    }

    public boolean logWarning(String msg) {
        return writeMessage("Warning: " + msg + "\n");
    }

    public boolean logError(String msg) {
        return writeMessage("Error: " + msg + "\n");
    }

    public boolean logVerbose(String msg) {
        return verboseMode ? writeMessage(msg + "\n") : true;
    }

    public boolean logDebug(String msg) {
        return debugMode ? writeMessage("[DEBUG] " + msg + "\n") : true;
    }
    
}