package token;

public enum Category {
    VOID(1), FEC_PAR(2), ABR_PAR(3), ABR_CHA(4), FEC_CHA(5), ABR_COL(6), FEC_COL(7), VIRGULA(8),
    PON_VIR(9), ATRIBUICAO(10), IDENTIFICADOR(11), TIP_INT(12), TIP_FLO(13), TIP_BOO(14), TIP_CHA(15),
    TIP_STR(16), OPE_ADI(17), OPE_MUL(18), OPE_REL(19), OPE_LOG(20), OPE_NEG(21), OPE_CON(22), RETURN(23),
    IF(24), ELSE(25), FOR(26), IN(27), TO(28), STEP(29), WHILE(30), VAR(31), INPUT(32), OUTPUT(33),
    FUN(34), CONTINUE(35), BREAK(36), CON_INT(37), CON_FLO(38), CON_BOO(39), CON_CHA(40),
    CON_STR(41), EOF(42), OPE_LIM(43);

    private final int value;
    Category(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
