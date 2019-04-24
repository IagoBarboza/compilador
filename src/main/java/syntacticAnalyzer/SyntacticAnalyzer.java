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

    }

    //Atr = '=' ExpConcat | epsilon
    public void fAtr(){

    }

    //DeclFun = 'fun' TypeF 'id'  '(' LParam ')''{' LSent '}'
    public void fDeclFun(){

    }

    //TypeF = 'void' | Type
    public void fTypeF(){

    }

    //Type = 'boolean' | 'float' | 'int' | 'char' | 'string'
    public void fType(){

    }

    //LParam = Param LParam1
    public void fLParam(){

    }

    //LParam1 = ',' Param LParam1 | epsilon
    public void fLParam1(){

    }

    //Param = Type 'id' '[' ']' | Type 'id' | epsilon
    public void Param(){

    }

    //LSent = Sent LSent | epsilon
    public void fLSent(){

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

    }

    //LArg = Arg LArgR
    public void fLArg(){

    }

    //LArgR = ',' LArgR | epsilon
    public void fLArgr(){

    }

    //Arg = ExpConcat | epsilon
    public void fArg(){

    }

    //Print = 'print' '(' ExpConcat ')'
    public void fPrint(){

    }

    //Read = 'read' '(' LParamR ')'
    public void fRead(){

    }

    //LParamR = ParamR LParamR1
    public void fLParamR(){

    }

    //LParamR1 = ',' ParamR LParamR1 | epsilon
    public void fLParamR1(){

    }

    //ParamR = 'id'
    public void fParamR(){

    }

    //For = 'for' 'id' 'in' ExpArit 'to' ExpArit Step '{'LSent'}'
    public void fFor(){

    }

    //Step = 'step' ExpArit | epsilon
    public void fStep(){

    }

    //While = 'while' '(' ExpBool ')' '{' LSent '}'
    public void fWhile(){

    }

    //If = 'if' '(' ExpBool ')' '{'LSent'}' Else
    public void fIf(){

    }

    //Else = 'else' '{' LSent '}' | epsilon
    public void fElse(){

    }

    //Return = 'return' ExpConcat ';' | epsilon
    public void fReturn(){

    }

    //ExpConcat =	ExpBool ExpConcatR
    public void fExpConcat(){

    }

    //ExpConcatR = '++' ExpBool ExpConcatR | epsilon
    public void fExpConcatr(){

    }

    //ExpBool = TermBool ExpBool1
    public void fExpBool(){

    }

    //ExpBoolR = 'opr_log' TermBool ExpBoolR | epsilon
    public void fExpBoolr(){

    }

    //TermBool =	'!' TermBool TermBoolR | ExpArit TermBoolR
    public void fTermBool(){

    }
    //TermBoolR =	'opr_rel' ExpArit TermBoolR | epsilon
    public void fTermBoolr(){

    }

    //ExpArit =	TermArit ExpAritR
    public void fExpArit(){

    }
    //ExpAritR = 'opa_adi' TermArit ExpAritR | ExpAritR
    public void fExpAritr(){

    }

    //TermArit = ExpLim TermAritR
    public void fTermArit(){

    }
    //TermAritR =	'opa_mult' ExpLim TermAritR | epsilon
    public void fTermAritr(){

    }

    //ExpLim = FatArit ExpLimR
    public void fExpLim(){

    }
    //ExpLimR = 'opa_lim' FatArit ExpLimR | epsilon
    public void fExpLimr(){

    }

    /**
     *     FatArit = 'id' '(' LParam ')'
     *     FatArit = 'id' '[' ExpArit ']'
     *     FatArit = 'opa_nega' FatArit
     *     FatArit = 'id'
     *     FatArit = 'cte_int'
     *     FatArit = 'cte_float'
     *     FatArit = 'cte_char'
     *     FatArit = 'cte_cad_ch'
     *     FatArit = 'cte_bool'
     * **/
    public void fFatArit(){

    }
}
