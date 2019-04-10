
// ini = ldecl :: lsent . 
ini(){
    ldcecl();
    if (TOKEN == '::'){
        proximoToken();
        lsent();
        if (TOKEN == '.'){
            proximoToken();
            if(TOKEN == EOF)
                return;
            else
                erro(EOF, " esperado ", TOKEN );
        }
        else{
            erro('.', " esperado ", TOKEN );
        }
    }
    else{
        erro('::', " esperado ", TOKEN );
    }
}

// ldecl = decl ldeclr
ldecl(){
    decl();
    ldeclr();
}

// ldecl = ';' decl ldeclr | vazio
ldeclr(){
    if(TOKEN == ';'){
        proximoToken();
        decl();
        ldeclr();
    }
}

//decl = lid ':' tipo
decl(){
    lid();
    if(TOKEN == ':'){
        proximoToken();
        tipo();
    }
    else{
        erro(':' , " esperado ", TOKEN);
    }
}


//lid = 'id' lidr
lid(){
    if(TOKEN == 'id'){
        proximoToken();
        lidr();
    }
    else{
        erro('id', " esperado ", TOKEN);
    }
}

//lidr = ',' 'id' lidr | vazio
lidr(){
    if(TOKEN == ','){
        proximoToken();
        if(TOKEN == 'id'){
            proximoToken();
            lidr();
        }
        else{
            erro('id', " esperado ", TOKEN);
        }
    }
}

//tipo = tbase tipof
tipo(){
    tbase();
    tipof();
}

//tipof = '[' ctei ']' | vazio 
tipof(){
    if(TOKEN == '['){
        proximoToken();
        ctei();
        if(TOKEN == ']'){
            proximoToken();
        }
        else{
            erro(']', " esperado ", TOKEN);
        }
    }
} 

//tbase = 'int' | 'real'
tbase(){
    if(TOKEN == 'int')
        proximoToken();        
    else if(TOKEN == 'real')
        proximoToken();
    else
        erro('int', "ou", 'real', " esperado", TOKEN);
}

//lsent = sent lsentr
lsent(){
    sent();
    lsentr();
}

//lsentr = ';'sent lsentr
lsentr(){
    if(TOKEN == ';'){
        proximoToken();
        sent();
        lsentr();
    }
}

//sent = atr | cond | iter
sent(){
    if(TOKEN == 'id')
        art();
    else if(TOKEN == 'se')
        cond();
    else if(TOKEN == 'para' || 'enquanto' || 'repita')    
        iter();
    else
        erro("sentença esperada", TOKEN);
}

//atr = 'id' '=' ea
atr(){
    if(TOKEN == 'id'){
        proximoToken();
        if(TOKEN == '='){
            proximoToken();
            ea();
        }
        else{
            erro('=', " esperado ", TOKEN);
        }
    }
    else{
        erro('id', " esperado ", TOKEN);
    }
}

//cond = 'se' eb 'entao' lsent condf
cond(){
    if(TOKEN == 'se'){
		proximoToken();
		eb();
		if(TOKEN == 'entao'){
			proximoToken();
			lsent();
			condf();
		}else{
			erro('entao', "esperado", TOKEN);
		}
	}else{
		erro('se', " esperado", TOKEN)
	}
}

//condf = 'senao' lsent 'fim' | fim
condf(){
    if(TOKEN == 'senao'){
		proximoToken();
		lsetn();
		if(TOKEN == 'fim'){
			proximoToken();
		}else{
			erro(FIM, " esperado", TOKEN);
		}
	}
    else if (TOKEN == 'fim'){
		proximoToken();
	}
    else{
		erro(SENAO, "ou", FIM, " esperado", TOKEN);
	}
}

//iter = 'para' atr 'ate'  ea passo 'faca' lsent 'fim' | 'enquanto' eb 'faca' lsent 'fim' | 'repita' lsent 'enquanto' eb 'fim'
iter(){
    if(TOKEN == 'para'){
		tk.next();
		atr();
		if(TOKEN == 'ate'){
			proximoToken();
			ea();
			passo();
			if(TOKEN == 'faca'){
				proximoToken();
				lsent();
				if(TOKEN == 'fim'){
					proximoToken();
				}
                else{
					erro('fim', " esperado", TOKEN);
				}
			}
            else{
				erro('faca', " esperado", TOKEN);
			}
		}
        else{
			erro('ate', " esperado");
		}	
	}
    else if(TOKEN == 'enquanto'){
		proximoToken();
		eb();
		if(TOKEN == 'faca'){
			proximoToken();
			lsent();
			if(TOKEN == 'fim'){
				proximoToken();
			}
            else{
				erro('fim', " esperado", TOKEN);
			}
		}
        else{
			erro('faca', " esperado", TOKEN);
		}
	}
    else if(TOKEN == 'repita'){
		proximoToken();
		lsent();
		if(TOKEN == 'enquanto'){
			proximoToken();
			eb();
			if(TOKEN == 'fim'){
				proximoToken();
			}
            else{
				erro('fim', " esperado", TOKEN);
			}
		}
        else{
			erro('enquanto', " esperado", TOKEN);
		}
	}
    else{
		erro('para', ",", 'enquanto', "ou", 'repita', " esperado", TOKEN);
	}
}

//passo = 'passo' ea | vazio
passo(){
    if(TOKEN == 'passo'){
        proximoToken();
        ea();
    }
}