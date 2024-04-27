package objects;

public class IngredientHolder {
	private String type, locale, name, identifier;
	
	public IngredientHolder(String type, String locale, String name, String identifier) {
		this.type = type;
		this.locale = locale;
		this.name = name;
		this.identifier = identifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
