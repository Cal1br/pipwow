package cal1br.pipwow.window;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public enum WindowState {
    INSTANCE;

    private final User32 u32 = com.sun.jna.platform.win32.User32.INSTANCE;
    private final Deque<WinDef.HWND> deque = new LinkedList<>();

    WindowState() {
        u32.EnumWindows((pointer, data) -> {
            final char[] buffer = new char[256];
            u32.GetWindowText(pointer, buffer, 256);
            if (Native.toString(buffer).contains("World of Warcraft")) {
                System.out.println("Found WoW!");
                deque.add(pointer);
            }

            return true;
        }, null);
        if (deque.isEmpty()) {
            System.out.println("Didn't find any WoW instances. Terminating...");
            System.exit(0);
        }
    }

    public Deque<WinDef.HWND> getStack() {
        return deque;
    }
}
