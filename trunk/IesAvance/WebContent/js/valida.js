function trim(str){
if (str !=  ""){
	while (str.indexOf(" ") == 0 || str.indexOf("\n") == 1 || str.lastIndexOf(" ") == str.length -1 || str.lastIndexOf("\n") == str.length-1){
	if (str !=  " "){
			if (str.lastIndexOf(" ") == str.length - 1){
				str = str.substring(0, str.length-1)
				}
			else{
				if (str.lastIndexOf("\n") == str.length-1){
					str = str.substring(0, str.length-2)
					}
				else{
					if (str.indexOf(" ") == 0){
						str = str.substring(1,str.length)
						}
					else{
						if (str.indexOf("\n") == 1){
							str = str.substring(2,str.length)
							}				
						}
					}
				}
			}
		else{
			return ""
			}
		}
	}
return str
}


function iif(condicao, truePart, falsePart){
	if (condicao){
		return truePart;
		}
	else{
		return falsePart;
		}
}


function check_cpf (numcpf)
{
   x = 0; soma = 0; dig1 = 0; dig2 = 0; texto = ""; numcpf1="";
   len = numcpf.length; x = len -1;
   for (var i=0; i <= len - 3; i++) {
      y = numcpf.substring(i,i+1); 
      soma = soma + ( y * x); 
      x = x - 1; 
      texto = texto + y; 
   }
   dig1 = 11 - (soma % 11);
   if (dig1 == 10) dig1=0 ; 
   if (dig1 == 11) dig1=0 ;
   numcpf1 = numcpf.substring(0,len - 2) + dig1 ; 
   x = 11; soma=0;
   for (var i=0; i <= len - 2; i++) {
      soma = soma + (numcpf1.substring(i,i+1) * x); x = x - 1; 
   }
   dig2= 11 - (soma % 11);
   if (dig2 == 10) dig2=0;	
   if (dig2 == 11) dig2=0;
   if ((dig1 + "" + dig2) == numcpf.substring(len,len-2)) { 
      return true; 
   }
   return false;
}


function isdate(data){
var dia, mes, ano, maxDiames
if (data.length != 10){
	return false;
	}
else{
	if (data.indexOf("/")!= 2 ||  data.indexOf("/",3)!= 5){
		return false;
		}
	else{
		dia = data.substring(0,2);	mes = data.substring(3,5);	ano = data.substring(6,10)
		if (mes < 1 || mes > 12 || dia < 1 || ano < 1900){
			return false
			}
		else{
			if (mes == 1 || mes == 3  || mes == 5  || mes == 7  || mes == 8 || mes == 10 || mes == 12){
				maxDiames = 31;
				}
			if (mes == 4 || mes == 6 || mes == 9 || mes == 11){
				maxDiames = 30;
				}
			if (mes == 2){ 
				if ((ano % 4) == 0){
					maxDiames = 29;
					}
				else{
					maxDiames = 28;
					}
				}
			if (dia <= maxDiames){
				return true;
				}
			else{
				return false;
				}
			}
		}
	}
}


function nMaxDiaMes(mes, ano){
	if (mes == 1 || mes == 3  || mes == 5  || mes == 7  || mes == 8 || mes == 10 || mes == 12){
		return 31;	
		}
	if (mes == 4 || mes == 6 || mes == 9 || mes == 11){
		return 30;	
		}
	if (mes == 2){ 
		if ((ano % 4) == 0){
			return 29;
			}
		else{
			return 28;
			}
		}
	}

function adicionaDias(data, nDias){
	var dataS, dia, mes, ano, maxDiames, x;
	dataS = data
	for(x=iif(nDias >= 0, 0, 2*nDias ); x < nDias; x++){
		dia = (dataS.substring(0,2)  - 0);
		mes = (dataS.substring(3,5)  - 0);
		ano = (dataS.substring(6,10) - 0);
		maxDiames = nMaxDiaMes(mes, ano);
		if(nDias > 0){
			if (dia < maxDiames){
				dia++;
				}
			else{
				if (mes < 12){
					dia = 1;
					mes++;
					}
				else{
					dia = 1;
					mes = 1;
					ano++;
					}
			}
		}
		else{
			if (dia > 1){
				dia--;
				}
			else{
				if (mes > 1){
					dia = nMaxDiaMes(mes-1,ano);
					mes--;
					}
				else{
					dia = nMaxDiaMes(1, ano-1);
					mes = 1;
					ano--;
					}
			}
		}
		dataS = iif(dia < 10, "0" + dia,  dia) + "/" + iif(mes < 10, "0" + mes, mes)+ "/" + ano ;
	}
	return dataS;
}
	
function datediff(dataInicial, DataFinal){
	var dataComp, anoComp, anoCont, mesComp, diaComp ;
	dataComp = dataInicial;
	anoComp = dataComp.substring(6,10);
	diaComp = iif(dataComp.substring(0,2) > 28, 28, dataComp.substring(0,2));
	mesComp = dataComp.substring(3,5) ;
	anoCont = 0;


	while(dataComp != DataFinal){
		dataComp = adicionaDias(dataComp,1)
		if(anoComp != dataComp.substring(6,10) && diaComp == dataComp.substring(0,2) && mesComp == dataComp.substring(3,5)){
			anoCont++;
			anoComp = dataComp.substring(6,10)
		}
	}
	return anoCont
}

function isEmpty(s)
{   return ((s == null) || (s.length == 0))
}

function isIntegerInRange (x, v1, v2)
{    
  if (!isInteger(x)) return false;
  return ((x >= v1) && (x <= v2));
}

