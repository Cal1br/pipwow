package cal1br.pipwow.action;

import cal1br.pipwow.window.WindowConfiguration;
import cal1br.pipwow.window.WindowState;
import cal1br.pipwow.window.WindowUtils;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public enum AltActions implements Action {
    INSTANCE;
    private final User32 u32 = com.sun.jna.platform.win32.User32.INSTANCE;

    @Override
    public Map<Integer, Consumer<NativeKeyEvent>> getKeyActions() {
        Map<Integer, Consumer<NativeKeyEvent>> actions = new TreeMap<>() {
            @Override
            public Consumer<NativeKeyEvent> get(final Object key) {
                return super.get(key);
            }
        };

        actions.put(NativeKeyEvent.VC_TAB, (key) -> {
            final Deque<WinDef.HWND> deque = WindowState.INSTANCE.getStack();
            final WinDef.HWND pip = deque.poll();
            final WinDef.HWND maximized = deque.poll();
            deque.add(maximized);
            deque.add(pip);
            WindowUtils.maximize(maximized);
            WindowUtils.setFromRight(pip, WindowConfiguration.INSTANCE.getxOffset(), WindowConfiguration.INSTANCE.getyOffset(), WindowConfiguration.INSTANCE.getPreferredSize());
        });


        actions.put(NativeKeyEvent.VC_SHIFT, (key) -> {
            final Deque<WinDef.HWND> deque = WindowState.INSTANCE.getStack();
            final WinDef.HWND pip = deque.poll();
            final WinDef.HWND maximized = deque.poll();
            deque.add(maximized);
            deque.add(pip);
            WindowUtils.maximize(maximized);
            WindowUtils.setFromRight(pip, WindowConfiguration.INSTANCE.getxOffset(), WindowConfiguration.INSTANCE.getyOffset(), WindowConfiguration.INSTANCE.getPreferredSize());
            final int identity = u32.GetWindowThreadProcessId(maximized, null);
            final WinDef.DWORD idAttachTo = new WinDef.DWORD(identity);
            final WinDef.DWORD idAttach = new WinDef.DWORD(Kernel32.INSTANCE.GetCurrentThreadId());

/*
            u32.AttachThreadInput(idAttach, idAttachTo, true);

            u32.SetForegroundWindow(maximized);
            u32.SetFocus(maximized);


            u32.AttachThreadInput(idAttach, idAttachTo, false);

 */
        });
        actions.put(NativeKeyEvent.VC_ESCAPE, (key) -> {
            try {
                WindowConfiguration.INSTANCE.saveCurrentState();
                GlobalScreen.unregisterNativeHook();
                System.exit(0);
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        });

        actions.put(NativeKeyEvent.VC_UP, (key) -> {
            WindowConfiguration.INSTANCE.setyOffset(WindowConfiguration.INSTANCE.getyOffset() - 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_DOWN, (key) -> {
            WindowConfiguration.INSTANCE.setyOffset(WindowConfiguration.INSTANCE.getyOffset() + 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_LEFT, (key) -> {
            WindowConfiguration.INSTANCE.setxOffset(WindowConfiguration.INSTANCE.getxOffset() + 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_RIGHT, (key) -> {
            WindowConfiguration.INSTANCE.setxOffset(WindowConfiguration.INSTANCE.getxOffset() - 1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_PAGE_UP, (key) -> {
            WindowConfiguration.INSTANCE.setScale(WindowConfiguration.INSTANCE.getScale() - 0.1);
            WindowUtils.refreshSmall();

        });

        actions.put(NativeKeyEvent.VC_PAGE_DOWN, (key) -> {
            WindowConfiguration.INSTANCE.setScale(WindowConfiguration.INSTANCE.getScale() + 0.1);
            WindowUtils.refreshSmall();
        });

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
        actions.put(NativeMouseEvent.BUTTON1, (mouseEvent) -> {
            WindowConfiguration.INSTANCE.setxOffset(WindowConfiguration.INSTANCE.getDisplayWidth() - mouseEvent.getX() - WindowConfiguration.INSTANCE.getPreferredSize().x()/2);
            WindowConfiguration.INSTANCE.setyOffset(mouseEvent.getY() - WindowConfiguration.INSTANCE.getPreferredSize().y()/2);
            WindowUtils.refreshSmall();
        });
        return actions;
    }

}
