package cal1br.pipwow.window;

import cal1br.pipwow.window.struct.WindowSize;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import static cal1br.pipwow.window.constant.HWNDConstant.HWND_NOTOPMOST;
import static cal1br.pipwow.window.constant.HWNDConstant.HWND_TOPMOST;

public class WindowUtils {
    private final static User32 u32 = User32.INSTANCE;

    public static WinDef.HWND smallWindow = null;
    public static WindowSize lastSize = null;

    private WindowUtils() {
    }

    public static void setFromRight(final WinDef.HWND handle, int x, int y, final WindowSize size) {
        smallWindow = handle;
        lastSize = size;
        u32.SetWindowPos(handle, HWND_TOPMOST, WindowConfiguration.INSTANCE.getDisplayWidth() - x - size.x(), y, size.x(), size.y(), 0);
    }

    public static void maximize(final WinDef.HWND maximized) {
        u32.SetWindowPos(maximized, HWND_NOTOPMOST, 0, 0, WindowConfiguration.INSTANCE.getDisplayWidth(), WindowConfiguration.INSTANCE.getDisplayHeight(), 0);
    }

    /**
     * @param size can pass null to not change the size
     */
    public static void refreshSmall() {
        if (smallWindow == null) {
            return;
        }
        u32.SetWindowPos(smallWindow, HWND_TOPMOST, WindowConfiguration.INSTANCE.getDisplayWidth() - WindowConfiguration.INSTANCE.getxOffset() - lastSize.x(), WindowConfiguration.INSTANCE.getyOffset(), WindowConfiguration.INSTANCE.getPreferredSize().x(), WindowConfiguration.INSTANCE.getPreferredSize().y(), 0);
    }
}
