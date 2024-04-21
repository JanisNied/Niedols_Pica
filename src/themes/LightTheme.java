package themes;
import com.formdev.flatlaf.FlatLightLaf;

@SuppressWarnings("serial")
public class LightTheme extends FlatLightLaf{
	public static boolean setup() {
		return setup(new LightTheme());
	}
	
	@Override
	public String getName() {
		return "light";
	}
}
