package fileManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    private BufferedReader bufferReader = null;

    private String currentLine = null;
    private int currentLineIndex = 0;

    public FileManager(String file) {
        try {
            this.bufferReader = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException e){
            System.err.println("O arquivo não pode ser encontrado: " + file);
        }
    }

    public String nextLine(){
        try {
            currentLine = bufferReader.readLine();
            if (currentLine != null) {
                currentLineIndex++;
                return currentLine;
            } else {
                return "EOF";
            }
        }catch (IOException e){
            System.err.println("Erro ao ler a próxima linha");
        }
        return null;
    }

    public String currentLine(){
        return currentLine;
    }

    public int currentLineIndex(){
        return currentLineIndex;
    }

}
