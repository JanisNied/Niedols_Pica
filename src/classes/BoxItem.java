package classes;

public class BoxItem {
    private final String value;
    private final String displayText;

    public BoxItem(String value, String displayText) {
        this.value = value;
        this.displayText = displayText;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
