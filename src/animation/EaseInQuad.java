package animation;

import org.jdesktop.animation.timing.interpolation.Interpolator;

public class EaseInQuad implements Interpolator {
	@Override
    public float interpolate(float x) {
    	return x*x;
    }
}
