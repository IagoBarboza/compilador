package syntacticAnalyzer;
import fileManager.FileManager;
import  lexicalAnalyzer.LexicalAnalyzer;
import token.Category;
import token.Token;

public class SyntacticAnalyzer {

    private LexicalAnalyzer lexAnalyzer;
    private Token token;
    private String file;

    public  SyntacticAnalyzer(String file){
        lexAnalyzer = new LexicalAnalyzer(file);
        nextToken();
        fProgram();
    }

    private void nextToken(){
        token = lexAnalyzer.nextToken();
    }

    public void analyzer(){
        fProgram();
    }


    //Program = LDecl
    public void fProgram(){
        System.out.printf("          %s\n", "Program = LDecl");
        fLDecl();
    }

    //LDecl = Decl | LDeclr
    public void fLDecl(){
        System.out.printf("          %s\n", "LDecl = Decl | LDeclr");
        fDecl();
        fLDeclr();
    }

    //LDeclr = Decl LDeclr | epsilon
    public void fLDeclr(){
        System.out.printf("          %s\n", "LDeclr = Decl LDeclr | epsilon");

        if(token.getCategory() == Category.VAR || token.getCategory() == Category.FUN){
            fDecl();
            fLDeclr();
        }
    }

    //Decl = DeclVar | DeclFun
    public void fDecl(){
        System.out.printf("          %s\n", "Decl = DeclVar | DeclFun");
        if(token.getCategory() == Category.VAR){
            nextToken();
            fType();

            if(token.getCategory() == Category.IDENTIFICADOR){
                nextToken();
                fAtr();

                if(token.getCategory() == Category.PON_VIR){
                    nextToken();
                }
            }

        }else if(token.getCategory() == Category.FUN) {
            nextToken();
            fTypeF();
            if (token.getCategory() == Category.IDENTIFICADOR) {
                nextToken();
                if (token.getCategory() == Category.ABR_PAR) {
                    nextToken();
                    fLParam();

                    if (token.getCategory() == Category.FEC_PAR) {
                        nextToken();
                        if (token.getCategory() == Category.ABR_COL) {
                            nextToken();
                            fLSent();
                            if (token.getCategory() == Category.FEC_COL) {
                                nextToken();
                            }
                        }
                    }
                }
            }
        }
    }

    //DeclVar = 'var' Type 'id' Atr ';' | 'var' Type 'id' '[' ExpArit ']' Atr ';'
    public void fDeclVar(){

        if(token.getCategory() == Category.VAR){
            System.out.printf("          %s\n", "DeclVar = 'var' Type 'id' Atr ';'");
            nextToken();
            fType();
            if(token.getCategory() == Category.IDENTIFICADOR){
                nextToken();
                fAtr();
                if(token.getCategory() == Category.PON_VIR){
                    nextToken();
                }
            }
        }
        else if(token.getCategory() == Category.VAR){
            System.out.printf("          %s\n", "'var' Type 'id' '[' ExpArit ']' Atr ';'");
            nextToken();
            fType();
            if(token.getCategory() == Category.IDENTIFICADOR){
                nextToken();
                fAtr();
                if (token.getCategory() == Category.ABR_COL) {
                    nextToken();
                    fExpArit();
                    if (token.getCategory() == Category.FEC_COL) {
                        nextToken();
                        fAtr();
                        if(token.getCategory() == Category.PON_VIR){
                            nextToken();
                        }
                    }
                }
            }
        }

    }

    //Atr = '=' ExpConcat | epsilon
    public void fAtr(){

        if(token.getCategory() == Category.ATRIBUICAO){
            nextToken();
            fExpConcat();
        }
        System.out.printf("          %s\n", "DeclVar = epsilon");
    }

    //DeclFun = 'fun' TypeF 'id'  '(' LParam ')''{' LSent '}'
    public void fDeclFun(){
        System.out.printf("          %s\n", "DeclFun = 'fun' TypeF 'id'  '(' LParam ')''{' LSent '}'");
        if(token.getCategory() == Category.FUN){
            nextToken();
            fTypeF();
            if(token.getCategory() == Category.IDENTIFICADOR){
                nextToken();
                if(token.getCategory() == Category.ABR_PAR){
                    nextToken();
                    fLParam();
                    if(token.getCategory() == Category.FEC_PAR){
                        nextToken();
                        if(token.getCategory() == Category.ABR_CHA){
                            nextToken();
                            fLSent();
                            if(token.getCategory() == Category.FEC_CHA){
                                nextToken();
                            }
                        }
                    }
                }
            }
        }
    }

