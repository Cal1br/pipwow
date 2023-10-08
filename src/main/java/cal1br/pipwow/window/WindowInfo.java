package cal1br.pipwow.window;

import cal1br.pipwow.window.struct.WindowSize;

public interface WindowInfo {
    int DISPLAY_WIDTH = 2560;
    int DISPLAY_HEIGHT = 1440;

    WindowSize PREFERRED_SIZE = new WindowSize(DISPLAY_WIDTH / 5, DISPLAY_HEIGHT / 5);
    //WindowSize ORIGINAL_SIZE = new WindowSize(DISPLAY_WIDTH , DISPLAY_HEIGHT);
}