function isInteger (x) 
{
  var i;
  for (i = 0; i < x.length; i++)
  {   
    var c = x.charAt(i);
    if (!isDigit(c)) return false;
  } 
  return true;
}

function isDigit (x)
{
  return ((x >= "0") && (x <= "9"))
}

function isData(dt)
{
  if (dt.length != 10) return false;
  if ((dt.substr(2,1) != '/') || (dt.substr(5,1) != '/'))  return false;
  dia = dt.substr(0, 2);
  if (!isIntegerInRange(dia,1,31)) return false;
  mes = dt.substr(3, 2);
  if (!isIntegerInRange(mes,1,12)) return false;
  ano = dt.substr(6, 4);
  if (!isInteger(ano)) return false;
  return true;
}
function isValor(f)
{ 
  var i;
  var decimalPointDelimiter = ",";
  var seenDecimalPoint = false;
  var posDecPoint = 0;
  var s = f.value;

  if (s.length == 0) return false;
  if (s == decimalPointDelimiter) return false;

  for (i = 0; i < s.length; i++)
  {   
    var c = s.charAt(i);
    if ((c == decimalPointDelimiter) && !seenDecimalPoint)
    { 
      posDecPoint = i;
      seenDecimalPoint = true 
    }
    else 
      if (!isDigit(c)) return false;
  }
  if (!seenDecimalPoint) 
    f.value += ",00";
  else
    { 
      s = f.value + "00";
      f.value= s.charAt(0);
      for (i = 1; i <= (posDecPoint+2); i++)
        f.value += s.charAt(i);
      if (posDecPoint == 0) f.value = "0" + f.value;
    }
  if (f.value.length > 12) return false;
  return true;
}

function c_email(str){
	var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
	return (filter.test(str))
}

function checkemail(email){
if (document.layers||document.getElementById||document.all)
    return c_email(email)
else
    return true
}

function emailNCE(email){

	return true;
}

// Critica dos dados do formulário
//
function CriticarCPF(f)
{
  if (isEmpty(f.cpf.value))
    return aviso(f.cpf,"Cpf não preenchido.");	
  if (!isInteger(f.cpf.value))
    return aviso(f.cpf,"Coloque apenas números no CPF.");	
  if (f.cpf.value.length < 11)
    return aviso(f.cpf,"Informe o Cpf com 11 dígitos. Ex: 76543210011.");	
  if (!check_cpf(f.cpf.value))
    return aviso(f.cpf,"Cpf inválido.");	
}

function dataValida (day, month, year) {
  month = month - 1;  // o mês em javascript é de 0 a 11...
  var tempDate = new Date(year,month,day);
  return ( (year  == tempDate.getFullYear()) &&
           (month == tempDate.getMonth()) &&
           (day   == tempDate.getDate()) )
  }

function aviso (campo, msg)
{ 
  alert(msg);
  campo.focus();
  // campo.select(); só funciona se o campo for text...
  return false;
}

function ValidaDataMesAno(data,anoMin)   
//anoMin: Ano limítrofe inferior (Afeta candidatos menores de 18 anos.) Atualmente 1987 abaixo.
{
	var dia,mes,ano,i;
	var varauxbissexto;
	var obj;
	var tam;
	obj = data;
	tam = parseInt(obj.length,10);
	for(i=0;i<tam;i++)
	{
		if((tam==8)||(tam!=8)&&((i!=2) && (i!=5)))
		{
			if(!(obj.charAt(i) >= '0' && obj.charAt(i) <= '9'))
			{
				alert("DATA NASCIMENTO - Data Inválida.\nDigite a data no formato dd/mm/aaaa.\nExemplo: 31/12/1970.");
				return false;			
			}
			
		}
	}
	
	if(obj.length==8)
	{
		dia = parseInt(obj.substring(0,2),10);
		mes = parseInt(obj.substring(2,4),10);
		ano = parseInt(obj.substring(4,8),10)
	}
	else
	{
		dia = parseInt(obj.substring(0,2),10);
		mes = parseInt(obj.substring(3,5),10);
		ano = parseInt(obj.substring(6,10),10)
		
		if((obj.charAt(2)!='/')||(obj.charAt(5)!='/'))
		{
			if(obj!="")
			{			
				alert("DATA NASCIMENTO - Data Invalida.\nDigite novamente o campo Data Nascimento.");
				return false;
			}
		}
	}	

	//critica data
	if((dia > 31) || (dia == 0))
	{
		alert("DATA NASCIMENTO - Dia Inválido.");
		return false;		
	}
	if ((mes > 12) || (mes == 0))
	{
		alert("DATA NASCIMENTO - Mês Inválido.");
		return false;		
	}
  //Impedir candidatos abaixo de 18 anos. 
	if ((ano < 1900) || (ano > anoMin))
	{
		alert("DATA NASCIMENTO - Ano de nascimento posterior a " + anoMin + "." );
		return false;		
	}
	if(ano%4 == 0)
		varauxbissexto = 1;
	else
		varauxbissexto = 0;
	if(((mes == 2) && (dia > (28 + parseInt(varauxbissexto,10)))) || (mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30))
	{
		alert("DATA DE NASCIMENTO  - Preenchimento inválido.");
		return false;
	}	
	return true;
}

function verificaAspascampo(ficha){
var i, retAspas;
retAspas = true;
for(i=0;i< ficha.length;i++){
	if(ficha.elements(i).value.indexOf("\"") >= 0 || ficha.elements(i).value.indexOf("'") >= 0){
		alert("Não poderá haver aspas no formulário de inscrição!");
		ficha.elements(i).focus();
		retAspas = false;
		break;
		}
	}
return retAspas;
}

