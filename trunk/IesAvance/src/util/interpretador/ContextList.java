package util.interpretador;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ContextList 
{
	private List<ContextNode> contexto;

	public ContextList() 
	{
		super();
		contexto = new ArrayList<ContextNode>();
	}
	
	public void add(ContextNode no)
	{
		contexto.add(no);
	}
	
	public ContextNode getNode(String nome)
	{
		Iterator it;
		ContextNode no;
		
		it = contexto.iterator();
		while(it.hasNext()) 
		{
			no = (ContextNode)it.next();
			if ( no.getNome().equals(nome) )
				return no;
		}
		
		return null;
	}
	
	public int getIntNode(String nome)
	{
		Iterator it;
		ContextNode no;
		
		it = contexto.iterator();
		while(it.hasNext()) 
		{
			no = (ContextNode)it.next();
			if ( no.getNome().equals(nome) )
				return no.getValInt();
		}
		
		return 0;
	}
	
	public String getStrNode(String nome)
	{
		Iterator it;
		ContextNode no;
		
		it = contexto.iterator();
		while(it.hasNext()) 
		{
			no = (ContextNode)it.next();
			if ( no.getNome().equals(nome) )
				return no.getValStr();
		}
		
		return null;
	}
}
