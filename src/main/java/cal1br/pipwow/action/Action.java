package cal1br.pipwow.action;

import java.util.Map;

public interface Action {
    Map<Integer, Runnable> getActions();
}
