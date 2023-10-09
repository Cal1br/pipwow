package cal1br.pipwow;

import cal1br.pipwow.keylistener.GlobalKeyListener;
import cal1br.pipwow.window.WindowConfiguration;

public class Main {

    public static void main(String[] args) {
        System.out.println("Success!");
        final WindowConfiguration instance = WindowConfiguration.INSTANCE;
        new GlobalKeyListener();
    }
}