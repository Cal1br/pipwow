package cal1br.pipwow.window;

import cal1br.pipwow.window.struct.WindowSize;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public enum WindowConfiguration {
    INSTANCE;

    private static final String CONF_NAME = "conf.json";
    private final File file = new File(CONF_NAME);
    private int displayWidth = 1920;
    private int displayHeight = 1080;
    private int xOffset = 300;
    private int yOffset = 300;
    private double scale = 6;
    private WindowSize preferredSize;


    WindowConfiguration() {
        loadStateFromFile();
    }

    public void saveCurrentState() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error when reading saving file! Message: " + e.getMessage());
        }
        final JSONObject object = new JSONObject();
        object.put("displayWidth", displayWidth);
        object.put("displayHeight", displayHeight);
        object.put("xOffset", xOffset);
        object.put("yOffset", yOffset);
        object.put("scale", scale);
        try (FileOutputStream writer  = new FileOutputStream(file)) {
            final byte[] bytes = object.toString().getBytes();
            writer.write(bytes,0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException("Error when saving configuration! Message " + e.getMessage());
        }
    }

    public void loadStateFromFile() {
        try {
            if (file.createNewFile()) {
                saveCurrentState();
            }
        } catch (IOException e) {
            saveCurrentState();
        }
        try {
            final JSONObject json = new JSONObject(Files.readString(Paths.get(CONF_NAME)));
            displayWidth = json.getInt("displayWidth");
            displayHeight = json.getInt("displayHeight");
            xOffset = json.getInt("xOffset");
            yOffset = json.getInt("yOffset");
            scale = json.getDouble("scale");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found! Message: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Error when reading from file! Message: " + e.getMessage());
        }
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(final int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(final int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(final int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(final int yOffset) {
        this.yOffset = yOffset;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(final double scale) {
        this.scale = scale;
        preferredSize = new WindowSize((int) (displayWidth / scale), (int) (displayHeight / scale));
    }

    public WindowSize getPreferredSize() {
        if (preferredSize == null) {
            preferredSize = new WindowSize((int) (displayWidth / scale), (int) (displayHeight / scale));
        }
        return preferredSize;
    }
}
