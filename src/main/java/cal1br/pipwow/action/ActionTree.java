package cal1br.pipwow.action;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public enum ActionTree {
    INSTANCE;

    private final Map<Integer, Map<Integer, Runnable>> actions = new TreeMap<>();

    ActionTree() {
        actions.put(NativeKeyEvent.VC_ALT, AltActions.INSTANCE.getActions());
        actions.put(Integer.MIN_VALUE, Map.of());
    }

    public Map<Integer, Map<Integer, Runnable>> getActions() {
        return Collections.unmodifiableMap(actions);
    }
}
