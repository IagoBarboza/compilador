calc(){
    if(TOKEN == '='){
		proximoToken();
		printf("%f", Ea());	
	}
    else{
		erro(" = esperado");	
	}
}

float fEa(){
	
	Ta.val=Ta();
	Ear.vh=Ta.val;
	Ear.vs=fEar(Ear.vh);
	Ea.val= Ear.vs;
	return(Ea.val);
}

float fEar(Ear){
	if(TOKEN == '+'){
		proximoToken();
		Ta.val = Ta();
		Ear1.vh = Ear.vh + Ta.val; 
		Ear1.vs = fEar(Ear1.vh);
		Ear.vs = Ear1.vs;
	}
    else if(TOKEN == MENOS){
		proximoToken();

		Ta.val = fTa();
		Ear1.vh = Ear.vh - Ta.val;
		Ear1.vs = fEar(Ear1.vh);
		Ear.vs = Ear1.vs;
	}
    else{
		Ear.vs=Ear.vh;
	}
	return Ear.vs;
}

float fTa(){

	Fav.al = Fa();
	Tar.vh = Fav.al;
	Tar.vs = fTar(Tar.vh);
	Ta.val = Tar.vs;
	return Ta.val;
}

float fTar(Tar){
  
	if(TOKEN == '*'){
		proximoToken();
		
		Fav.al = fFa();
		Tar1.vh = Tar.vh * Fav.al;
		Tar1.vs = fTar(Tar1.vh);
		Tar.vs = Tar1.vs;
	}
    else if(TOKEN == DIV){
		proximoToken();
		Fav.al = fFa();
		Tar1.vh = Tarvh / Faval;
		Tar1.vs = fTar(Tar1.vh);
		Tar.vs = Tar1.vs;
	}else{
		Tar.vs = Tar.vh;
	}
	return Tar.vs;
}

float fFa(){

	if(TOKEN == '('){
		proximoToken();

		Ea.val = fEa();
		Fa.val = Ea.val;
		if(TOKEN == ')'){
			proximoToken();
			return Fa.val;
		}else{
			erro(" ) esperado");
		}
	}else if(TOKEN == 'cteN') {
		proximoToken();
		Fa.val = atof(TOKEN.lex);
	}else{
		erro(" '(' ou 'Constante' esperados");
	}