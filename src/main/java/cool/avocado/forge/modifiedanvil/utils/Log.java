package cool.avocado.forge.modifiedanvil.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import static cool.avocado.forge.modifiedanvil.Main.MOD_ID;

public class Log {

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final Marker MODIFIED_ANVIL = MarkerManager.getMarker("MODIFIED_ANVIL");

    public static final Marker ANVIL_MENU_MIXIN = MarkerManager.getMarker("ANVIL_MENU_MIXIN");
    public static final Marker ANVIL_SCREEN_MIXIN = MarkerManager.getMarker("ANVIL_SCREEN_MIXIN");

    public static void init() {
        ANVIL_MENU_MIXIN.setParents(MODIFIED_ANVIL);
        ANVIL_SCREEN_MIXIN.setParents(MODIFIED_ANVIL);

        LOGGER.debug(MODIFIED_ANVIL, "Hi, my name is modifiedanvil");
    }
}