    //TypeF = 'void' | Type
    public void fTypeF(){

        if(token.getCategory() == Category.VOID){
            System.out.printf("          %s\n", "TypeF = 'void'");
            nextToken();
        }
        else{
            System.out.printf("          %s\n", "TypeF = 'Type'");
            fType();
            nextToken();
        }


    }

    //Type = 'boolean' | 'float' | 'int' | 'char' | 'string'
    public void fType() {
        if (token.getCategory() == Category.TIP_BOO) {
            System.out.printf("          %s\n", "Type = 'boolean'");
            nextToken();
        } else if (token.getCategory() == Category.TIP_FLO) {
            System.out.printf("          %s\n", "Type = 'float'");
            nextToken();
        } else if (token.getCategory() == Category.TIP_INT) {
            System.out.printf("          %s\n", "Type = 'int'");
            nextToken();
        } else if (token.getCategory() == Category.TIP_CHA) {
            System.out.printf("          %s\n", "Type = 'char'");
            nextToken();
        } else if (token.getCategory() == Category.TIP_STR) {
            System.out.printf("          %s\n", "Type = 'string'");
            nextToken();
        }
    }

    //LParam = Param LParamR | epsilon
    public void fLParam(){
        if(token.getCategory() == Category.VAR) {
            System.out.printf("          %s\n", "LParam = Param LParamR");
            nextToken();
            fParam();
            fLParam();
        }else{
            System.out.printf("          %s\n", "LParam = epsilon");
        }
    }

    //LParamR = ',' Param LParamR | epsilon
    public void fLParamR(){
        if(token.getCategory() == Category.VIRGULA){
            System.out.printf("          %s\n", "LParamR = ',' Param LParamR");
            nextToken();
            fParam();
            fLParamR();
        }else {
            System.out.printf("          %s\n", "LParamR = epsilon");
        }
    }

    //Param = Type 'id' '[' ']' | Type 'id'
    public void fParam(){
        fType();
        if(token.getCategory() == Category.IDENTIFICADOR){
            nextToken();
            if (token.getCategory() == Category.ABR_COL){
                nextToken();
                if (token.getCategory() == Category.FEC_COL){
                    nextToken();
                }else{
                    printErro("] esperado.");
                }
            }else{
                printErro("Identificador esperado");
            }
        }else{
            printErro("Tipo esperado.");
        }
    }

    //LSent = Sent LSent | epsilon
    public void fLSent(){
        if(token.getCategory() == Category.VAR ||
                token.getCategory() == Category.CONTINUE ||
                token.getCategory() == Category.BREAK ||
                token.getCategory() == Category.IDENTIFICADOR ||
                token.getCategory() == Category.OUTPUT ||
                token.getCategory() == Category.INPUT ||
                token.getCategory() == Category.FOR ||
                token.getCategory() == Category.WHILE ||
                token.getCategory() == Category.IF ||
                token.getCategory() == Category.RETURN ){

            System.out.printf("          %s\n", "LSent = Sent LSent");
            fSent();
            fLSent();
        }


    }

    //Sent = DeclVar | Command
    public void fSent(){

    }

