package nucleo.lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nucleo.nivel.NivelAvance;

abstract public class ListaNivelAvance extends ArrayList<NivelAvance> 
{
	static final long serialVersionUID = 1; 
	
	abstract NivelAvance criaNivel( ResultSet rs ) throws SQLException;

	public NivelAvance Busca( String nome )
	{
		for (int i = 0; i < size(); i++)
			if ( get(i).getNome().equals(nome) )
				return get(i);
		
		return null;
	}
	
	public NivelAvance Inclui( ResultSet rs ) throws SQLException
	{
		String nome;
		NivelAvance regNivel;
		
		nome = NivelAvance.getNome(rs);
		regNivel = Busca( nome );

		if ( regNivel == null )
		{
			regNivel = criaNivel( rs ); 
			regNivel.setNomeResponsavel( rs );

	    	add( regNivel );
		}
		
		return regNivel;
	}
}
