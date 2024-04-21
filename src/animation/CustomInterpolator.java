package animation;

import org.jdesktop.animation.timing.interpolation.Interpolator;

public class CustomInterpolator implements Interpolator {
    @Override
    public float interpolate(float x) {
    	return 1 - (1 - x) * (1 - x);
    }
}
