package token;

public class Token {
    public Categoria category;
    public int line;
    public int column;
    public String lexicalValue;

    public Token(Categoria category, int line, int column, String lexicalValue) {
        this.category = category;
        this.line = line;
        this.column = column;
        this.lexicalValue = lexicalValue;
    }

}
