package editor;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            textEditor.currentFile = file;

            String theText = "";

            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    theText = theText.concat(scanner.nextLine() + "\n");
                }
                textArea.setText(theText);
            } catch(FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (FileWriter fileWriter = new FileWriter(file)) {

                    fileWriter.write(textArea.getText());
                    textEditor.currentFile = file;

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor, "Error: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else{
            try (FileWriter fileWriter = new FileWriter(textEditor.currentFile)) {
                fileWriter.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "Error: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");

        textEditor.currentFile = null;
    }
}
