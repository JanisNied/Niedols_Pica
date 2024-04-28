package objects;

import java.awt.Component;
import java.net.URL;

import javax.swing.JButton;

import raven.swing.AnimationIcon;
import raven.swing.animation.KeyFrames;

@SuppressWarnings("serial")
public class AnimatedButton extends JButton {

    public AnimatedButton(String text, URL icon) {
        setText(text);
        if (icon != null) {
            AnimationIcon.AnimatedOption option = new AnimationIcon.AnimatedOption()
                    .setScaleInterpolator(new KeyFrames(0f, 0.4f))
                    .setRotateInterpolator(new KeyFrames(0f, 20f, 0f))
                    .setDuration(350);
            setIcon(new AnimationIconCustom(icon, 1.5f, option));
        }
    }

    private class AnimationIconCustom extends AnimationIcon {

        public AnimationIconCustom(URL name, float scale, AnimationIcon.AnimatedOption option) {
            super(name, scale, option);
        }

        @Override
        public float getValue(Component component) {
            return getModel().isRollover() ? 1f : 0f;
        }
    }
}
