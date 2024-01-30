package cal1br.pipwow.action;

import cal1br.pipwow.window.WindowConfiguration;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public enum NoModActions implements Action {
    INSTANCE;
    private final Robot robot;

    NoModActions() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Map<Integer, Consumer<NativeKeyEvent>> getKeyActions() {
        Map<Integer, Consumer<NativeKeyEvent>> actions = new TreeMap<>() {
            @Override
            public Consumer<NativeKeyEvent> get(final Object key) {
                return super.get(key);
            }
        };
        return actions;
    }

    @Override
    public Map<Integer, Consumer<NativeMouseEvent>> getMouseActions() {
        Map<Integer, Consumer<NativeMouseEvent>> actions = new TreeMap<>() {
            @Override
            public Consumer<NativeMouseEvent> get(final Object key) {
                return super.get(key);
            }
        };

        actions.put(NativeMouseEvent.BUTTON5, (mouseEvent) -> {
            /*final Rectangle rectangle = new Rectangle(
                    WindowConfiguration.INSTANCE.getDisplayWidth() - WindowConfiguration.INSTANCE.getxOffset(), WindowConfiguration.INSTANCE.getyOffset(), WindowConfiguration.INSTANCE.getPreferredSize().x(), WindowConfiguration.INSTANCE.getPreferredSize().y());*/
            final Rectangle rectangle = new Rectangle(WindowConfiguration.INSTANCE.getDisplayWidth() - (WindowConfiguration.INSTANCE.getxOffset() + WindowConfiguration.INSTANCE.getPreferredSize().x()), WindowConfiguration.INSTANCE.getyOffset(), WindowConfiguration.INSTANCE.getPreferredSize().x(), WindowConfiguration.INSTANCE.getPreferredSize().y());

            if (rectangle.contains(mouseEvent.getX(), mouseEvent.getY())) {
                robot.mouseMove(WindowConfiguration.INSTANCE.getDisplayWidth() / 2, WindowConfiguration.INSTANCE.getDisplayHeight() / 2);
            } else {
                robot.mouseMove(WindowConfiguration.INSTANCE.getDisplayWidth() - WindowConfiguration.INSTANCE.getxOffset() - WindowConfiguration.INSTANCE.getPreferredSize().x() / 2, WindowConfiguration.INSTANCE.getyOffset() + WindowConfiguration.INSTANCE.getPreferredSize().y() / 2);
            }
        });

        return actions;
    }
}
