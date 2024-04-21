package themes;

import com.formdev.flatlaf.FlatDarkLaf;

@SuppressWarnings("serial")
public class DarkTheme extends FlatDarkLaf{
	public static boolean setup() {
		return setup(new DarkTheme());
	}
	
	@Override
	public String getName() {
		return "dark";
	}
}
