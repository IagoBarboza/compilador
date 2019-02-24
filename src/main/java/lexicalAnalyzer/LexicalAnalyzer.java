package lexicalAnalyzer;

import fileManager.FileManager;
import token.Token;

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

        // Implementar a busca por tokens
        //Retornar um token
        return token;
    }

    public Token findToken(String line){

        String letra = "[A-Za-z]";
        String digito = "[0-9]";
        String simbolo = "!|#|\\$|%|&|\\”|\\(|\\)|\\*|\\+|\\,|\\-|\\.|\\/|:|;|<|=|>|\\?|@|[|]|\\^|_|\\‘|\\{|\\||}|\\∼";

        Pattern pMain = Pattern.compile("main");
        Pattern pVoid = Pattern.compile("void");
        Pattern pTipInt = Pattern.compile("int");
        Pattern pTipFlo = Pattern.compile("float");
        Pattern pTipCha = Pattern.compile("char");
        Pattern pTipBoo = Pattern.compile("boolean");
        Pattern pTipStr = Pattern.compile("string");
        Pattern pReturn = Pattern.compile("return");
        Pattern pIf = Pattern.compile("if");
        Pattern pElse = Pattern.compile("else");
        Pattern pFor = Pattern.compile("for");
        Pattern pIn = Pattern.compile("in");
        Pattern pTo = Pattern.compile("to");
        Pattern pStep = Pattern.compile("step");
        Pattern pWhile = Pattern.compile("while");
        Pattern pVar = Pattern.compile("var");
        Pattern pInput = Pattern.compile("input");
        Pattern oOutput = Pattern.compile("output");
        Pattern pFun = Pattern.compile("fun");
        Pattern pContinue = Pattern.compile("continue");
        Pattern pBreak = Pattern.compile("break");


        Pattern pFecPar = Pattern.compile("\\)");
        Pattern pAbrPar = Pattern.compile("\\(");
        Pattern pFecCha = Pattern.compile("\\}");
        Pattern pAbrCha = Pattern.compile("\\{");
        Pattern pAbrCol = Pattern.compile("\\[");
        Pattern pFecCol = Pattern.compile("\\]");
        Pattern pOpeAdi = Pattern.compile("\\+");
        Pattern pOpeSubNeg = Pattern.compile("-");
        Pattern pOpeMul = Pattern.compile("\\*");
        Pattern pOpeDiv = Pattern.compile("\\/");
        Pattern pOpeIgu = Pattern.compile("==");
        Pattern pOpeDif = Pattern.compile("!=");
        Pattern pOpeMen = Pattern.compile("<");
        Pattern pOpeMai = Pattern.compile(">");
        Pattern pOpeMenIgu = Pattern.compile("<=");
        Pattern pOpeMaiIgu = Pattern.compile(">=");
        Pattern pOpeOu = Pattern.compile("||");
        Pattern pOpeE = Pattern.compile("&&");
        Pattern pOpeNeg = Pattern.compile("!");
        Pattern pOpeCon = Pattern.compile("\\+\\+");
        Pattern pVir = Pattern.compile(",");
        Pattern pPonVir = Pattern.compile(";");
        Pattern pAtr = Pattern.compile("=");

        Pattern pIde = Pattern.compile(letra + "(" + letra + "|" + digito +")*");

        Pattern pConInt = Pattern.compile(digito+"+");
        Pattern pConIntCad = Pattern.compile("\\["+digito+"(,"+digito+"+)*\\]");

        Pattern pConFlo = Pattern.compile(digito+"+\\."+digito+"+");
        Pattern pConFloCad= Pattern.compile("\\["+digito+"+\\."+digito+"+(,"+digito+"+\\."+digito+"+)*\\]");

        Pattern pConBoo = Pattern.compile("true|false");
        Pattern pConBooCad= Pattern.compile("\\[true|false(,(true|false))*\\]");

        Pattern pConCha = Pattern.compile("'("+ letra + "|" + digito + "|" + simbolo + ")'");
        Pattern pConChaCad= Pattern.compile("\\['("+ letra + "|" + digito + "|" + simbolo + ")'"+
                                             "(,'("+ letra + "|" + digito + "|" + simbolo + ")')*\\]");

        Pattern pConStr = Pattern.compile("\"("+ letra + "|" + digito + "|" + simbolo + ")*\"");
        Pattern pConStrCad= Pattern.compile("\\[\"("+ letra + "|" + digito + "|" + simbolo + ")*\"" +
                                             "(,\"("+ letra + "|" + digito + "|" + simbolo + ")*\")*\\]");

        //Implementar a definição de todos os metchers
        Matcher mMain = pMain.matcher(line);
        Matcher mVoid = pVoid.matcher(line);

        //Busca o próximo token na linha recebida
        Token token = null;
        if(mMain.find()) {

        }else if(mVoid.find()){

        }

        return token;
    }

}
