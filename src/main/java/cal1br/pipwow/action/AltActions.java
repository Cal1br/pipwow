package cal1br.pipwow.action;

import cal1br.pipwow.window.WindowConfiguration;
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

public enum AltActions implements Action {
    INSTANCE;
    private final User32 u32 = com.sun.jna.platform.win32.User32.INSTANCE;

    @Override
    public Map<Integer, Runnable> getActions() {
        Map<Integer, Runnable> actions = new TreeMap<>(){
            @Override
            public Runnable get(final Object key) {
                return super.get(key);
            }
        };
        actions.put(NativeKeyEvent.VC_TAB, () -> {
            final Deque<WinDef.HWND> deque = WindowState.INSTANCE.getStack();
            final WinDef.HWND pip = deque.poll();
            final WinDef.HWND maximized = deque.poll();
            deque.add(maximized);
            deque.add(pip);

            u32.SetForegroundWindow(maximized);

            WindowUtils.setFromRight(pip, WindowConfiguration.INSTANCE.getxOffset(), WindowConfiguration.INSTANCE.getyOffset(), WindowConfiguration.INSTANCE.getPreferredSize());
            WindowUtils.maximize(maximized);
        });
        actions.put(NativeKeyEvent.VC_ESCAPE, () -> {
            try {
                WindowConfiguration.INSTANCE.saveCurrentState();
                GlobalScreen.unregisterNativeHook();
                System.exit(0);
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        });

        actions.put(NativeKeyEvent.VC_UP, () -> {
            WindowConfiguration.INSTANCE.setyOffset(WindowConfiguration.INSTANCE.getyOffset() - 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_DOWN, () -> {
            WindowConfiguration.INSTANCE.setyOffset(WindowConfiguration.INSTANCE.getyOffset() + 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_LEFT, () -> {
            WindowConfiguration.INSTANCE.setxOffset(WindowConfiguration.INSTANCE.getxOffset() + 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_RIGHT, () -> {
            WindowConfiguration.INSTANCE.setxOffset(WindowConfiguration.INSTANCE.getxOffset() - 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_PAGE_UP, () -> {
            WindowConfiguration.INSTANCE.setScale(WindowConfiguration.INSTANCE.getScale() - 0.1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_PAGE_DOWN, () -> {
            WindowConfiguration.INSTANCE.setScale(WindowConfiguration.INSTANCE.getScale() + 0.1);
            WindowUtils.refreshSmall();
        });

        return actions;
    }

}
