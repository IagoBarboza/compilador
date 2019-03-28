import lexicalAnalyzer.LexicalAnalyzer;
import token.Category;
import token.Token;

public class Main {

    public static void main(String args[]) {
        Token token = null;
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(args[0]);

        do {
            token = lexicalAnalyzer.nextToken();
            token.printFormatedToken();
        } while (token.getCategory() != Category.EOF);
    }
}