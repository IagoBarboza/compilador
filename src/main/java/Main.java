import lexicalAnalyzer.LexicalAnalyzer;
import token.Category;
import token.Token;

public class Main {

    public static void main(String args[]) {
        String file = "/Users/myron/shell.tc";

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(file);
        Token token = lexicalAnalyzer.nextToken();
        while (token.getCategory() != Category.EOF){
            System.out.printf("        [%04d, %04d] (%04d, %20s) {%s}\n", token.getLine(), token.getColumn(), token.getCategory().getValue(), token.getCategory(), token.getLexicalValue());
            token = lexicalAnalyzer.nextToken();
        }
    }
}