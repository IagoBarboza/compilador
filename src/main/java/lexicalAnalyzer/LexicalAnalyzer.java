package lexicalAnalyzer;

import fileManager.FileManager;
import token.Categoria;
import token.Token;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    private String file = null;
    private String currentLine = null;
    private FileManager fileManager = null;

    public LexicalAnalyzer(String file) {
        this.file = file;
        this.fileManager = new FileManager(file);
    }

    public Token nextToken(){
        Token token = null;

        //Lê a primeira linha do arquivo
        String line = fileManager.nextLine();
    findToken(line);
        // Implementar a busca por tokens
        //Retornar um token
        return token;
    }

    public Token findToken(String line){

        String letra = "[A-Za-z]";
        String digito = "[0-9]";
        String simbolo = "!|#|\\$|%|&|\\”|\\(|\\)|\\*|\\+|\\,|\\-|\\.|\\/|:|;|<|=|>|\\?|@|[|]|\\^|_|\\‘|\\{|\\||}|\\∼";

        Map<Pattern, Categoria> pattern = new LinkedHashMap<Pattern, Categoria>();

        pattern.put(Pattern.compile("main"), Categoria.MAIN);
        pattern.put(Pattern.compile("void"), Categoria.VOID);
        pattern.put(Pattern.compile("int"), Categoria.TIP_INT);
        pattern.put(Pattern.compile("float"), Categoria.TIP_FLO);
        pattern.put(Pattern.compile("char"), Categoria.TIP_CHA);
        pattern.put(Pattern.compile("boolean"), Categoria.TIP_BOO);
        pattern.put(Pattern.compile("string"), Categoria.TIP_STR);
        pattern.put(Pattern.compile("return"), Categoria.RETURN);
        pattern.put(Pattern.compile("if"), Categoria.IF);
        pattern.put(Pattern.compile("else"), Categoria.ELSE);
        pattern.put(Pattern.compile("for"), Categoria.FOR);
        pattern.put(Pattern.compile("in"), Categoria.IN);
        pattern.put(Pattern.compile("to"), Categoria.TO);
        pattern.put(Pattern.compile("step"), Categoria.STEP);
        pattern.put(Pattern.compile("while"), Categoria.WHILE);
        pattern.put(Pattern.compile("var"), Categoria.VAR);
        pattern.put(Pattern.compile("read"), Categoria.INPUT);
        pattern.put(Pattern.compile("print"), Categoria.OUTPUT);
        pattern.put(Pattern.compile("fun"), Categoria.FUN);
        pattern.put(Pattern.compile("continue"), Categoria.CONTINUE);
        pattern.put(Pattern.compile("break"), Categoria.BREAK);

        pattern.put(Pattern.compile(digito+"+"),
                Categoria.CON_INT);
        pattern.put(Pattern.compile("\\["+digito+"(,"+digito+"+)*\\]"),
                Categoria.CON_INT_CAD);
        pattern.put(Pattern.compile(digito+"+\\."+digito+"+"),
                Categoria.CON_FLO);
        pattern.put(Pattern.compile("\\["+digito+"+\\."+digito+"+(,"+digito+"+\\."+digito+"+)*\\]"),
                Categoria.CON_FLO_CAD);
        pattern.put(Pattern.compile("true|false"),
                Categoria.CON_BOO);
        pattern.put(Pattern.compile("\\[true|false(,(true|false))*\\]"),
                Categoria.CON_BOO_CAD);
        pattern.put(Pattern.compile("'("+ letra + "|" + digito + "|" + simbolo + ")'"),
                Categoria.CON_CHA);
        pattern.put(Pattern.compile("\\['("+ letra + "|" + digito + "|" + simbolo + ")'"+
                        "(,'("+ letra + "|" + digito + "|" + simbolo + ")')*\\]"),
                Categoria.CON_CHA_CAD);
        pattern.put(Pattern.compile("\"("+ letra + "|" + digito + "|" + simbolo + ")*\""),
                Categoria.CON_STR);
        pattern.put(Pattern.compile("\\[\"("+ letra + "|" + digito + "|" + simbolo + ")*\"" +
                        "(,\"("+ letra + "|" + digito + "|" + simbolo + ")*\")*\\]"),
                Categoria.CON_STR_CAD);

        pattern.put(Pattern.compile("\\)"), Categoria.FEC_PAR);
        pattern.put(Pattern.compile("\\("), Categoria.ABR_PAR);
        pattern.put(Pattern.compile("\\}"), Categoria.FEC_CHA);
        pattern.put(Pattern.compile("\\{"), Categoria.ABR_CHA);
        pattern.put(Pattern.compile("\\["), Categoria.ABR_COL);
        pattern.put(Pattern.compile("\\]"), Categoria.FEC_COL);
        pattern.put(Pattern.compile("\\+"), Categoria.OPE_ADI);
        pattern.put(Pattern.compile("-"), Categoria.OPE_SUB);
        pattern.put(Pattern.compile("\\*"), Categoria.OPE_MUL);
        pattern.put(Pattern.compile("\\/"), Categoria.OPE_DIV);
        pattern.put(Pattern.compile("=="), Categoria.OPE_IGU);
        pattern.put(Pattern.compile("!="), Categoria.OPE_DIF);
        pattern.put(Pattern.compile("<"), Categoria.OPE_MEN);
        pattern.put(Pattern.compile(">"), Categoria.OPE_MAI);
        pattern.put(Pattern.compile("<="), Categoria.OPE_MEN_IGU);
        pattern.put(Pattern.compile(">="), Categoria.OPE_MAI_IGU);
        pattern.put(Pattern.compile("\\|\\|"), Categoria.OPE_OU);
        pattern.put(Pattern.compile("&&"), Categoria.OPE_E);
        pattern.put(Pattern.compile("!"), Categoria.OPE_NEG);
        pattern.put(Pattern.compile("\\+\\+"), Categoria.OPE_CON);
        pattern.put(Pattern.compile(","), Categoria.VIRGULA);
        pattern.put(Pattern.compile(";"), Categoria.PON_VIR);
        pattern.put(Pattern.compile("="), Categoria.ATRIBUICAO);

        pattern.put(Pattern.compile(letra + "(" + letra + "|" + digito +")*"),
                Categoria.IDENTIFICADOR);


        //Remove spaces at the begin of line, and calculates how much spaces were removed
        String noSpacesAtBeginLine = line.replaceFirst("\\s*", "");
        int removedSpacesAtBegin = line.length() - noSpacesAtBeginLine.length();

        for (Map.Entry<Pattern, Categoria> element : pattern.entrySet()) {

            Matcher matcher = element.getKey().matcher(noSpacesAtBeginLine);

            if(matcher.find()){
                if(matcher.start() == 0) {
                    System.out.println(element.getValue().toString());
                    return null;
                }
            }
        }

        Token token = null;
        return token;
    }

}
