package lexicalAnalyzer;

import fileManager.FileManager;
import token.Category;
import token.Token;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    private  Map<Pattern, Category> pattern = null;
    private FileManager fileManager = null;
    private String input = null;
    private int columnIndex = 0;
    private int lineIndex = 0;


    public LexicalAnalyzer(String file) {
        pattern = new LinkedHashMap<Pattern, Category>();

        String letra = "[A-Za-z]";
        String digito = "[0-9]";
        String simbolo = "\\s|!|#|\\$|%|&|\\”|\\(|\\)|\\*|\\+|\\,|\\-|\\.|\\/|:|;|<|=|>|\\?|@|[|]|\\^|_|\\‘|\\{|\\||}|\\∼";

        pattern.put(Pattern.compile(digito+"+"), Category.CON_INT);
        pattern.put(Pattern.compile(digito+"+\\."+digito+"+"), Category.CON_FLO);
        pattern.put(Pattern.compile("true|false"), Category.CON_BOO);
        pattern.put(Pattern.compile("'("+ letra + "|" + digito + "|" + simbolo + ")'"), Category.CON_CHA);
        pattern.put(Pattern.compile("\"("+ letra + "|" + digito + "|" + simbolo + ")*\""), Category.CON_STR);


        pattern.put(Pattern.compile("void"), Category.VOID);
        pattern.put(Pattern.compile("int"), Category.TIP_INT);
        pattern.put(Pattern.compile("float"), Category.TIP_FLO);
        pattern.put(Pattern.compile("char"), Category.TIP_CHA);
        pattern.put(Pattern.compile("boolean"), Category.TIP_BOO);
        pattern.put(Pattern.compile("string"), Category.TIP_STR);
        pattern.put(Pattern.compile("return"), Category.RETURN);
        pattern.put(Pattern.compile("if"), Category.IF);
        pattern.put(Pattern.compile("else"), Category.ELSE);
        pattern.put(Pattern.compile("for"), Category.FOR);
        pattern.put(Pattern.compile("in"), Category.IN);
        pattern.put(Pattern.compile("to"), Category.TO);
        pattern.put(Pattern.compile("step"), Category.STEP);
        pattern.put(Pattern.compile("while"), Category.WHILE);
        pattern.put(Pattern.compile("var"), Category.VAR);
        pattern.put(Pattern.compile("read"), Category.INPUT);
        pattern.put(Pattern.compile("print"), Category.OUTPUT);
        pattern.put(Pattern.compile("fun"), Category.FUN);
        pattern.put(Pattern.compile("continue"), Category.CONTINUE);
        pattern.put(Pattern.compile("break"), Category.BREAK);
        pattern.put(Pattern.compile("EOF"), Category.EOF);
        pattern.put(Pattern.compile("\\)"), Category.FEC_PAR);
        pattern.put(Pattern.compile("\\("), Category.ABR_PAR);
        pattern.put(Pattern.compile("\\}"), Category.FEC_CHA);
        pattern.put(Pattern.compile("\\{"), Category.ABR_CHA);
        pattern.put(Pattern.compile("\\["), Category.ABR_COL);
        pattern.put(Pattern.compile("\\]"), Category.FEC_COL);
        pattern.put(Pattern.compile("\\+\\+"), Category.OPE_CON);
        pattern.put(Pattern.compile("%% | %"), Category.OPE_LIM);
        pattern.put(Pattern.compile("==|!=|<=|>=|>|<"), Category.OPE_REL);
        pattern.put(Pattern.compile("\\|\\||&&"), Category.OPE_LOG);
        pattern.put(Pattern.compile("\\+|-"), Category.OPE_ADI);
        pattern.put(Pattern.compile("\\*|\\/"), Category.OPE_MUL);
        pattern.put(Pattern.compile("!"), Category.OPE_NEG);
        pattern.put(Pattern.compile(","), Category.VIRGULA);
        pattern.put(Pattern.compile(";"), Category.PON_VIR);
        pattern.put(Pattern.compile("="), Category.ATRIBUICAO);

        pattern.put(Pattern.compile(letra + "(" + letra + "|" + digito +")*"), Category.IDENTIFICADOR);

        this.fileManager = new FileManager(file);
        this.nextLine();
        this.printFormatedLine();
    }

    public Token nextToken(){
        Token token = null;
        if (columnIndex < input.length()) {

            token = findToken(input, columnIndex);

            if (token != null) {

                columnIndex = token.getColumn() - 1 + token.getLexicalValue().length();

                //talvez não precise
                if (columnIndex > input.length()) {
                    nextLine();
                    printFormatedLine();
                    return nextToken();
                }

            } else {
                System.err.println("Token desconhecido!");
            }
        } else {
            nextLine();
            printFormatedLine();
            return nextToken();
        }

        return token;
    }

    private Token findToken(String line, int index){

        //Remove spaces at the begin of line, and calculates how much spaces were removed
        String noSpacesAtBeginLine = line.substring(index).replaceFirst("\\s*", "");
        int removedSpacesAtBegin = line.substring(index).length() - noSpacesAtBeginLine.length();

        Token token = null;
        for (Map.Entry<Pattern, Category> element : pattern.entrySet()) {

            Matcher matcher = element.getKey().matcher(noSpacesAtBeginLine);

            if(matcher.find()){
                if(matcher.start() == 0) {
                    token = new Token(element.getValue(), lineIndex, index + removedSpacesAtBegin + matcher.start() + 1, matcher.group());
                    return token;
                }
            }
        }

        return token;
    }

    private void nextLine(){
        this.input = removeCommentsAndSpacesAtEndOfLine(fileManager.nextLine());
        this.lineIndex = fileManager.getLineIndex();
        this.columnIndex = 0;
    }

    private String removeCommentsAndSpacesAtEndOfLine(String line){
        return line.replaceAll("//.*", "").replaceAll("\\s*$", "");
    }

    private void printFormatedLine(){
        System.out.printf("%4d  %s\n", fileManager.getLineIndex(), input);
    }
}
