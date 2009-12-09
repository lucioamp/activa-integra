package util.interpretador;

public class RestParser 
{
	protected static final int ERRO    = 0;
	protected static final int RECURSO = 1;
	protected static final int IDSTR   = 2;
	protected static final int IDINT   = 3;
	
	private ContextList contexto;
	
	public RestParser() 
	{
		super();
		
		contexto = new ContextList();
	}

	public ContextList getContexto()			  { return contexto;			}
	public void setContexto(ContextList contexto) { this.contexto = contexto;	}

	public int interpreta(ParseNode tab[], String parStr) throws ExcecaoParser
	{
		int no, pv;
		int ultimo;
		String parVet[];
		
		parVet = parStr.substring(1).split("/");
		
		pv = 0;
		no = 1;
		ultimo = parVet.length - 1;
		while ( no != 0 )
		{
			if ( tab[no].getTipo() == RECURSO )
			{
				// Se for igual quero ir para o proximo
				if ( !parVet[pv].equals(tab[no].getValor()) )
				{
					no = tab[no].getAlter();
					continue;
				}
			}
			else if ( tab[no].getTipo() == IDSTR )
			{
				contexto.add(new StrContextNode(tab[no].getValor(), parVet[pv]));
			}
			else if ( tab[no].getTipo() == IDINT )
			{
				int valor = 0;
				
				try
				{
					valor = Integer.parseInt(parVet[pv]);
					
					contexto.add(new IntContextNode(tab[no].getValor(), valor));
				}
				catch (Exception e)
				{
					throw new ExcecaoParser("Erro: " + parVet[pv] + " não numérico!");
				}
			}
			else
			{
				throw new ExcecaoParser("Tipo desconhecido na tabela: " + tab[no].getTipo());
			}

			// Se o parVet chegou ao final
			if ( pv == ultimo )
				break;
			
			pv++;
			no = tab[no].getProx();
		}

		// Verifico se cheguei ao final mas ainda há elementos na URI
		if ( pv != ultimo )
			throw new ExcecaoParser("Erro: " + parVet[pv] + " inesperado!");

		// E não tem função associada -> ERRO!
		if ( tab[no].getObjeto() == null )
			throw new ExcecaoParser("Final inesperado de URI");

		// Executa a função!
		return no;
	}

	public void executa(ParseNode tab[], int no, String prefixo) throws ExcecaoParser
	{
		tab[no].executa( getContexto(), prefixo );
	}
}
