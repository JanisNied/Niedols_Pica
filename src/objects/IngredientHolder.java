package objects;

import java.io.Serializable;
import java.util.Objects;

public class IngredientHolder implements Serializable {
	private static final long serialVersionUID = 5972435378762824013L;
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientHolder that = (IngredientHolder) o;
        return type.equals(that.type) &&
                locale.equals(that.locale) &&
                name.equals(that.name) &&
                identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, locale, name, identifier);
    }
}
