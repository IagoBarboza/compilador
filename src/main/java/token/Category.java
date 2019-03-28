package token;

public enum Category {
    MAIN(1), VOID(2), FEC_PAR(3), ABR_PAR(4), ABR_CHA(5), FEC_CHA(6), ABR_COL(7), FEC_COL(8), VIRGULA(9),
    PON_VIR(10), ATRIBUICAO(11), IDENTIFICADOR(12), TIP_INT(13), TIP_FLO(14), TIP_BOO(15), TIP_CHA(16),
    TIP_STR(17), OPE_ADI(18), OPE_SUB(19), OPE_MUL(20), OPE_DIV(21), OPE_IGU(22), OPE_DIF(23), OPE_MEN(24),
    OPE_MAI(25), OPE_MEN_IGU(26), OPE_MAI_IGU(27), OPE_OU(28), OPE_E(29), OPE_NEG(30), OPE_CON(31), RETURN(32),
    IF(33), ELSE(34), FOR(35), IN(36), TO(37), STEP(38), WHILE(39), VAR(40), INPUT(41), OUTPUT(42),
    FUN(43), CONTINUE(44), BREAK(45), CON_INT(46), CON_FLO(47), CON_BOO(48), CON_CHA(49),
    CON_STR(50), EOF(51), OPE_FOR_CAM(52), OPE_FOR_DEC(53);

    private final int value;
    Category(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
