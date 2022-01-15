package cool.avocado.forge.modifiedanvil.mixin;

import cool.avocado.forge.modifiedanvil.config.Handler;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import static cool.avocado.forge.modifiedanvil.utils.Log.ANVIL_MENU_MIXIN;
import static cool.avocado.forge.modifiedanvil.utils.Log.LOGGER;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @Redirect(method = "createResult", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/inventory/DataSlot;set(I)V"))
    private void clampToMax(DataSlot cost, int i) {
        int maxAnvilLevel = Handler.GENERAL.maxAnvilLevel.get();
        boolean clampToMax = Handler.GENERAL.clampToMax.get();
        if (clampToMax) {
            LOGGER.debug(ANVIL_MENU_MIXIN, "Base cost should be: " + i + ". Can clamp to max level: " + maxAnvilLevel);
            cost.set(Math.min(i, maxAnvilLevel));
        } else {
            LOGGER.debug(ANVIL_MENU_MIXIN, "Base cost is: " + i + ". Clamping is not configured. Max level: " + maxAnvilLevel);
            cost.set(i);
        }
    }

    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
    private int changeMax(int a) {
        int maxAnvilLevel = Handler.GENERAL.maxAnvilLevel.get();
        LOGGER.debug(ANVIL_MENU_MIXIN, "Setting max level in calculation to: " + maxAnvilLevel);
        return maxAnvilLevel + 1;
    }

    @Overwrite
    public static int calculateIncreasedRepairCost(int baseCost) {
        boolean linear = Handler.GENERAL.makeEnchantmentCostLinear.get();
        if (linear) {
            int newIncreasedCost = baseCost + 1;
            LOGGER.debug(ANVIL_MENU_MIXIN, "Linear enchantment cost enabled. Base cost: " + baseCost + " New increased cost is: " + newIncreasedCost);
            return newIncreasedCost;
        } else {
            int vanillaIncreaseCost = baseCost * 2 + 1;
            LOGGER.debug(ANVIL_MENU_MIXIN, "Linear enchantment cost is not enabled. Base cost: " + baseCost + " New increased cost is: " + vanillaIncreaseCost);
            return vanillaIncreaseCost;
        }
    }

}