    /**
        Command = 'continue' ';'
        Command = 'break' ';'
        Command = 'id' '[' ExpArit ']' Atr ';'
        Command = 'id' '(' LArg ')' ';'
        Command = 'id' Atr ';'
        Command = Print ';'
        Command = Read ';'
        Command = For
        Command = While
        Command = If
        Command = Return
    **/
    public void fCommand(){
        if(token.getCategory() == Category.CONTINUE){
            nextToken();
            if (token.getCategory() == Category.PON_VIR){
                nextToken();
            }
        }
        else if(token.getCategory() == Category.BREAK){
            if (token.getCategory() == Category.PON_VIR){
                nextToken();
            }
        }
        else if(token.getCategory() == Category.IDENTIFICADOR){
            nextToken();
            if(token.getCategory() == Category.ABR_COL){
                nextToken();
                fExpArit();
                if(token.getCategory() == Category.FEC_COL){
                    nextToken();
                    fAtr();
                    if (token.getCategory() == Category.PON_VIR){
                        nextToken();
                    }
                }
            }
        }
        else if(token.getCategory() == Category.IDENTIFICADOR){
            nextToken();
            if(token.getCategory() == Category.ABR_PAR){
                nextToken();
                fLArg();
                if(token.getCategory() == Category.FEC_PAR){
                    nextToken();
                    if(token.getCategory() == Category.PON_VIR){
                        nextToken();
                    }
                }
            }
        }
        else if(token.getCategory() == Category.IDENTIFICADOR){
            nextToken();
            fAtr();
            if (token.getCategory() == Category.PON_VIR){
                nextToken();
            }
        }
        else if(token.getCategory() == Category.OUTPUT){
            fPrint();
            nextToken();
            if(token.getCategory() == Category.PON_VIR){
                nextToken();
            }
        }
        else if(token.getCategory() == Category.INPUT){
            fRead();
            nextToken();
            if(token.getCategory() == Category.PON_VIR){
                nextToken();
            }
        }
        else if(token.getCategory() == Category.FOR){
            fFor();
            nextToken();
        }
        else if(token.getCategory() == Category.WHILE){
            fWhile();
            nextToken();
        }
        else if(token.getCategory() == Category.IF){
            fIf();
            nextToken();
        }
        else if(token.getCategory() == Category.RETURN){
            fReturn();
            nextToken();
        }

        
    }

    //LArg = Arg LArgR | LArg = epsilon
    public void fLArg(){

    }

    //LArgR = ',' Arg LArgR | epsilon
    public void fLArgr(){

    }

    //Arg = ExpConcat
    public void fArg(){

    }

    //Print = 'print' '(' ExpConcat ')'
    public void fPrint(){

    }

    //Read = 'read' '(' LParamR ')'
    public void fRead(){

    }

    //LParamRead = ParamRead LParamReadR
    public void fLParamRead(){

    }

    //LParamReadR = ',' ParamRead LParamReadR | epsilon
    public void fLParamReadR(){

    }

    //ParamRead = 'id'
    public void fParamRead(){

    }

    //For = 'for' 'id' 'in' ExpArit 'to' ExpArit Step '{'LSent'}'
    public void fFor(){

    }

    //Step = 'step' ExpArit | epsilon
    public void fStep(){

    }

    //While = 'while' '(' ExpBool ')' '{' LSent '}'
    public void fWhile(){
        if(token.getCategory() == Category.WHILE) {
            nextToken();
            if(token.getCategory() == Category.ABR_PAR) {
                nextToken();
                fExpBool();
                if(token.getCategory() == Category.FEC_PAR) {
                    nextToken();
                    if (token.getCategory() == Category.ABR_CHA) {
                        nextToken();
                        fLSent();
                        if (token.getCategory() == Category.FEC_CHA) {
                            nextToken();
                        }
                    }
                }
            }
        }
    }

    //If = 'if' '(' ExpBool ')' '{'LSent'}' Else
    public void fIf(){
        if(token.getCategory() == Category.IF) {
            nextToken();
            if(token.getCategory() == Category.ABR_PAR) {
                nextToken();
                fExpBool();
                if(token.getCategory() == Category.FEC_PAR) {
                    nextToken();
                    if(token.getCategory() == Category.ABR_CHA) {
                        nextToken();
                        fLSent();
                        if(token.getCategory() == Category.FEC_CHA) {
                            nextToken();
                            fElse();
                        }else{
                            printErro("} esperado.");

                        }
                    }else{
                        printErro("{ esperado.");
                    }
                }else{
                    printErro(") esperado.");

                }
            }else{
                printErro("( esperado.");

            }
        }else{
            printErro("If esperado.");
        }
    }

    //Else = 'else' '{' LSent '}' | epsilon
    public void fElse(){
        if(token.getCategory() == Category.ELSE) {
            nextToken();
            if(token.getCategory() == Category.ABR_CHA){
                nextToken();
                fLSent();
                if(token.getCategory() == Category.FEC_CHA) {
                    nextToken();
                }
            }
        }else{
            System.out.printf("          %s\n", "Else = epsilon");
        }
    }

    //Return = 'return' ExpConcat ';'
    public void fReturn(){
        if(token.getCategory() == Category.RETURN){
            nextToken();
            fExpConcat();
            if(token.getCategory() == Category.PON_VIR){
                nextToken();
            }else{
                printErro("; esperado.");
            }
        }else{
            printErro("Return esperado.");
        }
    }

    //ExpConcat =	ExpBool ExpConcatR
    public void fExpConcat(){
        fExpBool();
        fExpConcatR();
    }

