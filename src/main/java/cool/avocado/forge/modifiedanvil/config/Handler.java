package cool.avocado.forge.modifiedanvil.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Handler {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    public static class General {
        public final ForgeConfigSpec.ConfigValue<Integer> maxAnvilLevel;
        public final ForgeConfigSpec.ConfigValue<Boolean> clampToMax;
        public final ForgeConfigSpec.ConfigValue<Boolean> makeEnchantmentCostLinear;

        public General(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            maxAnvilLevel = builder
                    .comment("Change max anvil level")
                    .defineInRange("maxAnvilLevel", 40, 1, Integer.MAX_VALUE);

            clampToMax = builder
                    .comment("Clamp anvil to not go above max level even if calculations would make it more expensive. This will effectively remove \"Too Expensive!\" message.")
                    .define("clampToMax", true);

            makeEnchantmentCostLinear = builder
                    .comment("By default enchantments costs grow exponentially, making them linear with a higher max cost, stretches out the amount of times you can repair or add/combine enchantments before reaching max level.")
                    .define("makeEnchantmentCostLinear", true);

            builder.pop();
        }
    }
}