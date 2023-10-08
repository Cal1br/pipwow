package cal1br.pipwow.action;

import cal1br.pipwow.window.WindowState;
import cal1br.pipwow.window.WindowUtils;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import static cal1br.pipwow.window.WindowInfo.PREFERRED_SIZE;

public enum AltActions implements Action {
    INSTANCE;
    private final User32 u32 = com.sun.jna.platform.win32.User32.INSTANCE;

    @Override
    public Map<Integer, Runnable> getActions() {
        Map<Integer, Runnable> actions = new TreeMap<>(){
            @Override
            public Runnable get(final Object key) {
                System.out.println(key);
                return super.get(key);
            }
        };
        actions.put(NativeKeyEvent.VC_TAB, () -> {
            final Stack<WinDef.HWND> stack = WindowState.INSTANCE.getStack();
            final WinDef.HWND peek = stack.peek();
            if (peek == null) {
                System.out.println("Nothing found");
                return;
            }
            u32.SetForegroundWindow(peek);
            WindowUtils.setFromLeft(peek, 0, 0, PREFERRED_SIZE);
            WindowUtils.setFromRight(peek, 200, 0, PREFERRED_SIZE);
        });
        actions.put(NativeKeyEvent.VC_ESCAPE, () -> {
            try {
                GlobalScreen.unregisterNativeHook();
                System.exit(0);
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        });

        return actions;
    }

}
