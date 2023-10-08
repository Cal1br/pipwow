package cal1br.pipwow.keylistener;

import cal1br.pipwow.action.ActionTree;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {

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
            final Runnable runnable = ActionTree.INSTANCE.getActions().get(ModKey.values()[i].getValue()).get(e.getKeyCode());
            if (runnable != null) {
                runnable.run();
            }
        }
        final Runnable runnable = ActionTree.INSTANCE.getActions().get(Integer.MIN_VALUE).get(e);
        if (runnable != null) {
            runnable.run();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        final int modIndex = ModKey.getIndexByValue(e.getKeyCode());
        if (modIndex != -1) {
            modKeysPressed[modIndex] = false;
        }
    }
}
