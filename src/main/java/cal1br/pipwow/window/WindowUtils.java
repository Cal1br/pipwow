package cal1br.pipwow.window;

import cal1br.pipwow.window.struct.WindowSize;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import static cal1br.pipwow.window.constant.HWNDConstant.HWND_TOPMOST;

public class WindowUtils {
    final static User32 u32 = User32.INSTANCE;

    private WindowUtils() {
    }

    public static void setFromRight(final WinDef.HWND handle, int x, int y, final WindowSize size) {
        u32.SetWindowPos(handle, HWND_TOPMOST, WindowInfo.DISPLAY_WIDTH - x - size.x(), y, size.x(), size.y(), 0);
    }

    public static void setFromLeft(final WinDef.HWND handle, int x, int y, final WindowSize size) {
        u32.SetWindowPos(handle, HWND_TOPMOST, x, y, size.x(), size.y(), 0);
    }
}
