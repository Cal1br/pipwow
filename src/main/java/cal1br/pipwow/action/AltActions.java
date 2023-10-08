package cal1br.pipwow.action;

import cal1br.pipwow.window.WindowState;
import cal1br.pipwow.window.WindowUtils;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.util.Deque;
import java.util.Map;
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
            final Deque<WinDef.HWND> deque = WindowState.INSTANCE.getStack();
            //todo iterator
            final WinDef.HWND pip = deque.poll();
            final WinDef.HWND maximized = deque.poll();
            deque.add(maximized);
            deque.add(pip);

            u32.SetForegroundWindow(maximized);
            WindowUtils.setFromRight(pip, 300, 300, PREFERRED_SIZE);
            WindowUtils.maximize(maximized);
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
