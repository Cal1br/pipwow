package cal1br.pipwow.keylistener;


import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.TreeMap;

public enum ModKey {
    VC_ALT(NativeKeyEvent.VC_ALT), VC_CONTROL(NativeKeyEvent.VC_CONTROL), VC_META(NativeKeyEvent.VC_META), VC_CONTEXT_MEN(NativeKeyEvent.VC_CONTEXT_MENU)/*, VC_SHIFT(NativeKeyEvent.VC_SHIFT)*/;


    static final TreeMap<Integer, Integer> valueToIndexMap = new TreeMap<>();

    static {
        for (int i = 0; i < ModKey.values().length; i++) {
            valueToIndexMap.put(ModKey.values()[i].value, i);
        }
    }

    private final int value;

    ModKey(final int value) {
        this.value = value;
    }

    public static int getIndexByValue(int val) {
        final Integer integer = valueToIndexMap.get(val);
        if (null == integer) {
            return -1;
        }
        return integer;
    }

    public int getValue() {
        return value;
    }
}
