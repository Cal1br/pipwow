package cal1br.pipwow.keylistener;

import cal1br.pipwow.action.ActionTree;
import cal1br.pipwow.action.NoModActions;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

import java.util.Map;
import java.util.function.Consumer;

public class GlobalKeyListener implements NativeKeyListener, NativeMouseListener {
    //todo move with mouse
    //todo accerelate movement with keys
    //todo mouse 4 refocus other window, and move mouse to center

    private final static boolean[] modKeysPressed = new boolean[ModKey.values().length];

    public GlobalKeyListener() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        final int modIndex = ModKey.getIndexByValue(e.getKeyCode());
        if (modIndex != -1) {
            modKeysPressed[modIndex] = true;
            return;
        }
        for (int i = 0; i < modKeysPressed.length; i++) {
            if (!modKeysPressed[i]) {
                continue;
            }
            final Map<Integer, Consumer<NativeKeyEvent>> map = ActionTree.INSTANCE.getKeyActions().get(ModKey.values()[i].getValue());
            if (map == null) {
                continue;
            }
            final Consumer<NativeKeyEvent> runnable = map.get(e.getKeyCode());
            if (runnable != null) {
                runnable.accept(e);
            }
        }
        final Map<Integer, Consumer<NativeKeyEvent>> integerConsumerMap = ActionTree.INSTANCE.getKeyActions().get(Integer.MIN_VALUE);
        if(integerConsumerMap!= null){
            final Consumer<NativeKeyEvent> runnable = integerConsumerMap.get(e);
            if (runnable != null) {
                runnable.accept(e);
            }
        }
    }

    @Override
    public void nativeMousePressed(final NativeMouseEvent e) {
        for (int i = 0; i < modKeysPressed.length; i++) {
            if (!modKeysPressed[i]) {
                continue;
            }
            final Map<Integer, Consumer<NativeMouseEvent>> map = ActionTree.INSTANCE.getMouseActions().get(ModKey.values()[i].getValue());
            if (map == null) {
                continue;
            }
            final Consumer<NativeMouseEvent> nativeMouseEventConsumer = map.get(e.getButton());
            if (nativeMouseEventConsumer != null) {
                nativeMouseEventConsumer.accept(e);
            }
        }
        final Consumer<NativeMouseEvent> consumer = ActionTree.INSTANCE.getMouseActions().get(Integer.MIN_VALUE).get(e.getButton());
        if (consumer != null) {
            consumer.accept(e);
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        final int modIndex = ModKey.getIndexByValue(e.getKeyCode());
        if (modIndex != -1) {
            modKeysPressed[modIndex] = false;
        }
    }
}
