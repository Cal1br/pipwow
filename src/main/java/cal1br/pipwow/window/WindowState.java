package cal1br.pipwow.window;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.util.Stack;

public enum WindowState {
    INSTANCE;

    private final User32 u32 = com.sun.jna.platform.win32.User32.INSTANCE;
    private final Stack<WinDef.HWND> stack = new Stack<>();

    WindowState() {
        final WinDef.HWND worldOfWarcraft = u32.FindWindow(null, "World of Warcraft");
        stack.push(worldOfWarcraft);
    }

    public Stack<WinDef.HWND> getStack() {
        return stack;
    }
}
