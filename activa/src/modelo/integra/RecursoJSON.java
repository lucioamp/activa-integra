package modelo.integra;

import java.util.List;

import org.json.JSONException;
import org.json.JSONString;
import org.json.JSONStringer;

public class RecursoJSON implements JSONString {

	private String base;
	private String path;
	private String metodo;
	private String titulo;
	
	private List<ParametroJSON> parametros;

	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the metodo
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * @param metodo the metodo to set
	 */
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the parametros
	 */
	public List<ParametroJSON> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List<ParametroJSON> parametros) {
		this.parametros = parametros;
	}

	@Override
	public String toJSONString() {
		JSONStringer myString = new JSONStringer();
		
		try {
			myString.object()
				.key("base")
			 	.value(this.base)
			 	.key("path")
			 	.value(this.path)
			 	.key("metodo")
			 	.value(this.metodo)
			 	.key("titulo")
			 	.value(this.titulo)
			 ;	
			 	
			myString.endObject();
		} catch (JSONException e) {
			System.out.println("Não foi possível criar string para RecursoJSON");
		}
		
		return myString.toString();
	}
	
}
