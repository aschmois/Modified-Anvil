package cool.avocado.forge.modifiedanvil.mixin;

import cool.avocado.forge.modifiedanvil.config.Handler;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static cool.avocado.forge.modifiedanvil.utils.Log.ANVIL_SCREEN_MIXIN;
import static cool.avocado.forge.modifiedanvil.utils.Log.LOGGER;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin {

    @ModifyConstant(method = "m_280003_", constant = @Constant(intValue = 40))
    private int changeMax(int a) {
        int maxAnvilLevel = Handler.GENERAL.maxAnvilLevel.get();
        LOGGER.debug(ANVIL_SCREEN_MIXIN, "Setting max level in calculation to: " + maxAnvilLevel);
        return maxAnvilLevel + 1;
    }

}