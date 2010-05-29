package modelo.integra;

import org.json.JSONString;
import org.json.JSONStringer;


public class ParametroJSON implements JSONString {
	
	private String name;
	private boolean required;
	private String title;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required
	 *            the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toJSONString() {
		JSONStringer myString = new JSONStringer();
		return myString.toString();
	}

}
