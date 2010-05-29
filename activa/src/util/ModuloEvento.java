package util;

import java.util.ArrayList;

public class ModuloEvento /*extends Thread*/ {
	private String nome;
	private ArrayList<Character> eventos = new ArrayList<Character>();
	
	public ModuloEvento(String nome) {
		this.nome = nome;
	}
	
	public ModuloEvento() {
	}
	
	public boolean registrarEvento(char evento)
	{
		if(eventoRegistrado(evento))
			return false;
		
		this.eventos.add(evento);
		
		return true;
	}
	
	public boolean eventoRegistrado(char evento)
	{
		for(Object object:this.eventos)
		{
			if(object.equals(evento))
				return true;
		}
		return false;
	}
	
	public boolean removerEvento(char evento)
	{
		return this.removerEvento(evento, 0);
	}
	
	public boolean removerEvento(char evento, long time)
	{
		if(time != 0)
		{
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0, c = this.eventos.size(); i < c; i++)
		{
			if(this.eventos.get(i).equals(evento))
			{
				this.eventos.remove(i);
				return true;
			}
		}
		
		return false;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
