import lexicalAnalyzer.LexicalAnalyzer;
import token.Category;
import token.Token;

public class Main {

    public static void main(String args[]) {
        String file = "/Users/joaocorreia/fib.tc";

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(file);
        Token token = lexicalAnalyzer.nextToken();
        while (token.getCategory() != Category.EOF){
            token = lexicalAnalyzer.nextToken();
            token.printFormatedToken();
        }
    }
}