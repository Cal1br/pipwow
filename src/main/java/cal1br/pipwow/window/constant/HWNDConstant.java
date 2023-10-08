package cal1br.pipwow.window.constant;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;

/**
 * Missing constants from JNA
 */
public interface HWNDConstant {
    WinDef.HWND HWND_BROADCAST = new WinDef.HWND(new Pointer(0xffff));
    WinDef.HWND HWND_TOP = new WinDef.HWND(new Pointer(0));
    WinDef.HWND HWND_BOTTOM = new WinDef.HWND(new Pointer(1));
    WinDef.HWND HWND_TOPMOST = new WinDef.HWND(new Pointer(-1));
    WinDef.HWND HWND_NOTOPMOST = new WinDef.HWND(new Pointer(-2));
    WinDef.HWND HWND_MESSAGE = new WinDef.HWND(new Pointer(-3));
}
