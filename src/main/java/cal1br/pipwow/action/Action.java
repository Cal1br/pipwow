package cal1br.pipwow.action;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.util.Map;
import java.util.function.Consumer;

public interface Action {
    Map<Integer, Consumer<NativeKeyEvent>> getKeyActions();
    Map<Integer, Consumer<NativeMouseEvent>> getMouseActions();
}
