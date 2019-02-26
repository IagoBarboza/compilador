package token;

public class Token {
    private Category category;
    private int line;
    private int column;
    private String lexicalValue;

    public Token(Category category, int line, int column, String lexicalValue) {
        this.category = category;
        this.line = line;
        this.column = column;
        this.lexicalValue = lexicalValue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getLexicalValue() {
        return lexicalValue;
    }

    public void setLexicalValue(String lexicalValue) {
        this.lexicalValue = lexicalValue;
    }

    @Override
    public String toString() {
        return "Token{" + "category=" + category + ", line=" + line + ", column=" + column + ", lexicalValue='" + lexicalValue + '\'' + '}';
    }


}