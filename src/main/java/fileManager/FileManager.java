package fileManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    private BufferedReader bufferReader = null;

    private String line = null;
    private int lineIndex = 0;

    public FileManager(String file) {
        try {
            this.bufferReader = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException e){
            System.err.println("O arquivo não pode ser encontrado: " + file);
        }
    }

    public String nextLine(){
        try {
            line = bufferReader.readLine();
            if (line != null) {
                lineIndex++;
                return line;
            } else {
                return "EOF";
            }
        }catch (IOException e){
            System.err.println("Erro ao ler a próxima linha");
        }
        return null;
    }

    public String getLine(){
        return line;
    }

    public int getLineIndex(){
        return lineIndex;
    }

}
