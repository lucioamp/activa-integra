var tabNomeForm = [
          { nome: "CriarTema",			critica: criticaCriarTema         }, 
          { nome: "CriarAssunto",		critica: criticaCriarAssunto      },
          { nome: "CriarTopico",		critica: criticaCriarTopico       },
          { nome: "CriarMensagem",		critica: criticaCriarMensagem     },
          { nome: "CadastrarUsuario",	critica: criticaCadastrarUsuario  },
          { nome: "Curso",				critica: criticaCurso             },
          { nome: "Turma",				critica: criticaTurma             }
    ];

function chamaCritica( nomeForm, f )
{
    var i;
    
    for (i = 0; i < tabNomeForm.length; i++)
        if ( tabNomeForm.nome == nomeForm )
             return tabNomeForm.critica( f );
    
    return true;
}

function criticaCriarTema(f)
{
	if ( isEmpty(f.f_nome.value) )
		return aviso(f.f_nome,"Nome do Tema n�o preenchido.");

	if ( isEmpty(f.f_descricao.value) )
	    return aviso(f.f_descricao,"Descri��o do Tema n�o preenchida.");

	return true;
}

function criticaCriarAssunto(f)
{
	if ( isEmpty(f.f_nome.value) )
		return aviso(f.f_nome,"Nome do Assunto n�o preenchido.");

	if ( isEmpty(f.f_descricao.value) )
	    return aviso(f.f_descricao,"Descri��o do Assunto n�o preenchida.");

	return true;
}

function criticaCriarTopico(f)
{
	if ( isEmpty(f.f_nome.value) )
		return aviso(f.f_nome,"Nome do T�pico n�o preenchido.");

	if ( isEmpty(f.f_descricao.value) )
	    return aviso(f.f_descricao,"Descri��o do T�pico n�o preenchida.");

	return true;
}

function criticaCriarMensagem(f)
{
	if ( isEmpty(f.f_nome.value) )
		return aviso(f.f_nome,"Nome da Mensagem n�o preenchido.");

	if ( isEmpty(f.f_texto.value) )
	    return aviso(f.f_texto, "Texto da Mensagem n�o preenchido.");

	return true;
}

function criticaCadastrarUsuario(f)
{
	if (isEmpty(f.f_cpf.value))
    	return aviso(f.f_cpf,"CPF n�o preenchido.");

	if ( !isInteger(f.f_cpf.value) )
	    return aviso(f.f_cpf,"CPF deve ter um valor num�rico.");	

	if ( f.f_cpf.value.length < 11 )
	    return aviso(f.f_cpf,"Informe o CPF com 11 d�gitos. Ex: 76543210011.");	
	    
	if ( !check_cpf(f.f_cpf.value) )
	    return aviso(f.f_cpf,"CPF inv�lido.");	

	if ( isEmpty(f.f_senha.value) )
		return aviso(f.f_senha,"Senha n�o preenchida.");

	if ( isEmpty(f.f_confirma.value) )
	    return aviso(f.f_confirma,"Confirma��o da Senha n�o preenchida.");

	if ( f.f_senha.value != f.f_confirma.value )
	    return aviso(f.f_senha,"Senha diferente da Confirma��o da Senha.");

	if ( isEmpty(f.f_nomeUsuario.value) )
	    return aviso(f.f_nomeUsuario,"Nome n�o preenchido.");

	if ( isEmpty(f.f_email.value) )
	    return aviso(f.f_email,"E-mail n�o preenchido.");

	if ( !checkemail(f.f_email.value) )
	    return aviso(f.f_email,"E-mail inv�lido.");

	if ( !isEmpty(f.f_telefone.value) ) 
	{
		if ( !isInteger(f.f_telefone.value) )
		    return aviso(f.f_telefone,"Telefone deve ter um valor num�rico. Ex.:22226541");	

		if ( f.f_telefone.value.length < 8 )
		    return aviso(f.f_telefone,"Informe o Telefone com 8 d�gitos. Ex.:23451122");	
	}

	// return verificaAspascampo(f);
	return true;
}

function criticaCurso(f)
{
	if ( isEmpty(f.nomeCurso.value) )
		return aviso(f.nomeCurso,"Nome do Curso n�o preenchido.");
	
	if ( isEmpty(f.instituicao.value) )
		return aviso(f.instituicao,"Institui��o n�o preenchida.");
	
	if ( isEmpty(f.idResponsavel.value) )
		return aviso(f.idResponsavel,"Respons�vel n�o preenchido.");
	
	// return verificaAspascampo(f);
	return true;
}

/*
 * Ementa n�o � obrigat�ria
 */
function criticaDisciplina(f)
{
	if ( isEmpty(f.nomeDisciplina.value) )
		return aviso(f.nomeDisciplina,"Nome da Disciplina n�o preenchida.");
	
	if ( isEmpty(f.cargaHoraria.value) )
		return aviso(f.cargaHoraria,"Carga Hor�ria n�o preenchida.");

	if ( !isInteger(f.cargaHoraria.value) )
	    return aviso(f.cargaHoraria,"Carga Hor�ria deve ter um valor num�rico.");	
	
	if ( isEmpty(f.idCurso.value) )
		return aviso(f.idCurso,"Curso n�o preenchido.");
	
	if ( isEmpty(f.idResponsavel.value) )
		return aviso(f.idResponsavel,"Respons�vel n�o preenchido.");
	
	// return verificaAspascampo(f);
	return true;
}

/*
 * Tutor n�o � obrigat�rio
 */
function criticaTurma(f)
{
	if ( isEmpty(f.nomeTurma.value) )
		return aviso(f.nomeTurma,"Nome da Turma n�o preenchido.");
	
	if ( isEmpty(f.strDataInicio.value) )
		return aviso(f.strDataInicio,"Data de In�cio n�o preenchida.");
	
	if ( isEmpty(f.strDataFinal.value) )
		return aviso(f.strDataFinal,"Data Final n�o preenchida.");
	
	if ( isEmpty(f.idDisciplina.value) )
		return aviso(f.idDisciplina,"Disciplina n�o preenchida.");
	
	if ( isEmpty(f.idProfessor.value) )
		return aviso(f.idProfessor,"Professor n�o preenchido.");
	
	// return verificaAspascampo(f);
	return true;
}
