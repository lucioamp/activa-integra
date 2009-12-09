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
		return aviso(f.f_nome,"Nome do Tema não preenchido.");

	if ( isEmpty(f.f_descricao.value) )
	    return aviso(f.f_descricao,"Descrição do Tema não preenchida.");

	return true;
}

function criticaCriarAssunto(f)
{
	if ( isEmpty(f.f_nome.value) )
		return aviso(f.f_nome,"Nome do Assunto não preenchido.");

	if ( isEmpty(f.f_descricao.value) )
	    return aviso(f.f_descricao,"Descrição do Assunto não preenchida.");

	return true;
}

function criticaCriarTopico(f)
{
	if ( isEmpty(f.f_nome.value) )
		return aviso(f.f_nome,"Nome do Tópico não preenchido.");

	if ( isEmpty(f.f_descricao.value) )
	    return aviso(f.f_descricao,"Descrição do Tópico não preenchida.");

	return true;
}

function criticaCriarMensagem(f)
{
	if ( isEmpty(f.f_nome.value) )
		return aviso(f.f_nome,"Nome da Mensagem não preenchido.");

	if ( isEmpty(f.f_texto.value) )
	    return aviso(f.f_texto, "Texto da Mensagem não preenchido.");

	return true;
}

function criticaCadastrarUsuario(f)
{
	if (isEmpty(f.f_cpf.value))
    	return aviso(f.f_cpf,"CPF não preenchido.");

	if ( !isInteger(f.f_cpf.value) )
	    return aviso(f.f_cpf,"CPF deve ter um valor numérico.");	

	if ( f.f_cpf.value.length < 11 )
	    return aviso(f.f_cpf,"Informe o CPF com 11 dígitos. Ex: 76543210011.");	
	    
	if ( !check_cpf(f.f_cpf.value) )
	    return aviso(f.f_cpf,"CPF inválido.");	

	if ( isEmpty(f.f_senha.value) )
		return aviso(f.f_senha,"Senha não preenchida.");

	if ( isEmpty(f.f_confirma.value) )
	    return aviso(f.f_confirma,"Confirmação da Senha não preenchida.");

	if ( f.f_senha.value != f.f_confirma.value )
	    return aviso(f.f_senha,"Senha diferente da Confirmação da Senha.");

	if ( isEmpty(f.f_nomeUsuario.value) )
	    return aviso(f.f_nomeUsuario,"Nome não preenchido.");

	if ( isEmpty(f.f_email.value) )
	    return aviso(f.f_email,"E-mail não preenchido.");

	if ( !checkemail(f.f_email.value) )
	    return aviso(f.f_email,"E-mail inválido.");

	if ( !isEmpty(f.f_telefone.value) ) 
	{
		if ( !isInteger(f.f_telefone.value) )
		    return aviso(f.f_telefone,"Telefone deve ter um valor numérico. Ex.:22226541");	

		if ( f.f_telefone.value.length < 8 )
		    return aviso(f.f_telefone,"Informe o Telefone com 8 dígitos. Ex.:23451122");	
	}

	// return verificaAspascampo(f);
	return true;
}

function criticaCurso(f)
{
	if ( isEmpty(f.nomeCurso.value) )
		return aviso(f.nomeCurso,"Nome do Curso não preenchido.");
	
	if ( isEmpty(f.instituicao.value) )
		return aviso(f.instituicao,"Instituição não preenchida.");
	
	if ( isEmpty(f.idResponsavel.value) )
		return aviso(f.idResponsavel,"Responsável não preenchido.");
	
	// return verificaAspascampo(f);
	return true;
}

/*
 * Ementa não é obrigatória
 */
function criticaDisciplina(f)
{
	if ( isEmpty(f.nomeDisciplina.value) )
		return aviso(f.nomeDisciplina,"Nome da Disciplina não preenchida.");
	
	if ( isEmpty(f.cargaHoraria.value) )
		return aviso(f.cargaHoraria,"Carga Horária não preenchida.");

	if ( !isInteger(f.cargaHoraria.value) )
	    return aviso(f.cargaHoraria,"Carga Horária deve ter um valor numérico.");	
	
	if ( isEmpty(f.idCurso.value) )
		return aviso(f.idCurso,"Curso não preenchido.");
	
	if ( isEmpty(f.idResponsavel.value) )
		return aviso(f.idResponsavel,"Responsável não preenchido.");
	
	// return verificaAspascampo(f);
	return true;
}

/*
 * Tutor não é obrigatório
 */
function criticaTurma(f)
{
	if ( isEmpty(f.nomeTurma.value) )
		return aviso(f.nomeTurma,"Nome da Turma não preenchido.");
	
	if ( isEmpty(f.strDataInicio.value) )
		return aviso(f.strDataInicio,"Data de Início não preenchida.");
	
	if ( isEmpty(f.strDataFinal.value) )
		return aviso(f.strDataFinal,"Data Final não preenchida.");
	
	if ( isEmpty(f.idDisciplina.value) )
		return aviso(f.idDisciplina,"Disciplina não preenchida.");
	
	if ( isEmpty(f.idProfessor.value) )
		return aviso(f.idProfessor,"Professor não preenchido.");
	
	// return verificaAspascampo(f);
	return true;
}
