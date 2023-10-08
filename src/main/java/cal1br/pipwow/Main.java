package cal1br.pipwow;

import cal1br.pipwow.action.ActionTree;
import cal1br.pipwow.keylistener.GlobalKeyListener;
import cal1br.pipwow.window.WindowState;
import cal1br.pipwow.window.struct.WindowSize;
import com.sun.jna.platform.win32.WinDef;

import java.util.Map;
import java.util.Stack;

public class Main {
    final static WindowSize windowSize = new WindowSize(250, 250);

    public static void main(String[] args) {

        final Map<Integer, Map<Integer, Runnable>> actions = ActionTree.INSTANCE.getActions();
        final Stack<WinDef.HWND> stack = WindowState.INSTANCE.getStack();
        final GlobalKeyListener globalKeyListener = new GlobalKeyListener();
    }
}