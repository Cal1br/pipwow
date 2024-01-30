package cal1br.pipwow.action;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public enum ActionTree {
    INSTANCE;

    private final Map<Integer, Map<Integer, Consumer<NativeKeyEvent>>> actions = new TreeMap<>();
    private final Map<Integer, Map<Integer, Consumer<NativeMouseEvent>>> mouseEvents = new TreeMap<>();

    ActionTree() {
        actions.put(NativeKeyEvent.VC_ALT, AltActions.INSTANCE.getKeyActions());
        mouseEvents.put(NativeKeyEvent.VC_ALT, AltActions.INSTANCE.getMouseActions());
        mouseEvents.put(Integer.MIN_VALUE, NoModActions.INSTANCE.getMouseActions());
    }

    public Map<Integer, Map<Integer, Consumer<NativeKeyEvent>>> getKeyActions() {
        return Collections.unmodifiableMap(actions);
    }

    public Map<Integer, Map<Integer, Consumer<NativeMouseEvent>>> getMouseActions() {
        return Collections.unmodifiableMap(mouseEvents);
    }
}
