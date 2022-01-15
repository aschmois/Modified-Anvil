package cool.avocado.forge.modifiedanvil;

import cool.avocado.forge.modifiedanvil.config.Handler;
import cool.avocado.forge.modifiedanvil.utils.Log;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "modifiedanvil";

    public Main() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, Handler.spec);
        Log.init();
    }

}