    //ExpConcatR = '++' ExpBool ExpConcatR | epsilon
    public void fExpConcatR(){
        if(token.getCategory() == Category.OPE_CON){
            nextToken();
            fExpBool();
            fExpConcatR();
        }else {
            System.out.printf("          %s\n", "ExpConcatR = epsilon");
        }
    }

    //ExpBool = TermBool ExpBoolR
    public void fExpBool(){
        fTermBool();
        fExpBoolR();
    }

    //ExpBoolR = 'opr_log' TermBool ExpBoolR | epsilon
    public void fExpBoolR(){
        if(token.getCategory() == Category.OPE_LOG){
            nextToken();
            fTermBool();
            fExpBoolR();
        }else {
            System.out.printf("          %s\n", "ExpBoolR = epsilon");
        }
    }

    //TermBool =	'!' TermBool | ExpArit TermBoolR
    public void fTermBool(){
        if(token.getCategory() == Category.OPE_NEG){
            nextToken();
            fTermBool();
        }else{
            fExpArit();
            fTermBoolR();
        }
    }

    //TermBoolR =	'opr_rel' ExpArit TermBoolR | epsilon
    public void fTermBoolR(){
        if(token.getCategory() == Category.OPE_REL){
            nextToken();
            fExpArit();
            fTermBoolR();
        }else {
            System.out.printf("          %s\n", "TermBoolR = epsilon");
        }
    }

    //ExpArit =	TermArit ExpAritR
    public void fExpArit(){
        fTermArit();
        fExpAritR();
    }
    //ExpAritR = 'opa_adi' TermArit ExpAritR | epsilon
    public void fExpAritR(){
        if(token.getCategory() == Category.OPE_ADI){
            nextToken();
            fTermArit();
            fExpAritR();
        }else{
            System.out.printf("          %s\n", "ExpAritR = epsilon");
        }
    }

    //TermArit = ExpLim TermAritR
    public void fTermArit(){
        fExpLim();
        fTermAritR();
    }
    //TermAritR =	'opa_mult' ExpLim TermAritR | epsilon
    public void fTermAritR(){
        if(token.getCategory() == Category.OPE_MUL){
            nextToken();
            fExpLim();
            fTermAritR();
        }else{
            System.out.printf("          %s\n", "TermAritR = epsilon");
        }
    }

    //ExpLim = FatArit ExpLimR
    public void fExpLim(){
        fFatArit();
        fExpLimR();
    }
    //ExpLimR = 'opa_lim' FatArit ExpLimR | epsilon
    public void fExpLimR(){
        if(token.getCategory() == Category.OPE_LIM){
            nextToken();
            fFatArit();
            fExpLimR();
        }else{
            System.out.printf("          %s\n", "ExpLimR = epsilon");
        }
    }

    /**
     *     FatArit = 'id' '(' LParam ')'
     *     FatArit = 'id' '[' ExpArit ']'
     *     FatArit = 'opa_nega' FatArit
     *     FatArit = 'id'
     *     FatArit = 'cte_int'
     *     FatArit = 'cte_float'
     *     FatArit = 'cte_char'
     *     FatArit = 'cte_str'
     *     FatArit = 'cte_bool'
     * **/
    public void fFatArit() {
        if (token.getCategory() == Category.IDENTIFICADOR) {
            nextToken();
            if (token.getCategory() == Category.ABR_PAR) {
                nextToken();
                fLParam();
                if (token.getCategory() == Category.FEC_PAR) {
                    nextToken();
                }else{
                    printErro("] esperado.");
                }

            }else if (token.getCategory() == Category.ABR_CHA) {
                nextToken();
                fExpArit();
                if (token.getCategory() == Category.FEC_CHA) {
                    nextToken();
                }else{
                    printErro("} esperado.");
                }
            }
        }else if (token.getCategory() == Category.CON_INT) {
            nextToken();
        }else if (token.getCategory() == Category.CON_FLO) {
            nextToken();
        }else if (token.getCategory() == Category.CON_CHA) {
            nextToken();
        }else if (token.getCategory() == Category.CON_STR) {
            nextToken();
        }else if (token.getCategory() == Category.CON_BOO) {
            nextToken();
        }else{
            printErro("Identificador ou constante literal esperada.");
        }
    }

    private void printErro(String content){
        System.err.println("Linha: " + token.getLine() +" Coluna:" + token.getColumn() + "Erro: "+  content);
    }
}
